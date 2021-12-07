package com.QCINE.Main.AuthController;

import com.QCINE.Main.DTO.GoogleAccount;
import com.QCINE.Main.Entity.Customer_Entity;
import com.QCINE.Main.Entity.Role_Entity;
import com.QCINE.Main.Entity.User_Entity;
import com.QCINE.Main.Repository.Customer_Repository;
import com.QCINE.Main.Repository.Role_Repository;
import com.QCINE.Main.Repository.User_Repository;
import com.QCINE.Main.Service.Customer_Service;
import com.QCINE.Main.Service.SendGridMail_Service;
import com.QCINE.Main.Service.User_Service;
import com.QCINE.Main.payload.request.LoginRequest;
import com.QCINE.Main.payload.request.SignupRequest;
import com.QCINE.Main.payload.response.JwtResponse;
import com.QCINE.Main.payload.response.MessageResponse;
import com.QCINE.Main.security.config.GoogleUtils;
import com.QCINE.Main.security.config.JwtUtils;
import com.QCINE.Main.security.config.RestFB;
import com.QCINE.Main.security.service.UserDetailsImpl;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    GoogleUtils googleUtils;

    @Autowired
    RestFB restFb;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    User_Repository userRepository;

    @Autowired
    Customer_Repository customerRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    Role_Repository roleRepository;

    @Autowired
    Customer_Service customersService;

    @Autowired
    SendGridMail_Service sendGridMailService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws ParseException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getName(),
                userDetails.getEmail(),
                userDetails.getIdrap(),
                roles,jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        try {

            if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Email is already in use!"));
            }

            // Create new user's account
            User_Entity userEntity = new User_Entity(signUpRequest.getFirstname(),signUpRequest.getEmail(), signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()));

            Set<String> strRoles = signUpRequest.getRole();
            Set<Role_Entity> roleEntities = new HashSet<>();
            if (strRoles == null) {
                Role_Entity userRoleEntity = roleRepository.findByRoleName(Role_Entity.role_name.USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roleEntities.add(userRoleEntity);
            } else {
                strRoles.forEach(role -> {
                    switch (role.toLowerCase()) {
                        case "author":
                            Role_Entity authorRoleEntity = roleRepository.findByRoleName(Role_Entity.role_name.AUTHOR)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roleEntities.add(authorRoleEntity);
                            break;
                        case "admin":
                            Role_Entity adminRoleEntity = roleRepository.findByRoleName(Role_Entity.role_name.ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roleEntities.add(adminRoleEntity);
                            break;
                        case "staff":
                            Role_Entity staffRoleEntity = roleRepository.findByRoleName(Role_Entity.role_name.STAFF)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roleEntities.add(staffRoleEntity);
                            break;
                        default:
                            Role_Entity userRoleEntity = roleRepository.findByRoleName(Role_Entity.role_name.USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roleEntities.add(userRoleEntity);
                    }
                });
            }
            userEntity.setName(signUpRequest.getFirstname());
            userEntity.setIdRap(signUpRequest.getIdrap());
            userEntity.setRoles(roleEntities);
            userRepository.save(userEntity);

            //create default customer profile
            customersService.createCustomer(signUpRequest,userEntity);

            sendGridMailService.sendTemplateMail("welcome",signUpRequest.getEmail(),
                    signUpRequest.getFirstname(),"",0,"","","","","","");

            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

        }catch (Exception e){
            System.out.println("Exception "+e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registered error!");
        }
    }


    @GetMapping("/login-google")
    public ResponseEntity<?> loginGoogle(HttpServletRequest request) throws IOException, ParseException, URISyntaxException {
        try {

            System.out.println("login with google");
            String code = request.getParameter("code");
            if (code == null || code.isEmpty()) {
                System.out.println("login fail");
                return ResponseEntity.badRequest().body("Error login with gmail");
            }
            String accessToken = googleUtils.getToken(code);
            GoogleAccount googlePojo = googleUtils.getUserInfo(accessToken);

            //signup USER
            Optional<User_Entity> user = userRepository.findByUsername(googlePojo.getEmail());
            if(!user.isPresent()){
                System.out.println("Create new user");
                String[] name = googlePojo.getEmail().split("@");
                User_Entity userEntity = new User_Entity(name[0],googlePojo.getEmail(), googlePojo.getEmail(), encoder.encode(googlePojo.getId()));

                Set<Role_Entity> roleEntities = new HashSet<>();
                Role_Entity userRoleEntity = roleRepository.findByRoleName(Role_Entity.role_name.USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roleEntities.add(userRoleEntity);
                userEntity.setRoles(roleEntities);
                userEntity.setIdRap("none");
                userEntity.setName(name[0]);
                User_Entity users =  userRepository.save(userEntity);

                //signup customer
                Customer_Entity customer = new Customer_Entity();
                customer.setFirstName(name[0]);
                customer.setPromoPoint(0);
                customer.setAvatar("https://s3.us-east-2.amazonaws.com/myawsbucketappfile/1622470096048-avatar.png");
                customer.setUserEntity(users);
                customerRepository.save(customer);
            }
            System.out.println("login user");
            // auththen account
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(googlePojo.getEmail(),googlePojo.getId()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            URI yahoo = new URI("http://localhost:3000/login?active=true&platform=google&token="+jwt);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(yahoo);
            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        }catch (Exception e){
            System.out.println("Excrption: "+e);
            URI yahoo = new URI("http://localhost:3000/login?active=false&platform=google");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(yahoo);
            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        }
    }


    @GetMapping("/login-facebook")
    public ResponseEntity loginFacebook(HttpServletRequest request) throws ClientProtocolException, IOException, ParseException, URISyntaxException {
        try{
            String code = request.getParameter("code");
            if (code == null || code.isEmpty()) {
                System.out.println("login fail");
                return ResponseEntity.badRequest().body("Error login with facebook");
            }
            String accessToken = restFb.getToken(code);
            com.restfb.types.User userfb = restFb.getUserInfo(accessToken);

            //signup USER
            Optional<User_Entity> user = userRepository.findByUsername(userfb.getId());
            if(!user.isPresent()){
                System.out.println("Create new user");
                User_Entity userEntity = new User_Entity("",userfb.getId(), userfb.getId(), encoder.encode(userfb.getId()));

                Set<Role_Entity> roleEntities = new HashSet<>();
                Role_Entity userRoleEntity = roleRepository.findByRoleName(Role_Entity.role_name.USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roleEntities.add(userRoleEntity);
                userEntity.setRoles(roleEntities);
                userEntity.setIdRap("none");
                User_Entity users =  userRepository.save(userEntity);

                //signup customer
                Customer_Entity customer = new Customer_Entity();
                customer.setPromoPoint(0);
                customer.setAvatar("https://s3.us-east-2.amazonaws.com/myawsbucketappfile/1622470096048-avatar.png");
                customer.setUserEntity(users);
                customerRepository.save(customer);
            }
            System.out.println("login user");
            // auththen account
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userfb.getId(),userfb.getId()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            URI yahoo = new URI("http://localhost:3000/login?active=true&platform=facebook&token="+jwt);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(yahoo);
            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        }catch (Exception e){
            System.out.println("Excrption: "+e);
            URI yahoo = new URI("http://localhost:3000/login?active=false&platform=facebook");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(yahoo);
            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        }
    }

}
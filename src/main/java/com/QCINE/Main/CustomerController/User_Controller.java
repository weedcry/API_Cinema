package com.QCINE.Main.CustomerController;

import com.QCINE.Main.DTO.ChangePassword;
import com.QCINE.Main.Entity.User_Entity;
import com.QCINE.Main.Repository.User_Repository;
import com.QCINE.Main.Service.PasswordResetToken_Service;
import com.QCINE.Main.Service.SendGridMail_Service;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class User_Controller {
    @Autowired
    User_Repository user_repository;

    @Autowired
    PasswordResetToken_Service service;

    @Autowired
    SendGridMail_Service sendGridMailService;

    @PostMapping("/resetpassword")
    public ResponseEntity resetPassword(@RequestParam(name = "email") String email){
        if (!user_repository.existsByEmail(email)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("email not found");
        }

        if(service.createCodeResetPass(email)){
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed");
    }

    @PostMapping("/resetpassword/code")
    public ResponseEntity validateCode(@RequestParam(name = "email") String email,
                                       @RequestParam(name = "code") String code){

        String token = service.validateCodeResetPass(email,code);
        if(!token.equals("")){
            Map<String,String> tokenM = new HashMap<>();
            tokenM.put("token",token);
            return ResponseEntity.status(HttpStatus.OK).body(tokenM);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed");
    }

    @PostMapping("/resetpassword/new")
    public ResponseEntity newPass(@RequestParam(name = "email") String email,
                                    @RequestBody ChangePassword changePassword){

        if(service.createNewPass(changePassword,email)){
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed");
    }

    @PostMapping("/mail/sendtextmail")
    public String sendTextmail() throws IOException {
        return sendGridMailService.sendTextEmail();
    }

    @PostMapping("/mail/sendtemplatemail")
    public String sendTemplateMail(@RequestParam(name = "mode") String mode,
                                   @RequestParam(name = "email") String email,
                                   @RequestParam(name = "idlich") int  idlich) throws IOException {
        return sendGridMailService.sendTemplateMail(mode,email,
                "phong","1234",idlich,"05/11/2021 11:11:11","A7","1",
                "75000","0","75000");
    }

}

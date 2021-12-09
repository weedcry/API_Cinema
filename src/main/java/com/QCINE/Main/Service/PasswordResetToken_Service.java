package com.QCINE.Main.Service;

import com.QCINE.Main.DTO.ChangePassword;
import com.QCINE.Main.Entity.PasswordResetToken;
import com.QCINE.Main.Entity.User_Entity;
import com.QCINE.Main.Repository.PasswordResetToken_Repository;
import com.QCINE.Main.Repository.User_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
public class PasswordResetToken_Service {
    @Autowired
    PasswordResetToken_Repository repository;

    @Autowired
    User_Repository user_repository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    SendGridMail_Service sendGridMailService;

    public String validateCodeResetPass(String email, String  code) {

        UUID uuid = UUID.randomUUID();
        String value = uuid.toString();
        try {
            User_Entity user = user_repository.findByEmail(email);
            if(ObjectUtils.isEmpty(user)){
                System.out.println("user not found");
                return "";
            }

            PasswordResetToken resetToken = repository.findByUser(user);
            if(ObjectUtils.isEmpty(resetToken)){
                System.out.println("reset token not found");
                return "";
            }
            boolean check = encoder.matches(code,resetToken.getCode());
            if(!check){
                System.out.println("token not found");
                return "";
            }

            Date timeN = new Date();
            if(timeN.getTime() > resetToken.getExpiryDate()){
                System.out.println("time out "+timeN.getTime() + " - "+resetToken.getExpiryDate());
                return "";
            }

            String tokenT = encoder.encode(value);
            resetToken.setExpiryDate((timeN.getTime()+(300000))); // 5 phút
            resetToken.setToken(tokenT);
            resetToken.setCode("");
            repository.save(resetToken);

            return tokenT;
        }catch (Exception e){
            System.out.println("Exception: "+e);
            return "";
        }
    }

    public boolean createCodeResetPass(String email){

        Random generator = new Random();
        int code = generator.nextInt((9999 - 1000) + 1) + 1000;
        try {
            User_Entity user = user_repository.findByEmail(email);
            if(ObjectUtils.isEmpty(user)){
                return false;
            }
            PasswordResetToken resetToken = repository.findByUser(user);
            if(ObjectUtils.isEmpty(resetToken)){
                resetToken = new PasswordResetToken();
            }
            resetToken.setUser(user);
            Date timeN = new Date();
            resetToken.setExpiryDate((timeN.getTime()+(90000))); // 1 phút 30s
            System.out.println("expiryDate "+ timeN.getTime()+" - "+ resetToken.getExpiryDate());
            resetToken.setCode(encoder.encode(String.valueOf(code)));
            repository.save(resetToken);

            sendGridMailService.sendTemplateMail("code",email,
                    "",String.valueOf(code),0,"","","","","","");

            return true;
        }catch (Exception e){
            System.out.println("Exception: "+e);
            return false;
        }
    }

    public boolean createNewPass(ChangePassword changePassword, String email){
        try {

            PasswordResetToken resetToken = repository.findByToken(changePassword.getOldpassword());
            if(ObjectUtils.isEmpty(resetToken)){
                return false;
            }

            if(!changePassword.getNewpassword().equals(changePassword.getConfirmpassword())){
                return false;
            }

            User_Entity user = user_repository.findByEmail(email);
            if(ObjectUtils.isEmpty(user)){
                return false;
            }

            Date timeN = new Date();
            if(timeN.getTime() > resetToken.getExpiryDate()){
                return false;
            }

            user.setPassword(encoder.encode(changePassword.getNewpassword()));
            user_repository.save(user);

            resetToken.setToken("");
            repository.save(resetToken);
            return true;
        }catch (Exception e){
            System.out.println("Exception: "+e);
            return false;
        }
    }

}

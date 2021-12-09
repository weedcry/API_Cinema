package com.QCINE.Main.CustomerController;

import com.QCINE.Main.DTO.Comment_DTO;
import com.QCINE.Main.DTO.ResultPageComment;
import com.QCINE.Main.Service.Comment_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer/comment")
public class Comment_Controller {
    @Autowired
    Comment_Service comment_service;

    @GetMapping("/page")
    public ResponseEntity getListPhimPagination(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "idphim", defaultValue = "0") int  idphim){
        ResultPageComment resultPage =  comment_service.getListCommentPagination(page,size,idphim);
        if(resultPage.getListResult().size() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(resultPage);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
    }

    @PostMapping("/check/{id}")
    public ResponseEntity<Object> checkCmt(@PathVariable int id) throws ParseException {
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }

        boolean check = comment_service.checkComment(id,username);
        if(check){
            return ResponseEntity.status(HttpStatus.OK).body("can comment");
        }
        return new ResponseEntity<Object>("can't comment",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("")
    public ResponseEntity<Object> createCmt(@RequestBody Comment_DTO comment){
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }

        Comment_DTO cmt = comment_service.createComment(comment,username);
        if(cmt.getIdPhim() != 0){
            return ResponseEntity.status(HttpStatus.CREATED).body(cmt);
        }
        return new ResponseEntity<Object>("failed",HttpStatus.BAD_REQUEST);
    }
}

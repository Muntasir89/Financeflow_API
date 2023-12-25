package com.monim.financeflow_api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.monim.financeflow_api.model.UserModel;
import com.monim.financeflow_api.services.AuthService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class AuthController {
    @Autowired
    AuthService authService;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to FinanceFlow";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("email") String email,@RequestParam("password") String password) {
        UserModel user = authService.login(email, password);
        if(user != null){
            return new ResponseEntity<UserModel>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>("Email or password is incorrect", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserModel user){
        String message = authService.signup(user);
        if(message.length()==0){
            return new ResponseEntity<UserModel>(user, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUserModel(@RequestParam("id") String id, @RequestParam("field") String field, @RequestParam("fieldValue") String fieldValue) {
        UserModel user = authService.updateUser(id, field, fieldValue);
        if(user!=null){
            return new ResponseEntity<UserModel>(user, HttpStatus.OK);
        }else
            return new ResponseEntity<String>("Account does not exist", HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/deleteAccount")
    public ResponseEntity<?> deleteAccount(@RequestParam("id") String id) {
        UserModel user =  authService.deleteAccount(id);
        if(user!=null)
            return new ResponseEntity<UserModel>(user, HttpStatus.OK);
        return new ResponseEntity<String>("Account does not exist", HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/user")
    public ResponseEntity<?> getUser(@RequestParam("id") String id){
        UserModel user = authService.getUser(id);
        if(user!=null)
            return new ResponseEntity<UserModel>(user, HttpStatus.OK);
        return new ResponseEntity<String>("Account does not exist", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/allUsers")
    public List<UserModel> getAllUsers() {
        return authService.getAllUsers();
    }
    
}

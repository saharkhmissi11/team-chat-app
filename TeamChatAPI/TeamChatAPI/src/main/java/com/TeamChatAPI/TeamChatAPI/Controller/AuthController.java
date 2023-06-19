package com.TeamChatAPI.TeamChatAPI.Controller;

import com.TeamChatAPI.TeamChatAPI.Model.Employee;
import com.TeamChatAPI.TeamChatAPI.Service.AuthService;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService){
        this.authService=authService;
    }
    
    @GetMapping("/login/{username}/{lastname}/{password}")
    public ResponseEntity<Boolean> isValidEmployee(@PathVariable("username") String username, @PathVariable("lastname") String lastname,@PathVariable("password") String password  ){
        boolean valid=authService.isValid(username,lastname,password);
        return ResponseEntity.ok(valid);
        
    }
    
}

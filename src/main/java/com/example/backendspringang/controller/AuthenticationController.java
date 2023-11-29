package com.example.backendspringang.controller;

import com.example.backendspringang.config.JwtUtils;
import com.example.backendspringang.config.UserDetailsServiceImpl;
import com.example.backendspringang.entity.User;
import com.example.backendspringang.models.JwtRequest;
import com.example.backendspringang.models.JwtResponse;
import com.example.backendspringang.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;

    private JwtUtils jwtUtils;

    private UserService userService;

    private UserDetailsServiceImpl userDetailsServiceImpl;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserService userService, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{

        try{
            User user= userService.getUser(jwtRequest.getUsername());
            if(user.isDeleted()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");

            }
            this.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
            UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
            String token = this.jwtUtils.generateToken(userDetails);

            System.out.println("The token is"+token);
            JwtResponse jwtResponse = new JwtResponse(token,user);
            return ResponseEntity.ok(jwtResponse);

        }catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User Not Found");
        } catch (DisabledException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User Disabled");
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }


    }

    private void authenticate(String userName,String password){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
    }

    @GetMapping("/logout")
    public void logout(){
        SecurityContextHolder.clearContext();
    }
}

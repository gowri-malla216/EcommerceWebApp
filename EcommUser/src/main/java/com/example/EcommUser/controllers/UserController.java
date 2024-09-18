package com.example.EcommUser.controllers;

import com.example.EcommUser.Dto.AuthResponseDto;
import com.example.EcommUser.Dto.LoginDto;
import com.example.EcommUser.Dto.RegisterDto;
import com.example.EcommUser.Dto.UserDto;
import com.example.EcommUser.Helpers.JwtUtil;
import com.example.EcommUser.Model.User;
import com.example.EcommUser.Model.UserRepo;
import com.example.EcommUser.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if(userRepo.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username already taken", HttpStatus.BAD_REQUEST);
        }
        UserDto createdUserDto = userService.createUser(registerDto);
        return new ResponseEntity<>("User with user name \""+createdUserDto.getUsername()+"\" is created.", HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        if(authentication.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtil.createToken(authentication);
            return new ResponseEntity<>(new AuthResponseDto(token, Boolean.TRUE), HttpStatus.OK);
        }
        return new ResponseEntity<>(new AuthResponseDto(null, Boolean.FALSE), HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("validateToken")
    public ResponseEntity<AuthResponseDto> validateToken(@RequestHeader("Authorization") String token){
        if(userService.validateToken(token)){
            return new ResponseEntity<>(new AuthResponseDto(token, Boolean.TRUE), HttpStatus.OK);
        }
        return new ResponseEntity<>(new AuthResponseDto(token, Boolean.FALSE), HttpStatus.BAD_REQUEST);
    }
}

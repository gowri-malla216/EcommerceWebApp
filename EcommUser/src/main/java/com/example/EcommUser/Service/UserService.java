package com.example.EcommUser.Service;

import com.example.EcommUser.Dto.UserDto;
import com.example.EcommUser.Dto.UserMapper;
import com.example.EcommUser.Dto.RegisterDto;
import com.example.EcommUser.Helpers.JwtUtil;
import com.example.EcommUser.Model.User;
import com.example.EcommUser.Model.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        UserDto userDto = userMapper.toUserDto(user);
        return new org.springframework.security.core.userdetails.User(userDto.getUsername(), userDto.getPassword(), userDto.getAuthorities());
    }

    public UserDto createUser(RegisterDto registerDto) {
        User user = userMapper.toUser(registerDto);
        user = userRepo.save(user);
        return userMapper.toUserDto(user);
    }

    public Boolean validateUser(String username, String password) {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return passwordEncoder.matches(password, user.getPassword());
    }

    public Boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }
}

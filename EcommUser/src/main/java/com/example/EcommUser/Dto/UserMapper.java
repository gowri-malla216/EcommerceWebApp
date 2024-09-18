package com.example.EcommUser.Dto;

import com.example.EcommUser.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setName(new Name(user.getFirstName(), user.getLastName()));
        userDto.setPhone(user.getPhone());
        userDto.set__v(user.isActive()?1:0);
        userDto.setRole(user.getRole());
        return userDto;
    }


    public User toUser(UserDto userDto) {
        User user = new User();
        user.setId(user.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getName().getFirstname());
        user.setLastName(userDto.getName().getLastname());
        user.setPhone(userDto.getPhone());
        user.setActive(userDto.get__v()==1);
        user.setRole(userDto.getRole());
        return user;
    }

    public User toUser(RegisterDto registerDto) {
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setPhone(registerDto.getPhone());
        user.setFirstName(registerDto.getName().getFirstname());
        user.setLastName(registerDto.getName().getLastname());
        user.setActive(true);
        user.setRole("ROLE_USER");
        return user;
    }
}

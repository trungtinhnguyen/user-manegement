package org.example.service.impl;

import org.example.constant.UserConstant;
import org.example.dto.MyUser;
import org.example.entity.RoleEntity;
import org.example.entity.UserEntity;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    List<GrantedAuthority> authorities = new ArrayList<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUser userDetails;
        Optional<UserEntity> userOptional = userRepository.findOneByUsernameAndStatus(username, UserConstant.ACTIVE);
        if (!userOptional.isPresent()) throw new UsernameNotFoundException("User "+username+" is not exists");

        for (RoleEntity role : userOptional.get().getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        }
        userDetails = new MyUser(username, userOptional.get().getPassword(), true,true,true,true,authorities);
        userDetails.setFullName(userOptional.get().getFullName());
        return userDetails;
    }
}

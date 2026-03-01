package com.personal_project.issue_tracking_system.security;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.personal_project.issue_tracking_system.entities.UsersEntity;
import com.personal_project.issue_tracking_system.repositories.UserRepository;
import com.personal_project.issue_tracking_system.services.MasterRoleService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MasterRoleService masterRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsersEntity> user = userRepository.findUserByUsername(username);

        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Username or Password (001)");
        }

        UsersEntity userEntity = user.get();

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(masterRoleService.getRoleCodeById(userEntity.getId_role()))
                .build();
        
        return userDetails;
    }
}

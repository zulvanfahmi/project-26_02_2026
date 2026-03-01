package com.personal_project.issue_tracking_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.personal_project.issue_tracking_system.Helper.SecurityUtils;
import com.personal_project.issue_tracking_system.entities.UsersEntity;
import com.personal_project.issue_tracking_system.repositories.UserRepository;

@Service
public class CurrentUserService {

    @Autowired
    private UserRepository userRepository;

    public UsersEntity getCurrentUser() {
        String username = SecurityUtils.getCurrentUsername();

        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}

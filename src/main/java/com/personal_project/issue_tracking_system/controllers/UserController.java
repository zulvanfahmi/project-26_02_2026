package com.personal_project.issue_tracking_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal_project.issue_tracking_system.dto.CreateUserDTO;
import com.personal_project.issue_tracking_system.dto.GetUserDTO;
import com.personal_project.issue_tracking_system.services.UserService;
import com.personal_project.issue_tracking_system.utils.ApiResponseWrapper;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/user")
    public ResponseEntity<ApiResponseWrapper<String>> createUser(
            @RequestBody CreateUserDTO createUserDto) {

        userService.createUser(createUserDto);
        return ResponseEntity.ok(
                new ApiResponseWrapper<>(
                        "success create user",
                        null));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/user/{idUser}")
    public ResponseEntity<ApiResponseWrapper<GetUserDTO>> getUser(
        @PathVariable Long idUser) {

        GetUserDTO user = userService.getUserById(idUser);

        return ResponseEntity.ok(
                new ApiResponseWrapper<>(
                        "success get user",
                        user));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/user/{idUser}")
    public ResponseEntity<ApiResponseWrapper<String>> updateUser(
            @PathVariable Long idUser,
            @RequestBody CreateUserDTO updateUserDto) {

        userService.updateUser(updateUserDto, idUser);
        return ResponseEntity.ok(
                new ApiResponseWrapper<>(
                        "success update user",
                        null));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/user")
    public ResponseEntity<ApiResponseWrapper<String>> deleteUser(
        @RequestParam Long deletedIdUser
    ) {

        userService.deleteUser(deletedIdUser);

        return ResponseEntity.ok(
                new ApiResponseWrapper<>(
                        "success delete user",
                        null));
    }

}

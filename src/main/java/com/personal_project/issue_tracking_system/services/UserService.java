package com.personal_project.issue_tracking_system.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.personal_project.issue_tracking_system.dto.CreateUserDTO;
import com.personal_project.issue_tracking_system.dto.GetUserDTO;
import com.personal_project.issue_tracking_system.entities.UsersEntity;
import com.personal_project.issue_tracking_system.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CurrentUserService currentUserService;

    public void createUser(CreateUserDTO createUserDTO) {
        UsersEntity newUser = new UsersEntity();
        newUser.setFullname(createUserDTO.getFullname());
        newUser.setUsername(createUserDTO.getUsername());
        newUser.setEmail(createUserDTO.getEmail());
        newUser.setPassword(createUserDTO.getPassword());
        newUser.setId_role(createUserDTO.getId_role());
        newUser.setCreated_by(createUserDTO.getCreated_by());
        newUser.setCreated_on(new Date());

        userRepository.save(newUser);
    }

    public GetUserDTO getUserById(Long idUser) {
        return userRepository.getUserById(idUser).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + idUser));

    }

    public void updateUser(CreateUserDTO updateUserDTO, Long idUser) {
        UsersEntity existingUser = userRepository.findUserById(idUser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + idUser));

        existingUser.setFullname(updateUserDTO.getFullname());
        existingUser.setUsername(updateUserDTO.getUsername());
        existingUser.setEmail(updateUserDTO.getEmail());
        existingUser.setPassword(updateUserDTO.getPassword());
        existingUser.setId_role(updateUserDTO.getId_role());

        UsersEntity currentUser = currentUserService.getCurrentUser();

        existingUser.setModified_by(currentUser.getId_user());
        existingUser.setModified_on(new Date());

        userRepository.save(existingUser);
    }

    public void deleteUser(Long deletedIdUser) {
        UsersEntity existingUser = userRepository.findUserById(deletedIdUser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + deletedIdUser));

        existingUser.setIs_delete(true);

        UsersEntity currentUser = currentUserService.getCurrentUser();

        existingUser.setDeleted_by(currentUser.getId_user());
        existingUser.setDeleted_on(new Date());

        userRepository.save(existingUser);
    }

}

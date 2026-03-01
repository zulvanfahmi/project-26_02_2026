package com.personal_project.issue_tracking_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {

    private String fullname;
    private String username;
    private String email;
    private String password;
    private Long id_role;
    private Long created_by;
}

package com.personal_project.issue_tracking_system.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "m_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersEntity extends BaseProperties {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @Column
    private String fullname;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Long id_role;
    
}

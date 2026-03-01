package com.personal_project.issue_tracking_system.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.personal_project.issue_tracking_system.dto.GetUserDTO;
import com.personal_project.issue_tracking_system.entities.UsersEntity;

public interface UserRepository extends JpaRepository<UsersEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT \r\n" + //
            "u.fullname,\r\n" + //
            "u.username,\r\n" + //
            "u.email,\r\n" + //
            "u.password,\r\n" + //
            "u.id_role,\r\n" + //
            "r.role_name\r\n" + //
            "FROM m_user u\r\n" + //
            "INNER JOIN master_roles r ON u.id_role = r.id_role\r\n" + //
            "WHERE u.id_user = :id_user AND u.is_delete = false AND r.is_delete = false")
    public Optional<GetUserDTO> getUserById(Long id_user);

    @Query(nativeQuery = true, value = "SELECT * FROM m_user WHERE id_user = :id_user AND is_delete = false")
    public Optional<UsersEntity> findUserById(Long id_user);

    @Query(nativeQuery = true, value = "SELECT * FROM m_user WHERE username = :username AND is_delete = false")
    public Optional<UsersEntity> findUserByUsername(String username);
    
}

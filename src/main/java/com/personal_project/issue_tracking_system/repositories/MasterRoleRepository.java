package com.personal_project.issue_tracking_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.personal_project.issue_tracking_system.entities.MasterRoleEntity;

public interface MasterRoleRepository extends JpaRepository<MasterRoleEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT role_code FROM master_roles WHERE id_role = :idRole and is_delete = false")
    public String getRoleCodeById(@Param("idRole") Long idRole);
    
}

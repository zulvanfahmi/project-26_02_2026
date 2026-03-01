package com.personal_project.issue_tracking_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal_project.issue_tracking_system.repositories.MasterRoleRepository;

@Service
public class MasterRoleService {

    @Autowired
    private MasterRoleRepository masterRoleRepository;

    public String getRoleCodeById(Long idRole) {
        return masterRoleRepository.getRoleCodeById(idRole);
    }
    
}

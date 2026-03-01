package com.personal_project.issue_tracking_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllTicketDTO {
    private Long id_ticket;
    private String title;
    private String description;

    private Long id_status;
    private String status_name;

    private Long id_priority;
    private String priority_name;

    private Long id_created_by;
    private String created_by_name;

    private Long id_assigned_to;
    private String assigned_to_name;
    
    private Long id_assigned_by;
    private String assigned_by_name;
}

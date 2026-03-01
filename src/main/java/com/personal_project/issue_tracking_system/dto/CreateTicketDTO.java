package com.personal_project.issue_tracking_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketDTO {
    
    private Long id_ticket;
    private String title;
    private String description;
    private Long id_status;
    private Long id_priority;
    private Long created_by;
    private Long assigned_by;
    private Long assigned_to;

}

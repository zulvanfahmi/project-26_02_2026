package com.personal_project.issue_tracking_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTicketDTO {
    
    private String title;
    private String description;
    private Long status_id;
    private Long priority_id;
    private Long assigned_to;
    private Long assigned_by;
    private Long modified_by;

}

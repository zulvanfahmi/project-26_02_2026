package com.personal_project.issue_tracking_system.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetTicketFilteredPaginationDTO {
    
    private List<GetAllTicketDTO> tickets;
    private Integer totalAllItems;
    private Integer totalPages;
    private Integer currentPagePosition;
    private Integer totalItemsInPage;
}

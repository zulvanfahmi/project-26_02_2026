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
@Table(name = "ticket_comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketCommentsEntity extends BaseProperties {
    
    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ticketComment;

    @Column(nullable = false)
    private Long id_ticket;

    @Column(nullable = false)
    private String comment;

}

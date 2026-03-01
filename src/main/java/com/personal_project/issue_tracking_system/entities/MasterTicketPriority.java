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
@Table(name = "master_ticket_priority")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MasterTicketPriority extends BaseProperties {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_priority;

    @Column
    private String priority_code;

    @Column
    private String priority_name;

}
package com.personal_project.issue_tracking_system.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.personal_project.issue_tracking_system.dto.GetAllTicketDTO;
import com.personal_project.issue_tracking_system.entities.TicketEntity;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

        @Query(nativeQuery = true, value = "SELECT \r\n" + //
                        "t.id_ticket,\r\n" + //
                        "t.title,\r\n" + //
                        "t.description,\r\n" + //
                        "t.id_status,\r\n" + //
                        "s.status_name AS status_name,\r\n" + //
                        "t.id_priority,\r\n" + //
                        "p.priority_name AS priority_name,\r\n" + //
                        "t.created_by,\r\n" + //
                        "u_creator.fullname AS created_by_name,\r\n" + //
                        "t.assigned_to,\r\n" + //
                        "u_assigned_to.fullname AS assigned_to_name,\r\n" + //
                        "t.assigned_by,\r\n" + //
                        "u_assigned_by.fullname AS assigned_by_name\r\n" + //
                        "FROM ticket t\r\n" + //
                        "INNER JOIN master_ticket_status s ON t.id_status = s.id_status\r\n" + //
                        "INNER JOIN master_ticket_priority p ON t.id_priority = p.id_priority\r\n" + //
                        "LEFT JOIN m_user u_creator ON t.created_by = u_creator.id_user \r\n" + //
                        "LEFT JOIN m_user u_assigned_to ON t.assigned_to = u_assigned_to.id_user \r\n" + //
                        "LEFT JOIN m_user u_assigned_by ON t.assigned_by = u_assigned_by.id_user \r\n" + //
                        "WHERE \r\n" + //
                        "t.is_delete = false\r\n" + //
                        "AND s.is_delete = false\r\n" + //
                        "AND p.is_delete = false\r\n" + //
                        "AND (u_creator.is_delete = false OR u_creator.id_user IS NULL)\r\n" + //
                        "AND (u_assigned_to.is_delete = false OR u_assigned_to.id_user IS NULL)\r\n" + //
                        "AND (u_assigned_by.is_delete = false OR u_assigned_by.id_user IS NULL);")
        public List<GetAllTicketDTO> getAllActiveTickets();

        @Query(nativeQuery = true, value = "SELECT\r\n" + //
                        "t.id_ticket,\r\n" + //
                        "t.title,\r\n" + //
                        "t.description,\r\n" + //
                        "t.id_status,\r\n" + //
                        "s.status_name AS status_name,\r\n" + //
                        "t.id_priority,\r\n" + //
                        "p.priority_name AS priority_name,\r\n" + //
                        "t.created_by,\r\n" + //
                        "u_creator.fullname AS created_by_name,\r\n" + //
                        "t.assigned_to,\r\n" + //
                        "u_assigned_to.fullname AS assigned_to_name,\r\n" + //
                        "t.assigned_by,\r\n" + //
                        "u_assigned_by.fullname AS assigned_by_name\r\n" + //
                        "FROM ticket t\r\n" + //
                        "INNER JOIN master_ticket_status s ON t.id_status = s.id_status\r\n" + //
                        "INNER JOIN master_ticket_priority p ON t.id_priority = p.id_priority\r\n" + //
                        "LEFT JOIN m_user u_creator ON t.created_by = u_creator.id_user \r\n" + //
                        "LEFT JOIN m_user u_assigned_to ON t.assigned_to = u_assigned_to.id_user \r\n" + //
                        "LEFT JOIN m_user u_assigned_by ON t.assigned_by = u_assigned_by.id_user \r\n" + //
                        "WHERE\r\n" + //
                        "t.is_delete = false\r\n" + //
                        "AND s.is_delete = false\r\n" + //
                        "AND p.is_delete = false\r\n" + //
                        "AND (u_creator.is_delete = false OR u_creator.id_user IS NULL)\r\n" + //
                        "AND (u_assigned_to.is_delete = false OR u_assigned_to.id_user IS NULL)\r\n" + //
                        "AND (u_assigned_by.is_delete = false OR u_assigned_by.id_user IS NULL)\r\n" + //
                        "AND (:statusId IS NULL OR t.id_status = :statusId)\r\n" + //
                        "AND (:priorityId IS NULL OR t.id_priority = :priorityId)\r\n" + //
                        "AND (:createdBy IS NULL OR t.created_by = :createdBy)\r\n" + //
                        "AND (:assignedTo IS NULL OR t.assigned_to = :assignedTo)\r\n" + //
                        "AND (:assignedBy IS NULL OR t.assigned_by = :assignedBy)\r\n" + //
                        "AND (:search IS NULL OR t.title ILIKE '%' || :search || '%' OR t.description ILIKE '%' || :search || '%')\r\n"
                        + //
                        "ORDER BY\r\n" + //
                        "CASE WHEN :sortBy = 'title' AND :sortDirection = 'asc' THEN t.title END ASC,\r\n" + //
                        "CASE WHEN :sortBy = 'title' AND :sortDirection = 'desc' THEN t.title END DESC,\r\n" + //
                        "CASE WHEN :sortBy = 'priority' AND :sortDirection = 'asc' THEN t.id_priority END ASC,\r\n" + //
                        "CASE WHEN :sortBy = 'priority' AND :sortDirection = 'desc' THEN t.id_priority END DESC,\r\n" + //
                        "CASE WHEN :sortBy = 'status' AND :sortDirection = 'asc' THEN t.id_status END ASC,\r\n" + //
                        "CASE WHEN :sortBy = 'status' AND :sortDirection = 'desc' THEN t.id_status END DESC,\r\n" + //
                        "t.id_ticket ASC\r\n" + //
                        "LIMIT :limit OFFSET :offset;")
        public List<GetAllTicketDTO> getTickets(
                        @Param("statusId") Long statusId,
                        @Param("priorityId") Long priorityId,
                        @Param("createdBy") Long createdBy,
                        @Param("assignedTo") Long assignedTo,
                        @Param("assignedBy") Long assignedBy,
                        @Param("sortBy") String sortBy,
                        @Param("sortDirection") String sortDirection,
                        @Param("limit") Integer limit,
                        @Param("offset") Integer offset,
                        @Param("search") String search);

        @Query(nativeQuery = true, value = "SELECT COUNT(t.id_ticket) " +
                        "FROM ticket t " +
                        "INNER JOIN master_ticket_status s ON t.id_status = s.id_status " +
                        "INNER JOIN master_ticket_priority p ON t.id_priority = p.id_priority " +
                        "LEFT JOIN m_user u_creator ON t.created_by = u_creator.id_user " +
                        "LEFT JOIN m_user u_assigned_to ON t.assigned_to = u_assigned_to.id_user " +
                        "LEFT JOIN m_user u_assigned_by ON t.assigned_by = u_assigned_by.id_user " +
                        "WHERE t.is_delete = false " +
                        "AND s.is_delete = false " +
                        "AND p.is_delete = false " +
                        "AND (u_creator.is_delete = false OR u_creator.id_user IS NULL) " +
                        "AND (u_assigned_to.is_delete = false OR u_assigned_to.id_user IS NULL) " +
                        "AND (u_assigned_by.is_delete = false OR u_assigned_by.id_user IS NULL) " +
                        "AND (:statusId IS NULL OR t.id_status = :statusId) " +
                        "AND (:priorityId IS NULL OR t.id_priority = :priorityId) " +
                        "AND (:createdBy IS NULL OR t.created_by = :createdBy) " +
                        "AND (:assignedTo IS NULL OR t.assigned_to = :assignedTo) " +
                        "AND (:assignedBy IS NULL OR t.assigned_by = :assignedBy) " +
                        "AND (:search IS NULL OR t.title ILIKE '%' || :search || '%' OR t.description ILIKE '%' || :search || '%')")
        public Integer getTicketsSize(
                        @Param("statusId") Long statusId,
                        @Param("priorityId") Long priorityId,
                        @Param("createdBy") Long createdBy,
                        @Param("assignedTo") Long assignedTo,
                        @Param("assignedBy") Long assignedBy,
                        @Param("search") String search);

        @Query(nativeQuery = true, value = "SELECT \r\n" + //
                        "t.id_ticket,\r\n" + //
                        "t.title,\r\n" + //
                        "t.description,\r\n" + //
                        "t.id_status,\r\n" + //
                        "s.status_name AS status_name,\r\n" + //
                        "t.id_priority,\r\n" + //
                        "p.priority_name AS priority_name,\r\n" + //
                        "t.created_by,\r\n" + //
                        "u_creator.fullname AS created_by_name,\r\n" + //
                        "t.assigned_to,\r\n" + //
                        "u_assigned_to.fullname AS assigned_to_name,\r\n" + //
                        "t.assigned_by,\r\n" + //
                        "u_assigned_by.fullname AS assigned_by_name\r\n" + //
                        "FROM ticket t\r\n" + //
                        "INNER JOIN master_ticket_status s ON t.id_status = s.id_status\r\n" + //
                        "INNER JOIN master_ticket_priority p ON t.id_priority = p.id_priority\r\n" + //
                        "LEFT JOIN m_user u_creator ON t.created_by = u_creator.id_user \r\n" + //
                        "LEFT JOIN m_user u_assigned_to ON t.assigned_to = u_assigned_to.id_user \r\n" + //
                        "LEFT JOIN m_user u_assigned_by ON t.assigned_by = u_assigned_by.id_user \r\n" + //
                        "WHERE \r\n" + //
                        "t.is_delete = false\r\n" + //
                        "AND s.is_delete = false\r\n" + //
                        "AND p.is_delete = false\r\n" + //
                        "AND (u_creator.is_delete = false OR u_creator.id_user IS NULL)\r\n" + //
                        "AND (u_assigned_to.is_delete = false OR u_assigned_to.id_user IS NULL)\r\n" + //
                        "AND (u_assigned_by.is_delete = false OR u_assigned_by.id_user IS NULL)\r\n" + //
                        "AND t.id_ticket = :idTicket")
        public Optional<GetAllTicketDTO> getTicketById(@Param("idTicket") Long idTicket);

        @Query(nativeQuery = true, value = "SELECT * FROM ticket WHERE id_ticket = :idTicket AND is_delete = false")
        public Optional<TicketEntity> findTicketById(@Param("idTicket") Long idTicket);

}

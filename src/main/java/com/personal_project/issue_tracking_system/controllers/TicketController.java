package com.personal_project.issue_tracking_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal_project.issue_tracking_system.dto.AssignTicketDTO;
import com.personal_project.issue_tracking_system.dto.ChangeTicketPriorityDTO;
import com.personal_project.issue_tracking_system.dto.ChangeTicketStatusDTO;
import com.personal_project.issue_tracking_system.dto.CreateTicketDTO;
import com.personal_project.issue_tracking_system.dto.GetAllTicketDTO;
import com.personal_project.issue_tracking_system.dto.GetTicketFilteredPaginationDTO;
import com.personal_project.issue_tracking_system.dto.UpdateTicketDTO;
import com.personal_project.issue_tracking_system.services.TicketService;
import com.personal_project.issue_tracking_system.utils.ApiResponseWrapper;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/ticket")
    public ResponseEntity<ApiResponseWrapper<String>> createTicket(
            @RequestBody CreateTicketDTO ticketDto) {
        ticketService.createTicket(ticketDto);
        return ResponseEntity.ok(
                new ApiResponseWrapper<>(
                        "success create ticket",
                        null));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/ticket/{idTicket}/assign")
    public ResponseEntity<ApiResponseWrapper<String>> assignTicket(
            @RequestBody AssignTicketDTO assignTicketDTO,
            @PathVariable Long idTicket) {
        ticketService.assignTicket(assignTicketDTO, idTicket);
        return ResponseEntity.ok(
                new ApiResponseWrapper<>(
                        "success assign ticket",
                        null));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping("/ticket/{idTicket}/status")
    public ResponseEntity<ApiResponseWrapper<String>> changeTicketStatus(
            @RequestBody ChangeTicketStatusDTO changeTicketStatusDTO,
            @PathVariable Long idTicket) {
        ticketService.changeTicketStatus(changeTicketStatusDTO, idTicket);
        return ResponseEntity.ok(
                new ApiResponseWrapper<>(
                        "success change ticket status",
                        null));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/ticket/{idTicket}/priority")
    public ResponseEntity<ApiResponseWrapper<String>> changeTicketPriority(
            @RequestBody ChangeTicketPriorityDTO changeTicketPriorityDTO,
            @PathVariable Long idTicket) {
        ticketService.changeTicketPriority(changeTicketPriorityDTO, idTicket);
        return ResponseEntity.ok(
                new ApiResponseWrapper<>(
                        "success change ticket priority",
                        null));
    }

    // @GetMapping("/all-tickets")
    // public ResponseEntity<ApiResponseWrapper<List<GetAllTicketDTO>>>
    // getAllTickets() {

    // List<GetAllTicketDTO> tickets = ticketService.getAllTickets();

    // return ResponseEntity.ok(
    // new ApiResponseWrapper<>(
    // "success get all tickets",
    // tickets));
    // }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/tickets")
    public ResponseEntity<ApiResponseWrapper<GetTicketFilteredPaginationDTO>> getTickets(
            @RequestParam(required = false) Long id_status,
            @RequestParam(required = false) Long id_priority,
            @RequestParam(required = false) Long created_by,
            @RequestParam(required = false) Long assigned_to,
            @RequestParam(required = false) Long assigned_by,
            @RequestParam(required = false) String sort_by,
            @RequestParam(required = false, defaultValue = "ASC") String sort_direction,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(required = false) String search) {

        GetTicketFilteredPaginationDTO response = ticketService.getTickets(
                id_status,
                id_priority,
                created_by,
                assigned_to,
                assigned_by,
                sort_by,
                sort_direction,
                limit,
                offset,
                search);

        return ResponseEntity.ok(
                new ApiResponseWrapper<>(
                        "success get tickets with filters",
                        response));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/ticket/{idTicket}")
    public ResponseEntity<ApiResponseWrapper<GetAllTicketDTO>> getTicketById(
            @PathVariable Long idTicket) {
        GetAllTicketDTO ticket = ticketService.getTicketById(idTicket);
        return ResponseEntity.ok(
                new ApiResponseWrapper<>(
                        "success get ticket by id",
                        ticket));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/ticket/{idTicket}/update")
    public ResponseEntity<ApiResponseWrapper<String>> updateTicket(
            @RequestBody UpdateTicketDTO ticketDto,
            @PathVariable Long idTicket) {
        ticketService.updateTicket(idTicket, ticketDto);
        return ResponseEntity.ok(
                new ApiResponseWrapper<>(
                        "success update ticket",
                        null));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/ticket/{idTicket}/delete")
    public ResponseEntity<ApiResponseWrapper<String>> deleteTicket(
            @PathVariable Long idTicket) {
        ticketService.deleteTicket(idTicket);
        return ResponseEntity.ok(
                new ApiResponseWrapper<>(
                        "success delete ticket",
                        null));
    }

}

package com.personal_project.issue_tracking_system.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.personal_project.issue_tracking_system.dto.AssignTicketDTO;
import com.personal_project.issue_tracking_system.dto.ChangeTicketPriorityDTO;
import com.personal_project.issue_tracking_system.dto.ChangeTicketStatusDTO;
import com.personal_project.issue_tracking_system.dto.CreateTicketDTO;
import com.personal_project.issue_tracking_system.dto.GetAllTicketDTO;
import com.personal_project.issue_tracking_system.dto.GetTicketFilteredPaginationDTO;
import com.personal_project.issue_tracking_system.dto.UpdateTicketDTO;
import com.personal_project.issue_tracking_system.entities.TicketEntity;
import com.personal_project.issue_tracking_system.entities.UsersEntity;
import com.personal_project.issue_tracking_system.repositories.TicketRepository;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CurrentUserService currentUserService;

    public void createTicket(CreateTicketDTO createTicketDTO) {

        TicketEntity newTicket = new TicketEntity();
        newTicket.setTitle(createTicketDTO.getTitle());
        newTicket.setDescription(createTicketDTO.getDescription());
        newTicket.setId_status(createTicketDTO.getId_status());
        newTicket.setId_priority(createTicketDTO.getId_priority());
        newTicket.setAssigned_to(createTicketDTO.getAssigned_to());
        newTicket.setAssigned_by(createTicketDTO.getAssigned_by());

        UsersEntity currentUser = currentUserService.getCurrentUser();
        newTicket.setCreated_by(currentUser.getId_user());
        newTicket.setCreated_on(new Date());

        ticketRepository.save(newTicket);
    }

    public void assignTicket(AssignTicketDTO assignTicketDTO, Long idTicket) {
        TicketEntity existingTicket = ticketRepository.findTicketById(idTicket)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Ticket not found with id: " + idTicket));

        existingTicket.setAssigned_to(assignTicketDTO.getAssigned_to());

        UsersEntity currentUser = currentUserService.getCurrentUser();

        existingTicket.setAssigned_by(currentUser.getId_user());
        existingTicket.setModified_by(currentUser.getId_user());
        existingTicket.setModified_on(new Date());

        ticketRepository.save(existingTicket);
    }

    public void changeTicketStatus(ChangeTicketStatusDTO changeTicketStatusDTO, Long idTicket) {
        TicketEntity existingTicket = ticketRepository.findTicketById(idTicket)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Ticket not found with id: " + idTicket));

        existingTicket.setId_status(changeTicketStatusDTO.getNew_id_status());

        UsersEntity currentUser = currentUserService.getCurrentUser();

        existingTicket.setModified_by(currentUser.getId_user());
        existingTicket.setModified_on(new Date());

        ticketRepository.save(existingTicket);
    }

    public void changeTicketPriority(ChangeTicketPriorityDTO changeTicketPriorityDTO, Long idTicket) {
        TicketEntity existingTicket = ticketRepository.findTicketById(idTicket)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Ticket not found with id: " + idTicket));

        existingTicket.setId_priority(changeTicketPriorityDTO.getNew_id_priority());

        UsersEntity currentUser = currentUserService.getCurrentUser();

        existingTicket.setModified_by(currentUser.getId_user());
        existingTicket.setModified_on(new Date());

        ticketRepository.save(existingTicket);
    }

    public List<GetAllTicketDTO> getAllTickets() {
        return ticketRepository.getAllActiveTickets();
    }

    public GetTicketFilteredPaginationDTO getTickets(
            Long statusId,
            Long priorityId,
            Long createdBy,
            Long assignedTo,
            Long assignedBy,
            String sortBy,
            String sortDirection,
            Integer limit,
            Integer offset,
            String search) {
        List<GetAllTicketDTO> tickets = ticketRepository.getTickets(
                statusId,
                priorityId,
                createdBy,
                assignedTo,
                assignedBy,
                sortBy.toLowerCase(),
                sortDirection.toLowerCase(),
                limit,
                offset,
                search);

        Integer totalItems = ticketRepository.getTicketsSize(
                statusId,
                priorityId,
                createdBy,
                assignedTo,
                assignedBy,
                search);

        GetTicketFilteredPaginationDTO response = new GetTicketFilteredPaginationDTO();
        response.setTickets(tickets);
        response.setTotalAllItems(totalItems);
        response.setTotalPages((int) Math.ceil((double) response.getTotalAllItems() / limit));
        response.setCurrentPagePosition(offset / limit + 1);
        response.setTotalItemsInPage(limit > tickets.size() ? tickets.size() : limit);
        return response;
    }

    public GetAllTicketDTO getTicketById(Long idTicket) {
        return ticketRepository.getTicketById(idTicket)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Ticket not found with id: " + idTicket));
    }

    public void updateTicket(Long idTicket, UpdateTicketDTO ticketDTO) {
        TicketEntity existingTicket = ticketRepository.findTicketById(idTicket)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Ticket not found with id: " + idTicket));

        existingTicket.setTitle(ticketDTO.getTitle());
        existingTicket.setDescription(ticketDTO.getDescription());
        existingTicket.setId_status(ticketDTO.getStatus_id());
        existingTicket.setId_priority(ticketDTO.getPriority_id());
        existingTicket.setAssigned_to(ticketDTO.getAssigned_to());

        UsersEntity currentUser = currentUserService.getCurrentUser();

        existingTicket.setModified_by(currentUser.getId_user());
        existingTicket.setModified_on(new Date());

        ticketRepository.save(existingTicket);
    }

    public void deleteTicket(Long idTicket) {
        TicketEntity existingTicket = ticketRepository.findTicketById(idTicket)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Ticket not found with id: " + idTicket));

        existingTicket.setIs_delete(true);
        existingTicket.setDeleted_on(new Date());

        UsersEntity currentUser = currentUserService.getCurrentUser();

        existingTicket.setDeleted_by(currentUser.getId_user());

        ticketRepository.save(existingTicket);
    }
}

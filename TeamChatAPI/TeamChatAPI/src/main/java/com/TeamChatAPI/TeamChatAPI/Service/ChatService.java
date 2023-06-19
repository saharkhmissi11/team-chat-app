package com.TeamChatAPI.TeamChatAPI.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.TeamChatAPI.TeamChatAPI.Model.Employee;
import com.TeamChatAPI.TeamChatAPI.Model.Message;
import com.TeamChatAPI.TeamChatAPI.Model.Team;
import com.TeamChatAPI.TeamChatAPI.Repository.EmployeeRepo;
import com.TeamChatAPI.TeamChatAPI.Repository.MessageRepo;
import com.TeamChatAPI.TeamChatAPI.Repository.TeamRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ChatService {
    private final MessageRepo messageRepo;
    private final TeamRepo teamRepo;
    private final EmployeeRepo employeeRepo;
    @Autowired
    public ChatService(MessageRepo messageRepo,TeamRepo teamRepo,EmployeeRepo employeeRepo){
        this.messageRepo=messageRepo;
        this.teamRepo=teamRepo;
        this.employeeRepo=employeeRepo;
    }
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    // Add message
    public Message addMessage(Long teamId,Long employeeId,Message message){
        message.setSentAt(LocalDateTime.now());
        Team team = teamRepo.findById(teamId).orElseThrow(() -> new EntityNotFoundException("Team not found"));
        Employee employee = employeeRepo.findById(employeeId).orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        message.setTeam(team.getName());
        message.setReceiverId(team.getId());
        message.setSenderId(employeeId);
        message.setSenderName(employee.getFirstname()+" "+employee.getLastname());
        team.addMessage(message);
        return messageRepo.save(message);
       
    }
    //Get Team Messages
    public List<Message> findMessagesByTeam(Long id){
        Team team=teamRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Team not found"));
         return team.getMessages();
     }
     //get my sent and received messages
    


    
}

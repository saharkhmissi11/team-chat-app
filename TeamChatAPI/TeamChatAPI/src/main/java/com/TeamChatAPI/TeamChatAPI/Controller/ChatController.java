package com.TeamChatAPI.TeamChatAPI.Controller;

import java.security.Principal;
import java.util.List;

import com.TeamChatAPI.TeamChatAPI.Model.Employee;
import com.TeamChatAPI.TeamChatAPI.Model.Message;
import com.TeamChatAPI.TeamChatAPI.Service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;
    @Autowired 
    private SimpMessagingTemplate simpMessagingTemplate;
    public ChatController(ChatService chatService){
        this.chatService=chatService;
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<List<Message>> getAllMessages(@PathVariable Long id){
        List<Message> messages=chatService.findMessagesByTeam(id);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
    @PostMapping("/send/{teamId}/{employeeId}")
    //@MessageMapping("/chat.send/{id}")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message, @PathVariable Long teamId, @PathVariable Long employeeId) {
        chatService.addMessage(teamId,employeeId,message);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @MessageMapping("/chat")
    @SendTo("/topic/public")
    public Message addUser(Message message) {
        return message;
    }
    
    
}

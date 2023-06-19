package com.TeamChatAPI.TeamChatAPI.Repository;

import com.TeamChatAPI.TeamChatAPI.Model.Message;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message,Long>{
    
    
}

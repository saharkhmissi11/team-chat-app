package com.TeamChatAPI.TeamChatAPI.Repository;

import com.TeamChatAPI.TeamChatAPI.Model.Employee;
import com.TeamChatAPI.TeamChatAPI.Model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepo extends JpaRepository<Team,Long> {
    void deleteTeamById(Long id);

    Optional<Team> findTeamById(Long id);

    Optional<Team> findTeamByName(String team);
}

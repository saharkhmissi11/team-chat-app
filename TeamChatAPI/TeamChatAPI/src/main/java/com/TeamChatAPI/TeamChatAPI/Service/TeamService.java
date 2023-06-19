package com.TeamChatAPI.TeamChatAPI.Service;

import com.TeamChatAPI.TeamChatAPI.Model.Employee;
import com.TeamChatAPI.TeamChatAPI.Model.Team;
import com.TeamChatAPI.TeamChatAPI.Repository.TeamRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@Transactional
public class TeamService {
    private final TeamRepo teamRepo;
    @Autowired
    public TeamService(TeamRepo teamRepo){this.teamRepo=teamRepo;}
    public Team addTeam(Team team){
        return teamRepo.save(team);
    }
    public List<Team> findAllTeams(){
        return teamRepo.findAll();
    }
    public void deleteTeamById(Long id){
        teamRepo.deleteTeamById(id);
    }
    public Team addEmployeeToTeam(Long teamId, Employee employee) {
        Team team = teamRepo.findById(teamId).orElseThrow(() -> new EntityNotFoundException("Team not found"));
        team.addEmployee(employee);
        employee.setTeam(team.getName());
        return teamRepo.save(team);
    }
    

}

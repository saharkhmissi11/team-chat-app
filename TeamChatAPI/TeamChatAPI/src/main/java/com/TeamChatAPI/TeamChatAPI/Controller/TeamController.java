package com.TeamChatAPI.TeamChatAPI.Controller;

import com.TeamChatAPI.TeamChatAPI.Model.Employee;
import com.TeamChatAPI.TeamChatAPI.Model.Team;
import com.TeamChatAPI.TeamChatAPI.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;
    @Autowired
    public TeamController(TeamService teamService){this.teamService=teamService;}
    @GetMapping("/all")
    public ResponseEntity<List<Team>> getAllTeams(){
        List<Team> teams=teamService.findAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Team> addTeam(@RequestBody Team team){
        Team newTeam =teamService.addTeam(team);
        return new ResponseEntity<>(newTeam,HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable("id") Long id){
        teamService.deleteTeamById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping ("/{teamId}/employees")
    public ResponseEntity<Team> addEmployeeToTeam(@PathVariable Long teamId, @RequestBody Employee employee) {
        Team newTeam = teamService.addEmployeeToTeam(teamId, employee);
        return new ResponseEntity<>(newTeam,HttpStatus.OK);
    }

}

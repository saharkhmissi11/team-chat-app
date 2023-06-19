package com.TeamChatAPI.TeamChatAPI.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String name;
    private String manager;
    private String department;
    @OneToMany(targetEntity = Employee.class, cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="team_id",referencedColumnName = ("id"))
    private List<Employee> employees = new ArrayList<>();
    
    @OneToMany(targetEntity = Message.class, cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();
    // Constructors, getters and setters

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setTeam(this.name);
    }
    public void addMessage(Message message) {
        messages.add(message);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.setTeam(null);
    }

}

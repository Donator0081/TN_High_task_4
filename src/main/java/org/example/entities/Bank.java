package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(schema = "high_task")
public class Bank {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
}

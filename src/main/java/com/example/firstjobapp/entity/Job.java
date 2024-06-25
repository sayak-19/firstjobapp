package com.example.firstjobapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter@Setter@Builder@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "job_table")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long id;
    private String title;
    private String description;
    private String location;

    @ManyToOne
    private Company company;
}

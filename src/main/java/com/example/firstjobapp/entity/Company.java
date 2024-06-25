package com.example.firstjobapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "company")
@Getter@Setter@AllArgsConstructor@NoArgsConstructor@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;
    private String description;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;

    //private List<Review> reviews;


}

package com.example.firstjobapp.dto;

import lombok.*;

import java.util.List;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@Builder
public class CompanyDTO {

    private Long id;
    private String description;
    private String name;
    private List<JobDTO> jobs;
    //private List<Long> jobsIds;
}

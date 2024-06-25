package com.example.firstjobapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class JobDTO {

    private Long id;
    private String title;
    private String description;
    private String location;
    private CompanyDTO company;
   // private Long companyId;
}

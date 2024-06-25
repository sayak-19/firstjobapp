package com.example.firstjobapp.utility;

import com.example.firstjobapp.dto.CompanyDTO;
import com.example.firstjobapp.dto.JobDTO;
import com.example.firstjobapp.entity.Company;
import com.example.firstjobapp.entity.Job;
import com.example.firstjobapp.service.CompanyService;
import com.example.firstjobapp.service.CompanyServiceImpl;
import com.example.firstjobapp.service.JobService;
import com.example.firstjobapp.service.JobServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class EntityDTOConverter {

    public static CompanyDTO convertToCompanyDTO(Company company, Boolean flag) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(company.getId());
        companyDTO.setDescription(company.getDescription());
        companyDTO.setName(company.getName());
        if(company.getJobs() == null || company.getJobs().isEmpty() || flag){
            companyDTO.setJobs(Collections.emptyList());
        }else {
            List<JobDTO> jobDTOList = new ArrayList<>();
            for (Job job : company.getJobs()) {
                jobDTOList.add(convertToJobDTO(job));
            }
            companyDTO.setJobs(jobDTOList);
        }
        return companyDTO;
    }

    public static JobDTO convertToJobDTO(Job job) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setCompany(convertToCompanyDTO(job.getCompany(), true));
        return jobDTO;
    }

    public static Company convertToCompanyEntity(CompanyDTO companyDTO) {
        Company company = new Company();
        company.setId(companyDTO.getId());
        company.setDescription(companyDTO.getDescription());
        company.setName(companyDTO.getName());
        if(companyDTO.getJobs() == null || companyDTO.getJobs().isEmpty()){
            company.setJobs(null);
        }
        else {
            List<Job> jobList = new ArrayList<>();
            for (JobDTO jobDTO : companyDTO.getJobs()) {
                jobList.add(convertToJobEntity(jobDTO));
            }
            company.setJobs(jobList);
        }
        return company;
    }

    public static Job convertToJobEntity(JobDTO jobDTO) {
        Job job = new Job();
        job.setId(jobDTO.getId());
        job.setTitle(jobDTO.getTitle());
        job.setDescription(jobDTO.getDescription());
        job.setLocation(jobDTO.getLocation());
        job.setCompany(convertToCompanyEntity(jobDTO.getCompany()));
        return job;
    }

}

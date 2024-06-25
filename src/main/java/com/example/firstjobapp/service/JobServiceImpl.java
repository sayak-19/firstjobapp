package com.example.firstjobapp.service;

import com.example.firstjobapp.dto.JobDTO;
import com.example.firstjobapp.entity.Job;
import com.example.firstjobapp.repository.CompanyRepository;
import com.example.firstjobapp.repository.JobRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import  com.example.firstjobapp.utility.EntityDTOConverter;


@RequiredArgsConstructor
@Service
public class JobServiceImpl implements JobService{

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    @Override
    public List<JobDTO> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        if(jobs.isEmpty())
            return Collections.emptyList();
        List<JobDTO> jobDTOS = new ArrayList<>();
        for (Job job : jobs){
            jobDTOS.add(EntityDTOConverter.convertToJobDTO(job));
        }
        return jobDTOS;
    }

    @Override
    public JobDTO getJobById(String id)throws Exception {
        Job job = jobRepository.findById(Long.parseLong(id))
                .orElseThrow(()-> new EntityNotFoundException("Invalid Job Id"));
        return EntityDTOConverter.convertToJobDTO(job);
    }

    @Override
    public JobDTO addJob(JobDTO jobDTO) throws Exception {
        Job job = EntityDTOConverter.convertToJobEntity(jobDTO);
        job = jobRepository.save(job);
        return EntityDTOConverter.convertToJobDTO(job);
    }

    @Override
    public void deleteJob(String id)throws Exception {
        Job job = jobRepository.findById(Long.parseLong(id))
                .orElseThrow(()-> new EntityNotFoundException("Invalid Id provided, could not delete job"));
        jobRepository.delete(job);
    }

    @Override
    public JobDTO updateJob(JobDTO jobDTO)throws Exception {
        Optional<Job> jobOptional = jobRepository.findById(jobDTO.getId());
                jobOptional.orElseThrow(()-> new EntityNotFoundException("Invalid Id provided, could not update job"));
        Job job = null;
        if(jobOptional.isPresent()) {
            job = jobOptional.get();
            job.setTitle(jobDTO.getTitle());
            job.setDescription(jobDTO.getDescription());
            /*job.setMaxSalary(jobDTO.getMaxSalary());
            job.setMinSalary(jobDTO.getMinSalary());*/
            job.setLocation(jobDTO.getLocation());
            job.setCompany(companyRepository.findById(jobDTO.getCompany().getId()).orElseThrow(
                    ()-> new EntityNotFoundException("Invalid Company Id provided, could not update job")
            ));

            job = jobRepository.save(job);
        }
        return EntityDTOConverter.convertToJobDTO(job);
    }

}

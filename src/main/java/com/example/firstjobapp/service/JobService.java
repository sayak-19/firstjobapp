package com.example.firstjobapp.service;

import com.example.firstjobapp.dto.JobDTO;

import java.util.List;

public interface JobService {

    public List<JobDTO> getAllJobs()throws Exception;

    public JobDTO getJobById(String id)throws Exception;

    public JobDTO addJob(JobDTO job)throws Exception;

    public void deleteJob(String id)throws Exception;

    public JobDTO updateJob(JobDTO jobDTO)throws Exception;
}

package com.example.firstjobapp.controller;

import com.example.firstjobapp.dto.JobDTO;
import com.example.firstjobapp.service.JobServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobServiceImpl jobService;

    @GetMapping
    public ResponseEntity<List<JobDTO>> getAllJobs(){
        List<JobDTO> jobDTOS = jobService.getAllJobs();
        return new ResponseEntity<>(jobDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable String id)throws Exception{
        JobDTO jobDTO = jobService.getJobById(id);
        return new ResponseEntity<>(jobDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JobDTO> addJob(@RequestBody JobDTO job) throws Exception {
        return new ResponseEntity<>(jobService.addJob(job), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable String id)throws Exception{
        jobService.deleteJob(id);
        return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);}

    @PutMapping
    public ResponseEntity<JobDTO> updateJob(@RequestBody JobDTO jobDTO)throws Exception{
        jobDTO = jobService.updateJob(jobDTO);
        return new ResponseEntity<>(jobDTO, HttpStatus.CREATED);}

}

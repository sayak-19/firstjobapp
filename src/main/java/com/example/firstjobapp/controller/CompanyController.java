package com.example.firstjobapp.controller;

import com.example.firstjobapp.dto.CompanyDTO;
import com.example.firstjobapp.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompanies(){
        List<CompanyDTO> companyDTOS = companyService.getAllCompanies();
        return new ResponseEntity<>(companyDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable("id") String id) throws Exception {
        CompanyDTO companyDTO = companyService.getCompanyById(id);
        return new ResponseEntity<>(companyDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> addCompany(@RequestBody CompanyDTO companyDTO) throws Exception {
        companyDTO = companyService.addCompany(companyDTO);
        return new ResponseEntity<>(companyDTO,HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable("id") String id, @RequestBody CompanyDTO companyDTO) throws Exception {
        companyDTO = companyService.updateCompany(id, companyDTO);
        return new ResponseEntity<>(companyDTO,HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable("id") String id) throws Exception {
        companyService.deleteCompany(id);
        return new ResponseEntity<>("Company with id: "+id+" deleted successfully", HttpStatus.OK);
    }

}

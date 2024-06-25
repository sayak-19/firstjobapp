package com.example.firstjobapp.service;

import com.example.firstjobapp.dto.CompanyDTO;
import com.example.firstjobapp.entity.Company;
import com.example.firstjobapp.repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import  com.example.firstjobapp.utility.EntityDTOConverter;

@Service
@RequiredArgsConstructor
@Eager
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;

    @Override
    public List<CompanyDTO> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        if(companies.isEmpty())
            return Collections.emptyList();
        List<CompanyDTO> companyDTOS = new ArrayList<>();
        for (Company company : companies){
            companyDTOS.add(EntityDTOConverter.convertToCompanyDTO(company, false));
        }
        return companyDTOS;
    }

    @Override
    public CompanyDTO getCompanyById(String id) throws Exception {
        System.out.println("CompanyServiceImpl.getCompanyById "+id);
        Company company = companyRepository.findById(Long.parseLong(id))
                .orElseThrow(()-> new EntityNotFoundException("Invalid Company Id"));
        return EntityDTOConverter.convertToCompanyDTO(company, false);
    }

    @Override
    public CompanyDTO addCompany(CompanyDTO companyDTO) throws Exception {
        Company company = EntityDTOConverter.convertToCompanyEntity(companyDTO);
        company = companyRepository.save(company);
        return EntityDTOConverter.convertToCompanyDTO(company, false);
    }

    @Override
    public void deleteCompany(String id) throws Exception {
        Company company = companyRepository.findById(Long.parseLong(id))
                .orElseThrow(()-> new EntityNotFoundException("Invalid Id provided, could not delete company"));
        companyRepository.delete(company);
    }

    @Override
    public CompanyDTO updateCompany(String id, CompanyDTO companyDTO) throws Exception {
        Company company = companyRepository.findById(Long.parseLong(id))
                .orElseThrow(()-> new EntityNotFoundException("Invalid Company Id provided"));

        company.setName(companyDTO.getName());
        company.setDescription(companyDTO.getDescription());

        company = companyRepository.save(company);
        return EntityDTOConverter.convertToCompanyDTO(company,false);
    }


}

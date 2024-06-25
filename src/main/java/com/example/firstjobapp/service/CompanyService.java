package com.example.firstjobapp.service;


import com.example.firstjobapp.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {

    public List<CompanyDTO> getAllCompanies();

    public CompanyDTO getCompanyById(String id)throws Exception;

    public CompanyDTO addCompany(CompanyDTO companyDTO)throws Exception;

    public void deleteCompany(String id)throws Exception;

    public CompanyDTO updateCompany(String id, CompanyDTO companyDTO)throws Exception;
}

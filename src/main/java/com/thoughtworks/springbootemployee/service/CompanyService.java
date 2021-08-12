package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;


    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.getAllCompanies();
    }

    public Company getCompanyById(Integer companyId) {
        return companyRepository.getCompanyById(companyId);
    }

    public List<Employee> getEmployeesByCompany(Integer companyId) {
        return companyRepository.getEmployeesByCompany(companyId);
    }

    public List<Company> getEmployeebyPage(Integer page, Integer pageSize) {
        return companyRepository.getCompanyByPage(page,pageSize);
    }

    public Company addNewCompany(Company company) {
        return companyRepository.addNewCompany(company);
    }

    public Company updateCompanyInfo(Integer companyId, Company companyToBeUpdated) {
        return companyRepository.updateCompanyInfo(companyId,companyToBeUpdated);
    }

    public void deleteCompany(Integer companyId) {
        companyRepository.deleteCompany(companyId);
    }
}

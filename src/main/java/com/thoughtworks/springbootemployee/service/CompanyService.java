package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.RetiringCompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private RetiringCompanyRepository retiringCompanyRepository;


    public CompanyService(RetiringCompanyRepository retiringCompanyRepository) {
        this.retiringCompanyRepository = retiringCompanyRepository;
    }

    public List<Company> getAllCompanies() {
        return retiringCompanyRepository.getAllCompanies();
    }

    public Company getCompanyById(Integer companyId) {
        return retiringCompanyRepository.getCompanyById(companyId);
    }

    public List<Employee> getEmployeesByCompany(Integer companyId) {
        return retiringCompanyRepository.getEmployeesByCompany(companyId);
    }

    public List<Company> getCompanyByPage(Integer page, Integer pageSize) {
        return retiringCompanyRepository.getCompanyByPage(page,pageSize);
    }

    public Company addNewCompany(Company company) {
        return retiringCompanyRepository.addNewCompany(company);
    }

    public Company updateCompanyInfo(Integer companyId, Company companyToBeUpdated) {
        return retiringCompanyRepository.updateCompanyInfo(companyId,companyToBeUpdated);
    }

    public void deleteCompany(Integer companyId) {
        retiringCompanyRepository.deleteCompany(companyId);
    }
}

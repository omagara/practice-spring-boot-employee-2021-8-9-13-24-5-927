package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.RetiringCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private RetiringCompanyRepository retiringCompanyRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public CompanyService(RetiringCompanyRepository retiringCompanyRepository) {
        this.retiringCompanyRepository = retiringCompanyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyById(Integer companyId) {
        return companyRepository.findById(companyId);
    }

    public List<Employee> getEmployeesByCompany(Integer companyId) {
        return employeeRepository.findByCompanyId(companyId);
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

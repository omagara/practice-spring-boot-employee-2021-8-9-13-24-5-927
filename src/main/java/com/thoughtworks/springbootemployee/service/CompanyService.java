package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.RetiringCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Company getCompanyById(Integer companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(()->new CompanyNotFoundException("Company ID not found."));
    }

    public List<Employee> getEmployeesByCompany(Integer companyId) {
        return employeeRepository.findByCompanyId(companyId);
    }

    public List<Company> getCompanyByPage(Integer page, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(page - 1, pageSize)).getContent();
    }

    public Company addNewCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompanyInfo(Integer companyId, Company companyToBeUpdated) {
        Company updatedCompany = companyRepository.findById(companyId)
                .map(company -> updateCompany(company, companyToBeUpdated))
                .orElseThrow(() -> new CompanyNotFoundException("Company ID not found"));
        return companyRepository.save(updatedCompany);
    }

    private Company updateCompany(Company company, Company companyToBeUpdated) {
        if (companyToBeUpdated.getCompanyName() != null) {
            company.setCompanyName(companyToBeUpdated.getCompanyName());
        }
        return company;
    }

    public void deleteCompany(Integer companyId) {
        companyRepository.deleteById(companyId);
    }
}

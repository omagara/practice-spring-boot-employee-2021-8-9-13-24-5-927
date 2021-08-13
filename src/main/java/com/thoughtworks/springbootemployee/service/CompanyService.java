package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.RetiringCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
        return companyRepository.findAll(PageRequest.of(page - 1, pageSize)).getContent();
    }

    public Company addNewCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompanyInfo(Integer companyId, Company companyToBeUpdated) {
        return companyRepository.findById(companyId)
                .map(company -> updateCompany(company, companyToBeUpdated))
                .orElse(null);
    }

    private Company updateCompany(Company company, Company companyToBeUpdated) {
        if (companyToBeUpdated.getCompanyName() != null) {
            company.setCompanyName(companyToBeUpdated.getCompanyName());
        }
        return companyRepository.save(company);
    }

    public void deleteCompany(Integer companyId) {
        companyRepository.deleteById(companyId);
    }
}

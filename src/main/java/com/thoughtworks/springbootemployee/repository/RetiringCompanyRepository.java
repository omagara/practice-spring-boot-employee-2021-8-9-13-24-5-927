package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RetiringCompanyRepository {
    private List<Company> companies = new ArrayList<>();
    public RetiringCompanyRepository(){
        List<Employee> firstEmployeesList = new ArrayList<>();
    }

    public List<Company> getAllCompanies() {
        return companies;
    }

    public Company getCompanyById(Integer companyId) {
        return companies.stream()
                .filter(company -> company.getId().equals(companyId))
                .findFirst()
                .orElse(null);
    }

    public List<Employee> getEmployeesByCompany(Integer companyId) {
        return companies.stream()
                .filter(company -> company.getId().equals(companyId))
                .findFirst()
                .get().getEmployees();
    }

    public List<Company> getCompanyByPage(Integer page, Integer pageSize) {
        return companies.stream()
                .skip((page -1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public Company addNewCompany(Company company) {
        companies.add(company);
        return company;
    }

    public Company updateCompanyInfo(Integer companyId, Company companyToBeUpdated) {
        return companies.stream()
                .filter(company -> company.getId().equals(companyId))
                .findFirst()
                .map(company -> updateCompany(company, companyToBeUpdated))
                .get();
    }
    private Company updateCompany(Company company, Company companyToBeUpdated) {
        if (companyToBeUpdated.getCompanyName() != null){
            company.setCompanyName(companyToBeUpdated.getCompanyName());
        }
        return company;
    }

    public void deleteCompany(Integer companyId) {
        companies.stream()
                .filter(company -> company.getId().equals(companyId))
                .findFirst()
                .ifPresent(company -> companies.remove(company));
    }
}

package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RetiringCompanyRepository {
    private List<Company> companies = new ArrayList<>();
    public RetiringCompanyRepository(){
        List<Employee> firstEmployeesList = new ArrayList<>();
        firstEmployeesList.add(new Employee(1, "Alice", 25, "Female", 10000));
        firstEmployeesList.add(new Employee(2, "Bob", 25, "Female", 10000));

        List<Employee> secondEmployeesList = new ArrayList<>();
        secondEmployeesList.add(new Employee(3, "Catnice", 25, "Female", 10000));
        secondEmployeesList.add(new Employee(4, "Doggo", 25, "Male", 10000));
        secondEmployeesList.add(new Employee(5, "Edd", 25, "Male", 10000));

        companies.add(new Company(12, "XYZ Company", 2, firstEmployeesList));
        companies.add(new Company(13, "ABC Company", 3, secondEmployeesList));
        companies.add(new Company(14, "EFG Company", 2, firstEmployeesList));
        companies.add(new Company(15, "XYZ Company", 3, secondEmployeesList));
        companies.add(new Company(16, "ABC Company", 2, firstEmployeesList));
        companies.add(new Company(17, "EFG Company", 3, secondEmployeesList));
        companies.add(new Company(18, "XYZ Company", 2, firstEmployeesList));




    }

    public List<Company> getAllCompanies() {
        return companies;
    }

    public Company getCompanyById(Integer companyId) {
        return companies.stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .findFirst()
                .orElse(null);
    }

    public List<Employee> getEmployeesByCompany(Integer companyId) {
        return companies.stream()
                .filter(company -> company.getCompanyId().equals(companyId))
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
                .filter(company -> company.getCompanyId().equals(companyId))
                .findFirst()
                .map(company -> updateCompany(company, companyToBeUpdated))
                .get(); //TODO: IsPresent Throw Exception
    }
    private Company updateCompany(Company company, Company companyToBeUpdated) {
        if (companyToBeUpdated.getCompanyName() != null){
            company.setCompanyName(companyToBeUpdated.getCompanyName());
        }
        return company;
    }

    public void deleteCompany(Integer companyId) {
        companies.stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .findFirst()
                .ifPresent(company -> companies.remove(company));
    }
}

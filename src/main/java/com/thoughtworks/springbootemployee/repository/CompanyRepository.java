package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyRepository {
    private List<Company> companies = new ArrayList<>();
    public CompanyRepository(){
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
        return null;
    }
}

package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {
    List<Company> companies = new ArrayList<>();
    List<Employee> employees = new ArrayList<>();
    public CompaniesController() {
        companies.add(new Company(12, "XYZ Company", 300, employees));
        companies.add(new Company(13, "ABC Company", 300, employees));
        companies.add(new Company(14, "EFG Company", 300, employees));

        employees.add(new Employee(1, "Alice", 25, "Female", 10000));
        employees.add(new Employee(2, "Bob", 25, "Female", 10000));
        employees.add(new Employee(3, "Catnice", 25, "Female", 10000));
        employees.add(new Employee(4, "Doggo", 25, "Male", 10000));
        employees.add(new Employee(5, "Edd", 25, "Male", 10000));
        employees.add(new Employee(6, "Farla", 25, "Female", 10000));
        employees.add(new Employee(7, "Ginger", 25, "Female", 10000));
        employees.add(new Employee(8, "Yondu", 25, "Male", 10000));
    }

    @GetMapping
    public List<Company> getAllCompanies(){
        return companies;
    }

    @GetMapping(path = "/{companyId}")
    public Company getCompanyById(@PathVariable Integer companyId){
        return companies.stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .findFirst()
                .orElse(null);
    }

}

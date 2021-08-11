package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompaniesController {
    List<Company> companies = new ArrayList<>();
    List<Employee> employees = new ArrayList<>();
    public CompaniesController() {
        companies.add(new Company(12, "XYZ Company", 300, employees));
        companies.add(new Company(13, "ABC Company", 300, employees));
        companies.add(new Company(14, "EFG Company", 300, employees));
        companies.add(new Company(15, "XYZ Company", 300, employees));
        companies.add(new Company(16, "ABC Company", 300, employees));
        companies.add(new Company(17, "EFG Company", 300, employees));
        companies.add(new Company(18, "XYZ Company", 300, employees));
        companies.add(new Company(19, "ABC Company", 300, employees));
        companies.add(new Company(20, "EFG Company", 300, employees));

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

    @GetMapping(path = "/{companyId}/employees")
    public List<Employee> getEmployeesByCompany (@PathVariable Integer companyId) {
        return companies.stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .findFirst()
                .get().getEmployees();
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getCompanyByPage (@RequestParam Integer page, @RequestParam Integer pageSize){
        return companies.stream()
                .skip((page -1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @PostMapping
    public Company addNewCompany (@RequestBody Company company){
        companies.add(company);
        return company;
    }

    @PutMapping (path = "/{companyId}")
    public Company updateCompanyInfo (@PathVariable Integer companyId, @RequestBody Company companyToBeUpdated){
        return companies.stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .findFirst()
                .map(company -> updateCompany(company, companyToBeUpdated))
                .get();
    }

    @DeleteMapping(path = "/{companyId}")
    public void deleteCompany(@PathVariable Integer companyId){
        companies.stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .findFirst()
                .ifPresent(company -> companies.remove(company));
    }

    private Company updateCompany(Company company, Company companyToBeUpdated) {
        if (companyToBeUpdated.getCompanyName() != null){
            company.setCompanyName(companyToBeUpdated.getCompanyName());
        }
        return company;
    }

}

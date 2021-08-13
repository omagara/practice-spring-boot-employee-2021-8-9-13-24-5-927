package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @GetMapping(path = "/{companyId}")
    public Company getCompanyById(@PathVariable Integer companyId){
        return companyService.getCompanyById(companyId);
    }

    @GetMapping(path = "/{companyId}/employees")
    public List<Employee> getEmployeesByCompany (@PathVariable Integer companyId) {
        return companyService.getEmployeesByCompany(companyId);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getCompanyByPage (@RequestParam Integer page, @RequestParam Integer pageSize){
        return companyService.getCompanyByPage(page, pageSize);
    }

    @PostMapping
    public Company addNewCompany (@RequestBody Company company){
        return companyService.addNewCompany(company);
    }

    @PutMapping (path = "/{companyId}")
    public Company updateCompanyInfo (@PathVariable Integer companyId, @RequestBody Company companyToBeUpdated){
        return companyService.updateCompanyInfo(companyId,companyToBeUpdated);
    }

    @DeleteMapping(path = "/{companyId}")
    public void deleteCompany(@PathVariable Integer companyId){
        companyService.deleteCompany(companyId);
    }
}

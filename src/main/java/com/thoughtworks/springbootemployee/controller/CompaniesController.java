package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping
    public List<CompanyResponse> getAllCompanies(){
        List<Company> companies = companyService.getAllCompanies();
        return companies.stream()
                .map(company -> companyMapper.toResponse(company))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{companyId}")
    public CompanyResponse getCompanyById(@PathVariable Integer companyId){
        return companyMapper.toResponse(companyService.getCompanyById(companyId));
    }

    @GetMapping(path = "/{companyId}/employees")
    public List<EmployeeResponse> getEmployeesByCompany (@PathVariable Integer companyId) {
        List<Employee> employees = companyService.getEmployeesByCompany(companyId);
        return employees.stream()
                .map(employee -> employeeMapper.toResponse(employee))
                .collect(Collectors.toList());
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<CompanyResponse> getCompanyByPage (@RequestParam Integer page, @RequestParam Integer pageSize){
        List<Company> companies = companyService.getCompanyByPage(page,pageSize);
        return companies.stream()
                .map(company -> companyMapper.toResponse(company))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CompanyResponse addNewCompany (@RequestBody CompanyRequest companyRequest){
        Company company = companyService.addNewCompany(companyMapper.toEntity(companyRequest));
        return companyMapper.toResponse(company);
    }

    @PutMapping (path = "/{companyId}")
    public CompanyResponse updateCompanyInfo (@PathVariable Integer companyId, @RequestBody CompanyRequest companyRequest){
        Company company =  companyService.updateCompanyInfo(companyId, companyMapper.toEntity(companyRequest));
        return companyMapper.toResponse(company);
    }

    @DeleteMapping(path = "/{companyId}")
    public void deleteCompany(@PathVariable Integer companyId){
        companyService.deleteCompany(companyId);
    }
}

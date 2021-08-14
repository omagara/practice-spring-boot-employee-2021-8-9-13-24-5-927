package com.thoughtworks.springbootemployee.dto;

import java.util.List;


public class CompanyResponse {
    private Integer id;
    private String companyName;
    private Integer numberOfEmployees;
    private List<EmployeeResponse> employees;

    public CompanyResponse() {

    }

    public CompanyResponse(Integer id, String companyName, Integer numberOfEmployees, List<EmployeeResponse> employees) {
        this.id = id;
        this.companyName = companyName;
        this.numberOfEmployees = numberOfEmployees;
        this.employees = employees;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(Integer numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public List<EmployeeResponse> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeResponse> employees) {
        this.employees = employees;
    }
}

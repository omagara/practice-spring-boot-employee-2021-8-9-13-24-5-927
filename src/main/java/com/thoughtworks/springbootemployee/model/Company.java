package com.thoughtworks.springbootemployee.model;

import javax.persistence.*;
import java.util.List;
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyId;
    private String companyName;
    private Integer numberOfEmployees;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "companyId")
    private List<Employee> employees;

    public Company(Integer companyId, String companyName, List<Employee> employees) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.employees = employees;
    }
    public Company(Integer companyId, String companyName, Integer numberOfEmployees, List<Employee> employees) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.numberOfEmployees = numberOfEmployees;
        this.employees = employees;
    }

    public Company() {

    }

    public void setCompanyId(Integer id) {
        this.companyId = id;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}

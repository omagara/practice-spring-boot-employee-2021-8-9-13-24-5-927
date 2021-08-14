package com.thoughtworks.springbootemployee.entity;

import javax.persistence.*;
import java.util.List;
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String companyName;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "companyId")
    private List<Employee> employees;

    public Company(Integer id, String companyName, List<Employee> employees) {
        this.id = id;
        this.companyName = companyName;
        this.employees = employees;
    }

    public Company() {

    }
    public Company(Integer id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Integer getId() {
        return id;
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

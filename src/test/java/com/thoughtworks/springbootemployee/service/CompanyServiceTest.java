package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {
    @InjectMocks
    private CompanyService companyService;
    @Mock
    private CompanyRepository companyRepository;

    @Test
    public void should_return_all_companies_when_getAllCompanies_given_companies() {
        //given
        List<Company> companies = new ArrayList<>();
        List<Employee> firstEmployeesList = new ArrayList<>();
        firstEmployeesList.add(new Employee(1, "Alice", 25, "Female", 10000));
        firstEmployeesList.add(new Employee(2, "Bob", 25, "Female", 10000));
        companies.add(new Company(12, "XYZ Company", 2, firstEmployeesList));
        companies.add(new Company(14, "EFG Company", 2, firstEmployeesList));
        companies.add(new Company(16, "ABC Company", 2, firstEmployeesList));
        given(companyRepository.getAllCompanies()).willReturn(companies);
        //when
        List<Company> actualCompanies = companyService.getAllCompanies();
        //then
        assertEquals(companies, actualCompanies);
    }

    @Test
    public void should_return_a_company_with_id_12_when_getCompanyById_given_all_companies() {
        //given
        List<Company> companies = new ArrayList<>();
        List<Employee> firstEmployeesList = new ArrayList<>();
        firstEmployeesList.add(new Employee(1, "Alice", 25, "Female", 10000));
        firstEmployeesList.add(new Employee(2, "Bob", 25, "Female", 10000));
        companies.add(new Company(12, "XYZ Company", 2, firstEmployeesList));
        companies.add(new Company(14, "EFG Company", 2, firstEmployeesList));
        companies.add(new Company(16, "ABC Company", 2, firstEmployeesList));
        given(companyRepository.getCompanyById(12)).willReturn(companies.get(0));
        //when
        Company actualCompany = companyService.getCompanyById(12);
        //then
        assertEquals(companies.get(0), actualCompany);
    }

    @Test
    public void should_return_employees_with_company_id_12_when_getEmployeesByCompany_given_companies() {
        //given
        List<Company> companies = new ArrayList<>();
        List<Employee> firstEmployeesList = new ArrayList<>();
        firstEmployeesList.add(new Employee(1, "Alice", 25, "Female", 10000));
        firstEmployeesList.add(new Employee(2, "Bob", 25, "Female", 10000));
        companies.add(new Company(12, "XYZ Company", 2, firstEmployeesList));
        companies.add(new Company(14, "EFG Company", 2, firstEmployeesList));
        companies.add(new Company(16, "ABC Company", 2, firstEmployeesList));
        given(companyRepository.getEmployeesByCompany(12)).willReturn(companies.get(0).getEmployees());
        //when
        List<Employee> actualEmployees = companyService.getEmployeesByCompany(12);
        //then
        assertEquals(companies.get(0).getEmployees(), actualEmployees);
    }

    @Test
    public void should_return_the_first_2_companies_when_getCompanyByPage_given_page_1_page_size_2() {
        //given
        List<Company> companies = new ArrayList<>();
        List<Employee> firstEmployeesList = new ArrayList<>();
        firstEmployeesList.add(new Employee(1, "Alice", 25, "Female", 10000));
        firstEmployeesList.add(new Employee(2, "Bob", 25, "Female", 10000));
        companies.add(new Company(12, "XYZ Company", 2, firstEmployeesList));
        companies.add(new Company(14, "EFG Company", 2, firstEmployeesList));
        companies.add(new Company(16, "ABC Company", 2, firstEmployeesList));
        given(companyRepository.getCompanyByPage(1, 2)).willReturn(companies.subList(0,2));
        //when
        List<Company> actualCompanies = companyService.getEmployeebyPage(1, 2);
        //then
        assertEquals(companies.subList(0,2), actualCompanies);
    }

    @Test
    public void should_create_a_company_when_addNewCompany_given_company_information() {
        //given
        List<Employee> firstEmployeesList = new ArrayList<>();
        firstEmployeesList.add(new Employee(1, "Alice", 25, "Female", 10000));
        firstEmployeesList.add(new Employee(2, "Bob", 25, "Female", 10000));
        Company newCompany = new Company(15, "XYZ Company", 3, firstEmployeesList);
        when(companyRepository.addNewCompany(newCompany)).thenReturn(newCompany);
        //when
        Company actualCompany = companyService.addNewCompany(newCompany);
        //then
        assertEquals(15, actualCompany.getCompanyId());
    }

    @Test
    public void should_update__company_information_when_updateCompanyInfo_given_company_id() {
        //given
        List<Employee> firstEmployeesList = new ArrayList<>();
        firstEmployeesList.add(new Employee(1, "Alice", 25, "Female", 10000));
        firstEmployeesList.add(new Employee(2, "Bob", 25, "Female", 10000));
        Company company = new Company(15, "XYZ Company", 3, firstEmployeesList);
        Company updatedCompany = new Company(15, "Junior Company", 3, firstEmployeesList);
        when(companyRepository.updateCompanyInfo(company.getCompanyId(), company)).thenReturn(updatedCompany);
        //when
        Company actualCompany = companyService.updateCompanyInfo(company.getCompanyId(), company);
        //then
        assertNotEquals(company.getCompanyName(), actualCompany.getCompanyName());
    }





}
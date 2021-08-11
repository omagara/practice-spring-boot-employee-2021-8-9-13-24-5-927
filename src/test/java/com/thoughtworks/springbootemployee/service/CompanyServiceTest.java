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
import static org.mockito.BDDMockito.given;

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




}
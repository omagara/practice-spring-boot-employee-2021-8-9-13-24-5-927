package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {
    @InjectMocks
    private CompanyService companyService;
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private EmployeeRepository employeeRepository;


    private List<Company> companies;
    private List<Employee> employees;

    @BeforeEach
    public void companyInformation() {
        companies = Arrays.asList(
                (new Company(1, "OOCL")),
                (new Company(2, "COSCO")),
                (new Company(3, "MSC"))
        );

        employees = Arrays.asList(
                (new Employee(1, "Ramon", 21, "Male", 1000, 1)),
                (new Employee(2, "Bob", 21, "Male", 1100,1)),
                (new Employee(3, "Carlo", 22, "Female", 1200,1)),
                (new Employee(4, "Dea", 23, "Female", 1300,2)),
                (new Employee(5, "Evans", 24, "Male", 1400,2)),
                (new Employee(6, "Faith", 25, "Female", 1500,3)),
                (new Employee(7, "Gab", 26, "Male", 1600,3))
        );
    }

    @Test
    public void should_return_all_companies_when_getAllCompanies_given_companies() {
        //given
        given(companyRepository.findAll()).willReturn(companies);
        //when
        List<Company> actualCompanies = companyService.getAllCompanies();
        //then
        assertEquals(companies, actualCompanies);
    }

    @Test
    public void should_return_a_company_with_id_2_when_getCompanyById_given_all_companies() {
        //given
        given(companyRepository.findById(2)).willReturn(Optional.of(companies.get(0)));
        //when
        Company actualCompany = companyService.getCompanyById(2);
        //then
        assertEquals(companies.get(0), actualCompany);
    }

    @Test
    public void should_return_employees_with_company_id_1_when_getEmployeesByCompany_given_companies() {
        //given
        given(employeeRepository.findByCompanyId(1)).willReturn(employees.subList(0,3));
        //when
        List<Employee> actualEmployees = employeeRepository.findByCompanyId(1);
        //then
        assertEquals(employees.subList(0,3), actualEmployees);
    }

    @Test
    public void should_return_the_first_2_companies_when_getCompanyByPage_given_page_1_page_size_2() {
        //given
        Page<Company> page = new PageImpl<>(companies.subList(0,2));
        Pageable pageable = PageRequest.of(0, 2);
        given(companyRepository.findAll(pageable)).willReturn(page);
        //when
        List<Company> actualCompanies = companyService.getCompanyByPage(1, 2);
        //then
        assertEquals(companies.subList(0,2), actualCompanies);
    }

    @Test
    public void should_create_a_company_when_addNewCompany_given_company_information() {
        //given
        Company newCompany = new Company(15, "XYZ Company");
        when(companyRepository.save(newCompany)).thenReturn(newCompany);
        //when
        Company actualCompany = companyService.addNewCompany(newCompany);
        //then
        assertEquals(15, actualCompany.getId());
    }

    @Test
    public void should_update__company_information_when_updateCompanyInfo_given_company_id() {
        //given
        Company updateCompany = new Company(1, "OOCL-Philippines");
        Optional<Company> optionalCompany = of(updateCompany);
        when(companyRepository.findById(companies.get(0).getId())).thenReturn(optionalCompany);
        when(companyRepository.save(optionalCompany.get())).thenReturn(updateCompany);
        //when
        Company actualCompany = companyService.updateCompanyInfo(companies.get(0).getId(), updateCompany);
        //then
        assertNotEquals(companies.get(0).getCompanyName(), actualCompany.getCompanyName());
    }

    @Test
    public void should_delete_a_company_when_deleteCompany_given_company_id() {
        //given
        //when
        companyService.deleteCompany(companies.get(0).getId());
        //then
        verify(companyRepository, times(1)).deleteById(companies.get(0).getId());
    }
}
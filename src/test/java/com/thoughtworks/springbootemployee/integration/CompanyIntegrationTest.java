package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
    @AutoConfigureMockMvc
    public class CompanyIntegrationTest {
        @Autowired
        private MockMvc mockMvc;
        @Autowired
        private CompanyRepository companyRepository;
        @Autowired
        private EmployeeRepository employeeRepository;

    private List<Employee> firstEmployees, secondEmployees;
    private List<Company> companies;

    @BeforeEach
    public void companyInformation() {
        companies = Arrays.asList(
                (new Company(1, "OOCL", firstEmployees)),
                (new Company(2, "COSCO", secondEmployees))
        );
        firstEmployees = Arrays.asList(
                (new Employee(1, "Ramon", 21, "Male", 1000, 1)),
                (new Employee(2, "Bob", 21, "Male", 1100,1)),
                (new Employee(3, "Carlo", 22, "Female", 1200,1)),
                (new Employee(4, "Dea", 23, "Female", 1300,1))
        );
        secondEmployees = Arrays.asList(
                (new Employee(5, "Evans", 24, "Male", 1400,2)),
                (new Employee(6, "Faith", 25, "Female", 1500,2)),
                (new Employee(7, "Gab", 26, "Male", 1600,2))
        );
    }
        @Test
        public void should_return_all_companies_when_getAllCompanies_API() throws Exception {
            //given
            companyRepository.saveAll(companies);
            //when
            //then
            mockMvc.perform(MockMvcRequestBuilders.get("/companies"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id").isNumber())
                    .andExpect(jsonPath("$[0].companyName").value("OOCL"))
                    .andExpect(jsonPath("$[1].id").isNumber())
                    .andExpect(jsonPath("$[1].companyName").value("COSCO"));
        }
}

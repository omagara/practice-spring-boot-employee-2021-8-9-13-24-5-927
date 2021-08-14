package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
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

    @AfterEach
    void tearDown() {
        companyRepository.deleteAll();
    }

    private List<Employee> firstEmployees, secondEmployees;
    private List<Company> companies;

    @BeforeEach
    public void companyInformation() {
        companies = Arrays.asList(
                (new Company(1, "OOCL")),
                (new Company(2, "COSCO")),
                (new Company(3, "MSC"))
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

    @Test
    public void should_return_company_with_Id_2_when_getCompanyById_API() throws Exception {
        //given
        Company company1 = companyRepository.save(companies.get(0));
        Company company2 = companyRepository.save(companies.get(1));

        int companyId = company2.getId();
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{companyId}", companyId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.companyName").value("COSCO"));
    }

    @Test
    void should_return_two_companies_when_getCompanyByPage_API_given_page_1_pag_size_2() throws Exception {
        //given
        companyRepository.save(companies.get(0));
        companyRepository.save(companies.get(1));
        companyRepository.save(companies.get(2));

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/companies?page=1&pageSize=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)));
    }

    @Test
    void should_create_a_Company_when_addNewCompany_API() throws Exception {
        //given
        String newCompany = "{\n" +
                "        \"companyName\": \"MAERSK\"\n" +
                "}";
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newCompany))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.companyName").value("MAERSK"));
    }

    @Test
    void should_update_a_company_when_updateCompanyInfo_API() throws Exception {
        //given
        Company company = companyRepository.save(companies.get(0));
        int companyId = company.getId();
        String updatedCompanyInfo = "{\n" +
                "        \"companyName\": \"OOCL-PH\"\n" +
                "}\n";
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/companies/{companyId}", companyId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedCompanyInfo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.companyName").value("OOCL-PH"));
    }

    @Test
    void should_delete_a_company_when_deleteCompany_API() throws Exception {
        //given
        Company company = companyRepository.save(companies.get(0));
        Company company1 = companyRepository.save(companies.get(1));
        Company company2 = companyRepository.save(companies.get(2));
        //when
        int companyId = company1.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete("/companies/{companyId}", companyId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").doesNotExist());
    }
}

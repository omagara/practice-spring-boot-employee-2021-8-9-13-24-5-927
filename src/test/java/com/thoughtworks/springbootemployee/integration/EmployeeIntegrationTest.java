package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.model.Employee;
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
    public class EmployeeIntegrationTest {
        @Autowired
        private MockMvc mockMvc;
        @Autowired
        private EmployeeRepository employeeRepository;

        private List<Employee> employees;
        @BeforeEach
        public void data() {
            employees = Arrays.asList(
                    (new Employee(1, "Ramon", 21, "Male", 1000)),
                    (new Employee(2, "Bob", 21, "Male", 1100)),
                    (new Employee(3, "Carlo", 22, "Female", 1200)),
                    (new Employee(4, "Dea", 23, "Female", 1300)),
                    (new Employee(5, "Evans", 24, "Male", 1400)),
                    (new Employee(6, "Faith", 25, "Female", 1500)),
                    (new Employee(7, "Gab", 26, "Male", 1600))
            );
        }

        @Test
        void should_return_all_employees_when_getAllEmployees() throws Exception {
            //given
            //when
            //then
            mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id").isNumber())
                    .andExpect(jsonPath("$[0].name").value("Ramon"))
                    .andExpect(jsonPath("$[0].age").value(21))
                    .andExpect(jsonPath("$[0].gender").value("Male"))
                    .andExpect(jsonPath("$[0].salary").value(1000));
        }

        @Test
        void should_return_employee_when_getEmployeeByID_api() throws Exception {
            //given
            int employeeId = employees.get(0).getId();
            //when
            //then
            mockMvc.perform(MockMvcRequestBuilders.get("/employees/{employeeId}", employeeId))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name").value("Ramon"))
                    .andExpect(jsonPath("$.age").value(21))
                    .andExpect(jsonPath("$.gender").value("Male"))
                    .andExpect(jsonPath("$.salary").value(1000));
        }

    }

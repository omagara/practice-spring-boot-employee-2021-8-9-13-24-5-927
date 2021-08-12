package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

    @SpringBootTest
    @AutoConfigureMockMvc
    public class EmployeeIntegrationTest {
        @Autowired
        private MockMvc mockMvc;
        @Autowired
        private EmployeeRepository employeeRepository;
        @AfterEach
        void tearDown(){
            employeeRepository.deleteAll();
        }
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
            employeeRepository.save(employees.get(0));
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
            Employee employee = employeeRepository.save(employees.get(0));
            int employeeId = employee.getId();
            //when
            //then
            mockMvc.perform(MockMvcRequestBuilders.get("/employees/{employeeId}", employeeId))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name").value("Ramon"))
                    .andExpect(jsonPath("$.age").value(21))
                    .andExpect(jsonPath("$.gender").value("Male"))
                    .andExpect(jsonPath("$.salary").value(1000));
        }

        @Test
        void should_return_two_employees_when_getEmployeeByPage_api_given_page_2_pag_size_2() throws Exception {
            //given
            employeeRepository.save(employees.get(0));
            employeeRepository.save(employees.get(1));
            employeeRepository.save(employees.get(2));
            employeeRepository.save(employees.get(3));

            //when
            //then
            mockMvc.perform(MockMvcRequestBuilders.get("/employees?page=2&pageSize=2"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.*", hasSize(2)));
        }

        @Test
        void should_return_male_employees_when_getEmployeeByGender_api() throws Exception {
            //given
            employeeRepository.save(employees.get(0));
            //when
            //then
            mockMvc.perform(MockMvcRequestBuilders.get("/employees?gender=Male"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].gender").value("Male"));
        }

        @Test
        void should_create_an_employee_when_addNewEmployee_api() throws Exception {
            //given
            String newEmployee = "{\n" +
                    "        \"id\": 3,\n" +
                    "        \"name\": \"Gary\",\n" +
                    "        \"age\": 28,\n" +
                    "        \"gender\": \"Male\",\n" +
                    "        \"salary\": 1900\n" +
                    "}";
            //when
            //then
            mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(newEmployee))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name").value("Gary"))
                    .andExpect(jsonPath("$.age").value(28))
                    .andExpect(jsonPath("$.gender").value("Male"))
                    .andExpect(jsonPath("$.salary").value(1900));
        }

        @Test
        void should_update_an_employee_when_updateEmployeeInfo_api() throws Exception {
            //given
            Employee employee = employeeRepository.save(employees.get(0));
            int employeeId = employee.getId();
            String updatedEmployeeInfo = "{\n" +
                    "        \"age\": 34,\n" +
                    "        \"salary\": 2500\n" +
                    "}\n";


            //when
            //then
            mockMvc.perform(MockMvcRequestBuilders.put("/employees/{employeeID}", employeeId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(updatedEmployeeInfo))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.age").value(34))
                    .andExpect(jsonPath("$.salary").value(2500));
        }

        @Test
        void should_delete_an_employee_when_deleteEmployee_api() throws Exception {
            //given
            Employee employee = employeeRepository.save(employees.get(0));

            //when
            int employeeId = employee.getId();
            mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{employeeId}", employeeId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }

    }

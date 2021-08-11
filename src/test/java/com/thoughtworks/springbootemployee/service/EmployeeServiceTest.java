package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;
    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void should_return_all_employees_when_getAllEmployees_given_all_employees() {
        //given
            List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Alice", 25, "Female", 10000));
        employees.add(new Employee(2, "Bob", 25, "Female", 10000));
        given(employeeRepository.getEmployees()).willReturn(employees);
        //when
        List<Employee> actualEmployees = employeeService.getAllEmployees();
        //then
        assertEquals(employees, actualEmployees);
    }

    @Test
    public void should_return_an_employee_with_id_1_when_getAllEmployeesById_given_all_employees() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Alice", 25, "Female", 10000));
        employees.add(new Employee(2, "Bob", 25, "Female", 10000));
        given(employeeRepository.getEmployeesById(1)).willReturn(employees.get(0));
        //when
        Employee actualEmployees = employeeService.getEmployeesById(1);
        //then
        assertEquals(employees.get(0), actualEmployees);
    }

    @Test
    public void should_return_the_first_3_employees_when_getEmployeeByPage_given_page_1_page_size_3() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Alice", 25, "Female", 10000));
        employees.add(new Employee(2, "Bob", 25, "Female", 10000));
        employees.add(new Employee(3, "Alice", 25, "Female", 10000));
        employees.add(new Employee(4, "Bob", 25, "Female", 10000));
        given(employeeRepository.getEmployeebyPage(1, 3)).willReturn(employees.subList(0,3));
        //when
        List<Employee> actualEmployees = employeeService.getEmployeebyPage(1, 3);
        //then
        assertEquals(employees.subList(0,3), actualEmployees);
    }


    }

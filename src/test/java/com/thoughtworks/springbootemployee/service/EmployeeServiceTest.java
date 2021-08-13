package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;
    @Mock
    private RetiringEmployeeRepository retiringEmployeeRepository;

    @Test
    public void should_return_all_employees_when_getAllEmployees_given_all_employees() {
        //given
            List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Alice", 25, "Female", 10000));
        employees.add(new Employee(2, "Bob", 25, "Female", 10000));
        given(retiringEmployeeRepository.getEmployees()).willReturn(employees);
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
        given(retiringEmployeeRepository.getEmployeesById(1)).willReturn(employees.get(0));
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
        given(retiringEmployeeRepository.getEmployeebyPage(1, 3)).willReturn(employees.subList(0,3));
        //when
        List<Employee> actualEmployees = employeeService.getEmployeebyPage(1, 3);
        //then
        assertEquals(employees.subList(0,3), actualEmployees);
    }

    @Test
    public void should_return_all_male_employees_when_getEmployeeByGender_given_employees() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Alice", 25, "Female", 10000));
        employees.add(new Employee(2, "Bobby", 26, "Male", 10000));
        employees.add(new Employee(3, "Alicia", 27, "Female", 10000));
        employees.add(new Employee(4, "Bob", 28, "Male", 10000));
        given(retiringEmployeeRepository.getEmployeebyGender("Male")).willReturn(employees);
        //when
        List<Employee> actualEmployees = employeeService.getEmployeebyGender("Male");
        //then
        assertEquals(employees, actualEmployees);
    }

    @Test
    public void should_create_employees_when_addNewEmployee_given_employee_information() {
        //given
        Employee newEmployee = new Employee(25, "Ramon", 21,"Male",5000);
        when(retiringEmployeeRepository.addNewEmployee(newEmployee)).thenReturn(newEmployee);

        //when
        Employee actualEmployee = employeeService.addNewEmployee(newEmployee);
        //then
        assertEquals(25, actualEmployee.getId());
    }

    @Test
    public void should_update_an_employee_when_updateEmployeeInfo_given_employee_id() {
        //given
        Employee employee = new Employee(25, "Ramon", 21,"Male",5000);
        Employee updateEmployee = new Employee(25, "Ramon", 22,"Male",5200);
        when(retiringEmployeeRepository.updateEmployeeInfo(employee.getId(), employee)).thenReturn(updateEmployee);

        //when
        Employee actualEmployee = employeeService.updateEmployeeInfo(employee.getId(), employee);
        //then
        assertNotEquals(employee.getSalary(), actualEmployee.getSalary());
    }

    @Test
    public void should_delete_an_employee_when_deleteEmployee_given_employee_id() {
        //given
        Employee employee = new Employee(25, "Ramon", 21,"Male",5000);
        EmployeeService service = new EmployeeService(retiringEmployeeRepository);
        //when
        service.deleteEmployee(employee.getId());
        //then
        verify(retiringEmployeeRepository, times(1)).deleteEmployee(employee.getId());
    }



    }

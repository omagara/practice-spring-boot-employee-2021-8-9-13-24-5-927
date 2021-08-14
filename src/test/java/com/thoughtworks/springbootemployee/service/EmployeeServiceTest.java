package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
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
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;
    @Mock
    private EmployeeRepository employeeRepository;

    private List<Employee> employees;
    @BeforeEach
    public void employeesInformation() {
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
    public void should_return_all_employees_when_getAllEmployees_given_all_employees() {
        //given
        given(employeeRepository.findAll()).willReturn(employees);
        //when
        List<Employee> actualEmployees = employeeService.getAllEmployees();
        //then
        assertEquals(employees, actualEmployees);
    }

    @Test
    public void should_return_an_employee_with_id_1_when_getAllEmployeesById_given_all_employees() {
        //given
        given(employeeRepository.findById(1)).willReturn(of(employees.get(0)));
        //when
        Employee actualEmployees = employeeService.getEmployeesById(1);
        //then
        assertEquals(employees.get(0), actualEmployees);
    }

    @Test
    public void should_return_the_first_3_employees_when_getEmployeeByPage_given_page_1_page_size_3() {
        //given
        Page<Employee> page = new PageImpl<>(employees.subList(0,3));
        Pageable pageable = PageRequest.of(0, 3);
        given(employeeRepository.findAll(pageable)).willReturn(page);
        //when
        List<Employee> actualEmployees = employeeService.getEmployeebyPage(1, 3);
        //then
        assertEquals(employees.subList(0,3), actualEmployees);
    }

    @Test
    public void should_return_all_male_employees_when_getEmployeeByGender_given_employees() {
        //given
        given(employeeRepository.findAllByGender("Male")).willReturn(employees);
        //when
        List<Employee> actualEmployees = employeeService.getEmployeebyGender("Male");
        //then
        assertEquals(employees, actualEmployees);
    }

    @Test
    public void should_create_employees_when_addNewEmployee_given_employee_information() {
        //given
        Employee newEmployee = new Employee(25, "Ramon", 21,"Male",5000);
        when(employeeRepository.save(newEmployee)).thenReturn(newEmployee);
        //when
        Employee actualEmployee = employeeService.addNewEmployee(newEmployee);
        //then
        assertEquals(25, actualEmployee.getId());
    }

    @Test
    public void should_update_an_employee_when_updateEmployeeInfo_given_employee_id() {
        //given
        Employee updateEmployee = new Employee(1, "Ramon", 22,"Male",5200);
        Optional<Employee> optionalEmployee = of(updateEmployee);
        when(employeeRepository.findById(employees.get(0).getId())).thenReturn(optionalEmployee);
        when(employeeRepository.save(optionalEmployee.get())).thenReturn(updateEmployee);
        //when
        Employee actualEmployee = employeeService.updateEmployeeInfo(employees.get(0).getId(), updateEmployee);
        //then
        assertNotEquals(employees.get(0).getSalary(), actualEmployee.getSalary());
    }

    @Test
    public void should_delete_an_employee_when_deleteEmployee_given_employee_id() {
        //given
        //when
        employeeService.deleteEmployee(employees.get(0).getId());
        //then
        verify(employeeRepository, times(1)).deleteById(employees.get(0).getId());
    }
}

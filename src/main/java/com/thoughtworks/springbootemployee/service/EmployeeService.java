package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getEmployees();
    }

    public Employee getEmployeesById(Integer employeeId) {
        return employeeRepository.getEmployeesById(employeeId);
    }

    public List<Employee> getEmployeebyPage(Integer page, Integer pageSize) {
        return employeeRepository.getEmployeebyPage(page,pageSize);
    }

    public List<Employee> getEmployeebyGender(String gender) {
        return employeeRepository.getEmployeebyGender(gender);
    }

    public Employee addNewEmployee(Employee employee) {
        return employeeRepository.addNewEmployee(employee);
    }

    public Employee updateEmployeeInfo(Integer employeeId, Employee employeeToBeUpdated) {
        return employeeRepository.updateEmployeeInfo(employeeId, employeeToBeUpdated);
    }

    public void deleteEmployee(Integer employeeId) {
        employeeRepository.deleteEmployee(employeeId);
    }
}

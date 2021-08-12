package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private RetiringEmployeeRepository retiringEmployeeRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(RetiringEmployeeRepository retiringEmployeeRepository) {
        this.retiringEmployeeRepository = retiringEmployeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeesById(Integer employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public List<Employee> getEmployeebyPage(Integer page, Integer pageSize) {
        return retiringEmployeeRepository.getEmployeebyPage(page,pageSize);
    }

    public List<Employee> getEmployeebyGender(String gender) {
        return retiringEmployeeRepository.getEmployeebyGender(gender);
    }

    public Employee addNewEmployee(Employee employee) {
        return retiringEmployeeRepository.addNewEmployee(employee);
    }

    public Employee updateEmployeeInfo(Integer employeeId, Employee employeeToBeUpdated) {
        return retiringEmployeeRepository.updateEmployeeInfo(employeeId, employeeToBeUpdated);
    }

    public void deleteEmployee(Integer employeeId) {
        retiringEmployeeRepository.deleteEmployee(employeeId);
    }
}

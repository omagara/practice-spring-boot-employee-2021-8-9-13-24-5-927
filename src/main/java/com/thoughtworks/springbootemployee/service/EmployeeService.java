package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {
    private RetiringEmployeeRepository retiringEmployeeRepository;

    public EmployeeService(RetiringEmployeeRepository retiringEmployeeRepository) {
        this.retiringEmployeeRepository = retiringEmployeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return retiringEmployeeRepository.getEmployees();
    }

    public Employee getEmployeesById(Integer employeeId) {
        return retiringEmployeeRepository.getEmployeesById(employeeId);
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

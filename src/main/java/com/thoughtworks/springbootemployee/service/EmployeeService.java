package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    public Employee getEmployeesById(Integer employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(()->new EmployeeNotFoundException("Employee ID not found."));
    }

    public List<Employee> getEmployeebyPage(Integer page, Integer pageSize) {
        return employeeRepository.findAll(PageRequest.of(page-1, pageSize)).getContent();
    }

    public List<Employee> getEmployeebyGender(String gender) {
        return employeeRepository.findAllByGender(gender);
    }

    public Employee addNewEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployeeInfo(Integer employeeId, Employee employeeToBeUpdated) {
        return employeeRepository.findById(employeeId)
                .map(employee -> updateEmployee(employee,employeeToBeUpdated))
                .orElse(null);


    }
    private Employee updateEmployee(Employee employee, Employee employeeToBeUpdated) {
        if (employeeToBeUpdated.getName() != null){
            employee.setName(employeeToBeUpdated.getName());
        }
        if (employeeToBeUpdated.getGender() != null){
            employee.setGender(employeeToBeUpdated.getGender());
        }
        if (employeeToBeUpdated.getAge() != null){
            employee.setAge(employeeToBeUpdated.getAge());
        }
        if (employeeToBeUpdated.getSalary() != null){
            employee.setSalary(employeeToBeUpdated.getSalary());
        }

        return  employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}

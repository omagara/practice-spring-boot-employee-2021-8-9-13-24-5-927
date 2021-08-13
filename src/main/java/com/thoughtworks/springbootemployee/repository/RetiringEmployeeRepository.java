package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RetiringEmployeeRepository {
    private List<Employee> employees = new ArrayList<>();
    public RetiringEmployeeRepository(){
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee getEmployeesById(Integer employeeId) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }

    public List<Employee> getEmployeebyPage(Integer page, Integer pageSize) {
        return employees.stream()
                .skip((page -1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeebyGender(String gender) {
        return employees.stream()
                .filter(employee -> employee.getGender().equalsIgnoreCase(gender))
                .collect(Collectors.toList());
    }

    public Employee addNewEmployee(Employee employee) {
        employees.add(employee);
        return employee;
    }

    public Employee updateEmployeeInfo(Integer employeeId, Employee employeeToBeUpdated) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .map(employee -> updateEmployee(employee, employeeToBeUpdated))
                .get();
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

        return  employee;
    }

    public void deleteEmployee(Integer employeeId) {
        employees.stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .ifPresent(employees::remove);
    }
}


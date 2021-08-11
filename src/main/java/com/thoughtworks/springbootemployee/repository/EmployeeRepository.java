package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
@Repository
public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();
    public EmployeeRepository(){
        employees.add(new Employee(1, "Alice", 25, "Female", 10000));
        employees.add(new Employee(2, "Bob", 25, "Female", 10000));
        employees.add(new Employee(3, "Catnice", 25, "Female", 10000));
        employees.add(new Employee(4, "Doggo", 25, "Male", 10000));
        employees.add(new Employee(5, "Edd", 25, "Male", 10000));
        employees.add(new Employee(6, "Farla", 25, "Female", 10000));
        employees.add(new Employee(7, "Ginger", 25, "Female", 10000));
        employees.add(new Employee(8, "Yondu", 25, "Male", 10000));
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee getEmployeesById(Integer employeeId) {
        return null;
    }
}

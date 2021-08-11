package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private final List<Employee> employees = new ArrayList<>();
    public EmployeesController(){
        employees.add(new Employee(1, "Alice", 25, "Female", 10000));
        employees.add(new Employee(2, "Bob", 25, "Female", 10000));
        employees.add(new Employee(3, "Catnice", 25, "Female", 10000));
        employees.add(new Employee(4, "Doggo", 25, "Male", 10000));
        employees.add(new Employee(5, "Edd", 25, "Male", 10000));
        employees.add(new Employee(6, "Farla", 25, "Female", 10000));
        employees.add(new Employee(7, "Ginger", 25, "Female", 10000));
        employees.add(new Employee(8, "Yondu", 25, "Male", 10000));

    }

    @GetMapping
    public List<Employee> getEmployees(){
        return employees;
    }

    @GetMapping(path = "/{employeeID}")
    public Employee getEmployeeByID (@PathVariable Integer employeeID){
        return employees.stream()
                .filter(employee -> employee.getId().equals(employeeID))
                .findFirst()
                .orElse(null);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getEmployeeByPage (@RequestParam Integer page, @RequestParam Integer pageSize){
        return employees.stream()
                .skip((page -1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }



}

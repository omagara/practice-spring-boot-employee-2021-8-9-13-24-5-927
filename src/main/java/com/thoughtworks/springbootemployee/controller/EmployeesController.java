package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private final List<Employee> employees = new ArrayList<>();
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
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

    @GetMapping(params = {"gender"} )
    public List<Employee> getEmployeeByGender (@RequestParam String gender){
        return employees.stream()
                .filter(employee -> employee.getGender().equalsIgnoreCase(gender))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Employee addNewEmployee (@RequestBody Employee employee){
        employees.add(employee);

        return employee;
    }

    @PutMapping (path = "/{employeeId}")
    public Employee updateEmployeeInfo (@PathVariable Integer employeeId, @RequestBody Employee employeeToBeUpdated){
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


}

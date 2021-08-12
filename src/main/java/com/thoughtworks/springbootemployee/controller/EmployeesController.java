package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Employee> getEmployeeByID (@PathVariable Integer employeeID){
        return employeeService.getEmployeesById(employeeID);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getEmployeeByPage (@RequestParam Integer page, @RequestParam Integer pageSize){
        return employeeService.getEmployeebyPage(page,pageSize);
    }

    @GetMapping(params = {"gender"} )
    public List<Employee> getEmployeeByGender (@RequestParam String gender){
        return employeeService.getEmployeebyGender(gender);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Employee addNewEmployee (@RequestBody Employee employee){
        return employeeService.addNewEmployee(employee);
    }

    @PutMapping (path = "/{employeeId}")
    public Employee updateEmployeeInfo (@PathVariable Integer employeeId, @RequestBody Employee employeeToBeUpdated){
        return employeeService.updateEmployeeInfo(employeeId,employeeToBeUpdated);
    }

    @DeleteMapping(path = "/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId){
        employeeService.deleteEmployee(employeeId);
    }
}

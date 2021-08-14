package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private final List<Employee> employees = new ArrayList<>();
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/{employeeID}")
    public Employee getEmployeeByID (@PathVariable Integer employeeID){
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
    public Employee addNewEmployee (@RequestBody EmployeeRequest employeeRequest){
        return employeeService.addNewEmployee(employeeMapper.toEntity(employeeRequest));
    }

    @PutMapping (path = "/{employeeId}")
    public Employee updateEmployeeInfo (@PathVariable Integer employeeId, @RequestBody EmployeeRequest employeeRequest){
        return employeeService.updateEmployeeInfo(employeeId, employeeMapper.toEntity(employeeRequest));
    }

    @DeleteMapping(path = "/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId){
        employeeService.deleteEmployee(employeeId);
    }
}

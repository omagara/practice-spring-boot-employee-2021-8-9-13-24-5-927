package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping
    public List<EmployeeResponse> getAllEmployees(){
        List<Employee> employees = employeeService.getAllEmployees();
        return employees.stream()
                .map(employee -> employeeMapper.toResponse(employee))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{employeeID}")
    public EmployeeResponse getEmployeeByID (@PathVariable Integer employeeID){
        return employeeMapper.toResponse(employeeService.getEmployeesById(employeeID));
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<EmployeeResponse> getEmployeeByPage (@RequestParam Integer page, @RequestParam Integer pageSize){
        List<Employee> employees = employeeService.getEmployeebyPage(page,pageSize);
        return employees.stream()
                .map(employee -> employeeMapper.toResponse(employee))
                .collect(Collectors.toList());
    }

    @GetMapping(params = {"gender"} )
    public List<EmployeeResponse> getEmployeeByGender (@RequestParam String gender){
        List<Employee> employees = employeeService.getEmployeebyGender(gender);
        return employees.stream()
                .map(employee -> employeeMapper.toResponse(employee))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EmployeeResponse addNewEmployee (@RequestBody EmployeeRequest employeeRequest){
        Employee employee = employeeService.addNewEmployee(employeeMapper.toEntity(employeeRequest));
        return employeeMapper.toResponse(employee);
    }

    @PutMapping (path = "/{employeeId}")
    public EmployeeResponse updateEmployeeInfo (@PathVariable Integer employeeId, @RequestBody EmployeeRequest employeeRequest){
        Employee employee = employeeService.updateEmployeeInfo(employeeId, employeeMapper.toEntity(employeeRequest));
        return employeeMapper.toResponse(employee);
    }

    @DeleteMapping(path = "/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId){
        employeeService.deleteEmployee(employeeId);
    }
}

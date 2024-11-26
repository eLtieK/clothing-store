package com.clothing_store.controller;

import com.clothing_store.dto.request.EmployeeRequest;
import com.clothing_store.dto.request.UserRequest;
import com.clothing_store.entity.Employee;
import com.clothing_store.entity.User;
import com.clothing_store.service.EmployeeService;
import com.clothing_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    Employee createEmployee(@RequestBody EmployeeRequest request) {
        return employeeService.createEmployee(request);
    }

    @GetMapping
    List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/{employeeID}")
    Employee getEmployee(@PathVariable("employeeID") String employeeID) {
        return employeeService.getEmployee(employeeID);
    }

//    @PutMapping("/{employeeID}")
//    Employee updateEmployee(@PathVariable String employeeID, @RequestBody EmployeeRequest request) {
//        return employeeService.updateEmployee(employeeID, request);
//    }

    @DeleteMapping("/{employeeID}")
    String deleteEmployee(@PathVariable String employeeID) {
        employeeService.deleteEmployee(employeeID);
        return "Employee " + employeeID + " has been deleted";
    }
}

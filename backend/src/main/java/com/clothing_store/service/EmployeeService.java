package com.clothing_store.service;

import com.clothing_store.dto.request.EmployeeRequest;
import com.clothing_store.entity.Employee;
import com.clothing_store.entity.User;
import com.clothing_store.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EmployeeService extends UserService{
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(EmployeeRequest request) {
        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());

        User savedUser = this.createUser(user);

        Employee employee = new Employee();
        employee.setUser(savedUser);
        employee.setPosition(request.getPosition());
        employee.setSalary(new BigDecimal(request.getSalary()));

        System.out.println(request);
        if (request.getSupervisorID() != null) {

            Employee supervisor = employeeRepository.findById(request.getSupervisorID()).orElse(null);
            employee.setSupervisor(supervisor);
        } else {
            employee.setSupervisor(null); // Explicitly setting supervisor to null
        }

        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployees() {return employeeRepository.findAll();}

    public Employee getEmployee(String employeeID) {
        return employeeRepository.findById(employeeID)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteEmployee(String employeeID) {
        employeeRepository.deleteById(employeeID);
    }
}

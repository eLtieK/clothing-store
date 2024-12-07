package com.clothing_store.controller;

import com.clothing_store.dto.request.CustomerRequest;
import com.clothing_store.dto.request.UserRequest;
import com.clothing_store.entity.Customer;
import com.clothing_store.entity.User;
import com.clothing_store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    Customer createCustomer(@RequestBody CustomerRequest request) {
        return customerService.createCustomer(request);
    }

    @GetMapping
    List<Customer> getCustomers() { return customerService.getCustomers();}

    @GetMapping("/{customerID}")
    Customer getCustomer(@PathVariable("customerID") String customerID) {
        return customerService.getCustomer(customerID);
    }

    @PutMapping("/{customerID}")
    Customer updateUser(@PathVariable String customerID, @RequestBody CustomerRequest request) {
        return customerService.updateCustomer(customerID, request);
    }

    @DeleteMapping("/{customerID}")
    String deleteCustomer(@PathVariable String customerID) {
        customerService.deleteCustomer(customerID);
        return "Customer " + customerID + " has been deleted";
    }
}

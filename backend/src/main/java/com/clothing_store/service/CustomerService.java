package com.clothing_store.service;

import com.clothing_store.dto.request.CustomerRequest;
import com.clothing_store.dto.request.UserRequest;
import com.clothing_store.entity.Customer;
import com.clothing_store.entity.User;
import com.clothing_store.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerService extends UserService{
    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(CustomerRequest request) {
        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());

        User savedUser = this.createUser(user);

        Customer customer = new Customer();
        customer.setUser(savedUser);
        customer.setAddresses(request.getAddresses());
        customer.setRegistrationDate(LocalDate.parse(request.getRegistrationDate()));

        return customerRepository.save(customer);
    }

    public List<Customer> getCustomers() {return customerRepository.findAll();}

    public Customer getCustomer(String customerID) {
        return customerRepository.findById(customerID)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer updateCustomer(String customerID, CustomerRequest request) {
        Customer customer = this.getCustomer(customerID);
        customer.setRegistrationDate(LocalDate.parse(request.getRegistrationDate()));
        customer.setAddresses(request.getAddresses());

        User user = customer.getUser();
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());

        this.updateUser(customerID, user);
        return customerRepository.save(customer);
    }

    public void deleteCustomer(String customerID) {
        customerRepository.deleteById(customerID);
        this.deleteUser(customerID);
    }
}

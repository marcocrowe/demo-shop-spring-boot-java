package io.github.marcocrowe.services;

import io.github.marcocrowe.model.Customer;
import io.github.marcocrowe.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> findCustomerById(Integer customerId) {
        return customerRepository.findById(customerId);
    }

    public List<Customer> findCustomers() {
        return customerRepository.findAll();
    }

    public void updateCustomer(Customer customer) throws IllegalArgumentException {
        if (customerRepository.findById(customer.getCustomerId()).isEmpty())
            throw new IllegalArgumentException("No such customer in the system for ." + customer.getCustomerId());

        customerRepository.save(customer);
    }

    public void deleteCustomerById(Integer customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isEmpty())
            throw new IllegalArgumentException("No such customer in the system for ." + customerId);

        customerRepository.delete(customerOptional.get());
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}

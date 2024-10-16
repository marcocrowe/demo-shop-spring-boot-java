package io.github.marcocrowe.services;

import io.github.marcocrowe.model.Customer;
import io.github.marcocrowe.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findCustomers()
    {
        return customerRepository.findAll();
    }

    public void updateCustomer(Customer customer) throws Exception {
        if(customerRepository.findById(customer.getCustomerId()).isEmpty())
            throw new Exception("No such customer in the system.");

        customerRepository.save(customer);
    }

}

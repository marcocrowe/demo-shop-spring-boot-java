package io.github.marcocrowe.repositories;

import io.github.marcocrowe.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByEmail(String email);
    Customer findCustomerByEmailIgnoreCaseAndPassword(String email, String password);
}

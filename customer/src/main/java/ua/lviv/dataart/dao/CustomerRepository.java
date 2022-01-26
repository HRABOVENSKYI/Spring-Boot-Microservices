package ua.lviv.dataart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.dataart.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}

package ua.lviv.dataart.service;

import org.springframework.stereotype.Service;
import ua.lviv.dataart.dao.CustomerRepository;
import ua.lviv.dataart.dto.CustomerRegistrationRequest;
import ua.lviv.dataart.model.Customer;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // TODO: check if email valid
        // TODO: check if email not taken
        customerRepository.save(customer);
    }
}

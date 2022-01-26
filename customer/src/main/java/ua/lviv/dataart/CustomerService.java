package ua.lviv.dataart;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // TODO: check if email valid
        // TODO: check if email not taken
        customerRepository.saveAndFlush(customer); // Flush to get generated id

        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        if (fraudCheckResponse == null || fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        // TODO: send notification
    }
}

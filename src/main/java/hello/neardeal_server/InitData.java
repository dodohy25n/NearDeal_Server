package hello.neardeal_server;

import hello.neardeal_server.member.dto.CustomerRequest;
import hello.neardeal_server.member.entity.Customer;
import hello.neardeal_server.member.repository.CustomerRepository;
import hello.neardeal_server.store.service.StoreService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InitData {

    private final StoreService storeService;
    private final CustomerRepository customerRepository;


    @PostConstruct
    public void init() {

        Customer customer = Customer.create(new CustomerRequest("ENGINEERING"));
        customerRepository.save(customer);
    }

}

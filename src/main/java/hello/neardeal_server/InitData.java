package hello.neardeal_server;

import hello.neardeal_server.member.dto.request.CustomerRequest;
import hello.neardeal_server.member.dto.request.OwnerRequest;
import hello.neardeal_server.member.entity.Customer;
import hello.neardeal_server.member.entity.Owner;
import hello.neardeal_server.member.entity.PartnerCategory;
import hello.neardeal_server.member.repository.CustomerRepository;
import hello.neardeal_server.member.repository.OwnerRepository;
import hello.neardeal_server.store.service.StoreService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InitData {

    private final StoreService storeService;
    private final CustomerRepository customerRepository;
    private final OwnerRepository ownerRepository;


    @PostConstruct
    public void init() {

//        Customer customer1 = Customer.create(new CustomerRequest(PartnerCategory.EDUCATION));
//        Customer customer2 = Customer.create(new CustomerRequest(PartnerCategory.ARTS));
//        customerRepository.save(customer1);
//        customerRepository.save(customer2);
//
//        Owner owner1 = Owner.create(new OwnerRequest("123456"));
//        Owner owner2 = Owner.create(new OwnerRequest("456235"));
//        Owner owner3 = Owner.create(new OwnerRequest("879555"));
//        Owner save1 = ownerRepository.save(owner1);
//        Owner save2 = ownerRepository.save(owner2);
//        Owner save3 = ownerRepository.save(owner3);
    }

}

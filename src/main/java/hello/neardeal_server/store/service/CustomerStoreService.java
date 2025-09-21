package hello.neardeal_server.store.service;

import hello.neardeal_server.member.entity.Customer;
import hello.neardeal_server.member.service.MemberService;
import hello.neardeal_server.store.dto.response.StoreDetailResponse;
import hello.neardeal_server.store.entity.CustomerStore;
import hello.neardeal_server.store.entity.Store;
import hello.neardeal_server.store.repository.CustomerStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerStoreService {

    private final StoreService storeService;
    private final CustomerStoreRepository customerStoreRepository;
    private final MemberService memberService;

    /**
     * 좋아요 클릭 -> 만들기
     */
    @Transactional
    public Long activeLikeStore(Long storeId, Long customerId){
        Store store = storeService.findOne(storeId);
        Customer customer = memberService.findOneCustomer(customerId);
        Optional<CustomerStore> findCustomerStore = findOne(storeId, customerId);

        // 기존에 존재한다면
        if(findCustomerStore.isPresent()){
            CustomerStore customerStore = findCustomerStore.get();
            customerStore.like();
            return customerStore.getId();
        }

        // 기존에 없다면
        CustomerStore newCustomerStore = CustomerStore.create(customer, store);

        CustomerStore save = customerStoreRepository.save(newCustomerStore);

        return save.getId();

    }

    /**
     * 좋아요 해제
     */
    @Transactional
    public Long cancelLike(Long storeId, Long customerId){

        Optional<CustomerStore> findCustomerStore = findOne(storeId, customerId);

        if(findCustomerStore.isEmpty()){
            throw new RuntimeException("좋아요한 스토어가 없습니다");
        }

        CustomerStore customerStore = findCustomerStore.get();
        customerStore.unlike();

        return customerStore.getId();
    }

    /**
     * 내가 찜 한 스토어 리스트 반환
     */
    public Page<StoreDetailResponse> findMyCustomerStore(Long customerId, int page, int size){

        Page<CustomerStore> customerStorePage = customerStoreRepository.findByCustomerId(customerId, PageRequest.of(page, size));

        return customerStorePage.map(customerStore -> StoreDetailResponse.entityToResponse(customerStore.getStore()));
    }

    /**
     * 삭제
     */






    private Optional<CustomerStore> findOne(Long storeId, Long customerId) {

        return customerStoreRepository.findByCustomerIdAndStoreId(customerId, storeId);
    }



}

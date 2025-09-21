package hello.neardeal_server.store.repository;

import hello.neardeal_server.store.entity.CustomerStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerStoreRepository extends JpaRepository<CustomerStore, Long> {

    Optional<CustomerStore> findByCustomerIdAndStoreId(Long customerId, Long storeId);

    Page<CustomerStore> findByCustomerId(Long customerId, Pageable pageable);
}

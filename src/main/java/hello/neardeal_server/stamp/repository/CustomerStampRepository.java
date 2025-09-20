package hello.neardeal_server.stamp.repository;

import hello.neardeal_server.member.entity.Customer;
import hello.neardeal_server.stamp.entity.CustomerStamp;
import hello.neardeal_server.stamp.entity.Stamp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerStampRepository extends JpaRepository<CustomerStamp, Long> {
    Page<CustomerStamp> findByCustomer(Customer customer, Pageable pageable);

    boolean existsByCustomerAndStamp(Customer customer, Stamp stamp);

}


package hello.neardeal_server.member.repository;

import hello.neardeal_server.member.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

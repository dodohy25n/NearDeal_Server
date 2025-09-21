package hello.neardeal_server.coupon.repository;

import hello.neardeal_server.coupon.entity.CustomerCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerCouponRepository extends JpaRepository<CustomerCoupon, Long> {

    Optional<CustomerCoupon> findByIdAndCustomerId(Long id, Long customerId);

    Page<CustomerCoupon> findAllByCustomerId(Long customerId, Pageable pageable);
}

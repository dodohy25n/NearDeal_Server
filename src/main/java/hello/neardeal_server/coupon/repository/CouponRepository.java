package hello.neardeal_server.coupon.repository;

import hello.neardeal_server.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}

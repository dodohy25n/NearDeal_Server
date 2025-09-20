package hello.neardeal_server.coupon.entity;

import hello.neardeal_server.member.entity.Customer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_coupon_id")
    private Long id;

    @Column(nullable = false)
    private String couponCode;

    @Column(nullable = false)
    private CouponUsageStatus usageStatus = CouponUsageStatus.UNUSED;

    @CreationTimestamp
    private LocalDateTime createdAt; // 쿠폰 적립 일

    /* --- 관계 --- */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;
}

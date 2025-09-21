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

    private boolean isVisible = true;

    @CreationTimestamp
    private LocalDateTime createdAt; // 쿠폰 적립 일

    /* --- 관계 --- */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;

    /* --- 메서드 --- */
    
    public static CustomerCoupon create(Customer customer, Coupon coupon, String couponCode) {
        CustomerCoupon customerCoupon = new CustomerCoupon();
        customerCoupon.couponCode = couponCode;
        customerCoupon.addCustomerAndCoupon(customer, coupon);
        return customerCoupon;
    }

    public void use() {
        if (this.usageStatus != CouponUsageStatus.UNUSED) {
            throw new IllegalStateException("이미 사용되었거나 만료된 쿠폰입니다.");
        }
        this.usageStatus = CouponUsageStatus.USED;
    }

    public void toggleVisibility() {
        this.isVisible = !this.isVisible;
    }

    /* --- 연관관계 편의 메서드 --- */
    private void addCustomerAndCoupon(Customer customer, Coupon coupon) {
        this.customer = customer;
        customer.getCustomerCouponList().add(this);
        this.coupon = coupon;
    }
}

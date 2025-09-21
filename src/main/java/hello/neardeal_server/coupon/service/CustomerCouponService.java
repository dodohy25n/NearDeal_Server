package hello.neardeal_server.coupon.service;

import hello.neardeal_server.common.RandomCode;
import hello.neardeal_server.coupon.dto.CouponDetailResponse;
import hello.neardeal_server.coupon.dto.MyCouponDetailResponse;
import hello.neardeal_server.coupon.entity.Coupon;
import hello.neardeal_server.coupon.entity.CustomerCoupon;
import hello.neardeal_server.coupon.repository.CouponRepository;
import hello.neardeal_server.coupon.repository.CustomerCouponRepository;
import hello.neardeal_server.member.entity.Customer;
import hello.neardeal_server.member.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerCouponService {

    private final CouponRepository couponRepository;
    private final CustomerRepository customerRepository;
    private final CustomerCouponRepository customerCouponRepository;

    /**
     * 쿠폰 적립
     */
    public Long saveCustomerCoupon(Long couponId, Long customerId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("해당 쿠폰이 존재하지 않습니다."));
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 고객이 존재하지 않습니다."));

        String couponCode = RandomCode.numeric(8);

        CustomerCoupon customerCoupon = CustomerCoupon.create(customer, coupon, couponCode);
        CustomerCoupon savedCustomerCoupon = customerCouponRepository.save(customerCoupon);

        return savedCustomerCoupon.getId();
    }

    /**
     * 쿠폰 사용
     */
    public void useCoupon(Long customerCouponId, Long customerId) {
        CustomerCoupon customerCoupon = customerCouponRepository.findByIdAndCustomerId(customerCouponId, customerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 쿠폰을 찾을 수 없습니다."));
        customerCoupon.use();
    }

    /**
     * 쿠폰 숨기기/보이기
     */
    public void updateVisibleCoupon(Long customerCouponId, Long customerId) {
        CustomerCoupon customerCoupon = customerCouponRepository.findByIdAndCustomerId(customerCouponId, customerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 쿠폰을 찾을 수 없습니다."));

        customerCoupon.toggleVisibility();
    }

    /**
     * 내 쿠폰 목록 조회
     */


    @Transactional(readOnly = true)
    public Page<MyCouponDetailResponse> getMyCoupons(Long customerId, Pageable pageable) {
        Page<CustomerCoupon> customerCoupons = customerCouponRepository.findAllByCustomerId(customerId, pageable);
        return customerCoupons.map(MyCouponDetailResponse::of);
    }
}

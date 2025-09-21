package hello.neardeal_server.stamp.service;

import hello.neardeal_server.common.RandomCode;
import hello.neardeal_server.coupon.dto.CouponRequest;
import hello.neardeal_server.coupon.entity.Coupon;
import hello.neardeal_server.coupon.entity.CouponStatus;
import hello.neardeal_server.coupon.entity.CouponType;
import hello.neardeal_server.coupon.service.CouponService;
import hello.neardeal_server.coupon.service.CustomerCouponService;
import hello.neardeal_server.member.entity.Customer;
import hello.neardeal_server.member.repository.CustomerRepository;
import hello.neardeal_server.member.service.MemberService;
import hello.neardeal_server.stamp.dto.response.CustomerStampResponse;
import hello.neardeal_server.stamp.entity.CustomerStamp;
import hello.neardeal_server.stamp.entity.Stamp;
import hello.neardeal_server.stamp.repository.CustomerStampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerStampService {


    private final StampService stampService;
    private final CustomerStampRepository customerStampRepository;
    private final MemberService memberService;
    private final CouponService couponService;
    private final CustomerCouponService customerCouponService;

    /**
     * 스탬프 신청하기
     */
    @Transactional
    public Long requestStamp(Long stampId, Long customerId){
        Stamp stamp = stampService.findOne(stampId);

        // todo: customer 시큐리티 이용해서 받아오기
        Customer customer = memberService.findOneCustomer(customerId);

        boolean exist = customerStampRepository.existsByCustomerAndStamp(customer, stamp);
        if(exist){
            throw new RuntimeException("이미 발급한 쿠폰입니다.");
        }

        CustomerStamp customerStamp = CustomerStamp.create(customer, stamp);
        CustomerStamp save = customerStampRepository.save(customerStamp);
        return save.getId();
    }

    /**
     * 스탬프 적립하기
     */
    @Transactional
    public int addStamp(Long customerStampId, String code){
        CustomerStamp customerStamp = findOne(customerStampId);
        Stamp stamp = customerStamp.getStamp();
        String secretCode1 = stamp.getSecretCode();
//
//        if(!secretCode1.equals(code)){
//            return -2;
//        }

        // 비밀번호 교체
        String newSecretCode = RandomCode.clearAlphaNum4();
        stamp.updateSecretCode(newSecretCode);
        
        // 10개 채워지면 쿠폰으로 변경
        if(customerStamp.getCurrentCount() + 1 >= customerStamp.getStamp().getMaxCount()){
            CouponRequest couponRequest = new CouponRequest(stamp.getStore().getId(), stamp.getStore().getStoreName() + "의 스탬프 쿠폰", stamp.getReward(), CouponStatus.ACTIVE, CouponType.STAMP);
            Long couponId = couponService.createCoupon(couponRequest);
            Customer customer = customerStamp.getCustomer();
            customerCouponService.saveCustomerCoupon(couponId, customer.getId());

            customerStamp.changeCoupon();
            return -1;
        }

        return customerStamp.addStamp();
    }

    /**
     * 스탬프 숨기기/보이기
     */
    @Transactional
    public void toggleStamp(Long customerStampId){
        CustomerStamp customerStamp = findOne(customerStampId);
        customerStamp.toggleVisible();
    }

    /**
     * 내가 적립한 스탬프 목록 조회
     */
    public Page<CustomerStampResponse> findCustomStamp(Long customerId, int page, int size){
        Customer customer = memberService.findOneCustomer(customerId);

        Pageable pageable = PageRequest.of(
                page, size,
                Sort.by(Sort.Order.desc("isVisible")) // 보이는 것 먼저 정렬하기
        );
        Page<CustomerStamp> all = customerStampRepository.findByCustomer(customer, pageable);

        return all.map(customerStamp -> CustomerStampResponse.entityToResponse(customerStamp));
    }

    /**
     * 내 스탬프 삭제하기?
     */
    
    // 찾기
    public CustomerStamp findOne(Long customerStampId){
        return customerStampRepository.findById(customerStampId).orElseThrow(() -> new RuntimeException("너가 만든 스탬프 없음"));
    }

}

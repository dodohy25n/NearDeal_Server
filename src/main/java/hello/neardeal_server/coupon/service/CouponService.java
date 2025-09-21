package hello.neardeal_server.coupon.service;

import hello.neardeal_server.common.PageResponse;
import hello.neardeal_server.coupon.dto.CouponDetailResponse;
import hello.neardeal_server.coupon.dto.CouponRequest;
import hello.neardeal_server.coupon.entity.Coupon;
import hello.neardeal_server.coupon.repository.CouponRepository;
import hello.neardeal_server.coupon.repository.CustomerCouponRepository;
import hello.neardeal_server.member.repository.CustomerRepository;
import hello.neardeal_server.store.entity.Store;
import hello.neardeal_server.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final StoreRepository storeRepository;
    private final CustomerRepository customerRepository;
    private final CustomerCouponRepository customerCouponRepository;

    /**
     * 쿠폰등록
     */
    public Long createCoupon(CouponRequest request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("해당 상점이 존재하지 않습니다."));
        Coupon coupon = Coupon.create(request, store);
        Coupon savedCoupon = couponRepository.save(coupon);
        return savedCoupon.getId();
    }

    /**
     * 쿠폰 수정
     */
    public CouponDetailResponse updateCoupon(Long couponId, CouponRequest request) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("해당 쿠폰이 존재하지 않습니다."));
        coupon.update(request);
        return CouponDetailResponse.entityToResponse(coupon);
    }

    /**
     * 쿠폰 조회
     */
    public CouponDetailResponse getCoupon(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow(() -> new IllegalArgumentException("해당 쿠폰이 존재하지 않습니다."));
        return CouponDetailResponse.entityToResponse(coupon);
    }

    /**
     * 전체 쿠폰 목록 조회
     */
    public PageResponse<CouponDetailResponse> getCouponList(int page, int size) {
        Page<Coupon> coupons = couponRepository.findAll(PageRequest.of(page, size));
        Page<CouponDetailResponse> couponDetailResponsePage = coupons.map(CouponDetailResponse::entityToResponse);
        return PageResponse.pageToResponse(couponDetailResponsePage);
    }

    /**
     * 쿠폰 삭제
     */
    public void deleteCoupon(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("해당 쿠폰이 존재하지 않습니다."));
        couponRepository.delete(coupon);
    }


}

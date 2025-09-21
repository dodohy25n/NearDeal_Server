package hello.neardeal_server.coupon.entity;

import hello.neardeal_server.common.auditing.BaseTimeEntity;
import hello.neardeal_server.coupon.dto.CouponRequest;
import hello.neardeal_server.item.entity.Item;
import hello.neardeal_server.member.entity.PartnerCategory;
import hello.neardeal_server.store.entity.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String name; // 쿠폰 이름

    @Lob
    private String description; // 쿠폰 정보

    private Integer issuingQuantity; // 전체 수량

    private Integer remainingQuantity; // 남은 수량

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CouponStatus status = CouponStatus.DRAFT; // 쿠폰 상태

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CouponType type; // 쿠폰 종류 (정액, 정률)

    private Double discountedAmount; // 최대 할인액 (정률권) 또는 할인액 (정액권)

    private LocalDateTime couponStartDate; // 쿠폰 적용 시작일

    private LocalDateTime couponEndDate; // 쿠폰 적용 마감일

    private Integer limitPerPerson;

    private String notice;

    private PartnerCategory partnerCategory;

    /* --- 관계 --- */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    /* --- 메서드 --- */

    public static Coupon create(CouponRequest couponRequest, Store store) {
        Coupon coupon = new Coupon();
        coupon.name = couponRequest.getName();
        coupon.description = couponRequest.getDescription();
        coupon.issuingQuantity = couponRequest.getIssuingQuantity();
        coupon.remainingQuantity = couponRequest.getIssuingQuantity(); // 남은 수량은 전체 수량으로 설정
        coupon.status = couponRequest.getStatus();
        coupon.type = couponRequest.getType();
        coupon.discountedAmount = couponRequest.getDiscountedAmount();
        coupon.couponStartDate = couponRequest.getCouponStartDate();
        coupon.couponEndDate = couponRequest.getCouponEndDate();
        coupon.limitPerPerson = couponRequest.getLimitPerPerson();
        coupon.notice = couponRequest.getNotice();
        coupon.partnerCategory = couponRequest.getPartnerCategory();
        coupon.addStore(store); // 연관관계 설정
        coupon.store = store; // 연관관계 설정
        return coupon;
    }

    /**
     * 쿠폰 정보 변경
     */
    public Long update(CouponRequest couponRequest) {
        this.name = couponRequest.getName();
        this.description = couponRequest.getDescription();
        this.issuingQuantity = couponRequest.getIssuingQuantity();
        this.status = couponRequest.getStatus();
        this.type = couponRequest.getType();
        this.discountedAmount = couponRequest.getDiscountedAmount();
        this.couponStartDate = couponRequest.getCouponStartDate();
        this.couponEndDate = couponRequest.getCouponEndDate();
        this.limitPerPerson = couponRequest.getLimitPerPerson();
        this.notice = couponRequest.getNotice();
        this.partnerCategory = couponRequest.getPartnerCategory();

        return this.id;
    }

    /* --- 연관관계 편의 메서드 --- */

    private void addStore(Store store) {
        store.getCoupons().add(this);
        this.store = store;
    }


}

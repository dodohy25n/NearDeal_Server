package hello.neardeal_server.coupon.entity;

import hello.neardeal_server.item.entity.Item;
import hello.neardeal_server.member.entity.PartnerCategory;
import hello.neardeal_server.store.entity.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.security.Timestamp;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    @Column(nullable = false)
    private Long issuingQuantity;

    @Column(nullable = false)
    private Long remainingQuantity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CouponStatus couponStatus = CouponStatus.ACTIVE;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CouponType couponType = CouponType.PERCENT_DISCOUNT;

    private Double discountedPrice;    // CouponType이 PERCENT_DISCOUNT or PRICE_DISCOUNT 일 때

    private String serviceDescription; // CouponType이 SERVICE or ETC 일 때

    @Column(nullable = false)
    private String couponName;

    @Column(nullable = false)
    private String description;

//    @Column(nullable = false)
//    private String couponCode;

    @Column(nullable = false)
    private Long limitPerPerson;

    private String notice; // 유의사항

    @Column(nullable = false)
    private Timestamp couponStartDate;  // 쿠폰 적용 시작 일시

    @Column(nullable = false)
    private Timestamp couponEndDate;    // 쿠폰 적용 마감 일시

    private PartnerCategory partnerCategory;

    @CreationTimestamp
    private Timestamp createdAt;


    /* --- 관계 --- */
    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

}

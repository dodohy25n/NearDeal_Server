package hello.neardeal_server.member.entity;

import hello.neardeal_server.coupon.entity.CustomerCoupon;
import hello.neardeal_server.stamp.entity.CustomerStamp;
import hello.neardeal_server.store.entity.CustomerStore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(nullable = false)
    private PartnerCategory affiliation = PartnerCategory.NONE; // 소속

    /* --- 관계 --- */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "customer")
    private List<CustomerStamp> customerStampList = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<CustomerCoupon> customerCouponList = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<CustomerStore> customerStoreList = new ArrayList<>();
}

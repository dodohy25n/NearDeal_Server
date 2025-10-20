package hello.neardeal_server.store.entity;

import hello.neardeal_server.member.entity.PartnerCategory;
import hello.neardeal_server.store.StoreCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartnerStore {

    @Id
    @Column(name = "partner_store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PartnerCategory partnerCategory;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String partnerBenefit;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreCategory category;

    @Column(nullable = false)
    private String storeName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String openingTime;

    @Column(nullable = false)
    private String closingTime;

    private String breakStartTime;

    private String breakEndTime;

    private String lastOrder;

    @Column(nullable = false, precision = 16, scale = 13)
    private BigDecimal lat; // 위도

    @Column(nullable = false, precision = 16, scale = 13)
    private BigDecimal lng; // 경도
 
    private String introduce; // 간단 소개

    private String phone;
    
    private String etc; // 기타

    private String sns;
}

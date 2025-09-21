package hello.neardeal_server.coupon.dto;

import hello.neardeal_server.coupon.entity.Coupon;
import hello.neardeal_server.coupon.entity.CouponStatus;
import hello.neardeal_server.coupon.entity.CouponType;
import hello.neardeal_server.member.entity.PartnerCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Builder
@Getter
@Schema(description = "쿠폰 상세 정보 조회")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CouponDetailResponse {

    @Schema(description = "쿠폰 ID")
    private Long id;

    @Schema(description = "적용 상점 ID")
    private Long storeId;

    @Schema(description = "적용 상점 이름")
    private String storeName;

    @Schema(description = "적용 상품 ID")
    private Long itemId;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "설명")
    private String description;

    @Schema(description = "발급 수량")
    private Integer issuingQuantity;

    @Schema(description = "남은 수량")
    private Integer remainingQuantity;

    @Schema(description = "쿠폰 상태")
    private CouponStatus status;

    @Schema(description = "쿠폰 타입")
    private CouponType type;

    @Schema(description = "할인율 또는 할인 가격")
    private Double discountedAmount;

    @Schema(description = "쿠폰 적용 시작 일시")
    private LocalDateTime couponStartDate;

    @Schema(description = "쿠폰 적용 마감 일시")
    private LocalDateTime couponEndDate;

    @Schema(description = "1인 당 발급 제한 개수")
    private Integer limitPerPerson;

    @Schema(description = "유의사항")
    private String notice;

    @Schema(description = "제휴 카테고리")
    private PartnerCategory partnerCategory;

    @Schema(description = "쿠폰 등록 일시")
    private LocalDateTime createdAt;

    public static CouponDetailResponse entityToResponse(Coupon coupon) {
        return CouponDetailResponse.builder()
                .id(coupon.getId())
                .storeId(coupon.getStore().getId())
                .storeName(coupon.getStore().getStoreName())
                .itemId(coupon.getItem().getId())
                .name(coupon.getName())
                .description(coupon.getDescription())
                .issuingQuantity(coupon.getIssuingQuantity())
                .remainingQuantity(coupon.getRemainingQuantity())
                .status(coupon.getStatus())
                .type(coupon.getType())
                .discountedAmount(coupon.getDiscountedAmount())
                .couponStartDate(coupon.getCouponStartDate())
                .couponEndDate(coupon.getCouponEndDate())
                .limitPerPerson(coupon.getLimitPerPerson())
                .notice(coupon.getNotice())
                .partnerCategory(coupon.getPartnerCategory())
                .build();
    }
}

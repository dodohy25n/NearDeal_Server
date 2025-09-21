package hello.neardeal_server.coupon.dto;

import hello.neardeal_server.coupon.entity.CouponStatus;
import hello.neardeal_server.coupon.entity.CouponType;
import hello.neardeal_server.member.entity.PartnerCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Schema(description = "새 쿠폰 등록 요청")
public class CouponRequest {

    @Schema(description = "적용 상점 ID")
    private Long storeId;

    @Schema(description = "적용 상품 ID")
    private Long itemId;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "설명")
    private String description;

    @Schema(description = "발급 수량")
    private Integer issuingQuantity;

    @Schema(description = "쿠폰 상태")
    private CouponStatus status;

    @Schema(description = "쿠폰 종류")
    private CouponType type;

    @Schema(description = "할인액")
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
}

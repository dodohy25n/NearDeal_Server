package hello.neardeal_server.coupon.dto;

import hello.neardeal_server.coupon.entity.CouponType;
import hello.neardeal_server.coupon.entity.CouponUsageStatus;
import hello.neardeal_server.coupon.entity.CustomerCoupon;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@Schema(description = "내 쿠폰 상세 정보 조회")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MyCouponDetailResponse {

    @Schema(description = "고객 쿠폰 ID")
    private Long customerCouponId;

    @Schema(description = "쿠폰 이름")
    private String name;

    @Schema(description = "쿠폰 설명")
    private String description;

    @Schema(description = "쿠폰 코드")
    private String couponCode;

    @Schema(description = "쿠폰 사용 상태")
    private CouponUsageStatus usageStatus;

    @Schema(description = "쿠폰 노출 여부")
    private boolean isVisible;

    @Schema(description = "쿠폰 적립 일시")
    private LocalDateTime savedAt;

    @Schema(description = "쿠폰 종류")
    private CouponType type;

    @Schema(description = "할인율 또는 할인 가격")
    private Double discountedAmount;

    @Schema(description = "쿠폰 적용 시작 일시")
    private LocalDateTime couponStartDate;

    @Schema(description = "쿠폰 적용 마감 일시")
    private LocalDateTime couponEndDate;

    @Schema(description = "상점 이름")
    private String storeName;

    public static MyCouponDetailResponse of(CustomerCoupon customerCoupon) {
        return MyCouponDetailResponse.builder()
                .customerCouponId(customerCoupon.getId())
                .name(customerCoupon.getCoupon().getName())
                .description(customerCoupon.getCoupon().getDescription())
                .couponCode(customerCoupon.getCouponCode())
                .usageStatus(customerCoupon.getUsageStatus())
                .isVisible(customerCoupon.isVisible())
                .savedAt(customerCoupon.getCreatedAt())
                .type(customerCoupon.getCoupon().getType())
                .discountedAmount(customerCoupon.getCoupon().getDiscountedAmount())
                .couponStartDate(customerCoupon.getCoupon().getCouponStartDate())
                .couponEndDate(customerCoupon.getCoupon().getCouponEndDate())
                .storeName(customerCoupon.getCoupon().getStore().getStoreName())
                .build();
    }
}

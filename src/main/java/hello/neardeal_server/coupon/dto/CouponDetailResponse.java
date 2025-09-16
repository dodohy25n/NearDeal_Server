package hello.neardeal_server.coupon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.security.Timestamp;

@Getter
@Schema(description = "쿠폰 상세 정보 조회")
public class CouponDetailResponse {

    @Schema(description = "쿠폰 ID")
    private Long couponId;

    @Schema(description = "적용 상품")
    private Long itemId;

    @Schema(description = "발급 수량")
    private Long issuedQuantity;

    @Schema(description = "남은 수량")
    private Long remainingQuantity;

    @Schema(description = "쿠폰 타입")
    private String couponType;
    
    @Schema(description = "할인율 또는 할인 가격")
    private Double discountedPrice;

    @Schema(description = "이름")
    private String couponName;

    @Schema(description = "설명")
    private String description;

    @Schema(description = "바코드 번호")
    private String couponCode;

    @Schema(description = "1인 당 발급 제한 개수")
    private Long limitPerPerson;

    @Schema(description = "쿠폰 적용 시작 일시")
    private Timestamp couponStartDate;

    @Schema(description = "쿠폰 적용 마감 일시")
    private Timestamp couponEndDate;

    @Schema(description = "쿠폰 등록 일시")
    private Timestamp createdAt;

    @Schema(description = "제휴 카테고리")
    private String partnerCategory;
}

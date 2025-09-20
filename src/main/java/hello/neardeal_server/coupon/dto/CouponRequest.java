package hello.neardeal_server.coupon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.security.Timestamp;

@Getter
@Schema(description = "새 쿠폰 등록 요청")
public class CouponRequest {

    @Schema(description = "적용 상품")
    private Long itemId;

    @Schema(description = "발급 수량")
    private Long issuingQuantity;

    @Schema(description = "쿠폰 타입")
    private String couponType;

    @Schema(description = "이름")
    private String couponName;

    @Schema(description = "설명")
    private String description;

//    @Schema(description = "바코드 번호")
//    private String couponCode;

    @Schema(description = "1인 당 발급 제한 개수")
    private Long limitPerPerson;

    @Schema(description = "쿠폰 적용 시작 일시")
    private Timestamp couponApplyStartDate;

    @Schema(description = "쿠폰 적용 마감 일시")
    private Timestamp couponApplyEndDate;

    @Schema(description = "제휴 카테고리")
    private String partnerCategory;
}

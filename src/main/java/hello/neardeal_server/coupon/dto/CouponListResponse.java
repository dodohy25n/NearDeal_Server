package hello.neardeal_server.coupon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Schema(description = "쿠폰 페이징 목록 정보")
public class CouponListResponse {

    @Schema(description = "쿠폰 목록")
    private final List<CouponDetailResponse> coupons;

    @Schema(description = "현재 페이지 번호")
    private final int page;

    @Schema(description = "페이지 당 데이터 수")
    private final int size;

    @Schema(description = "전체 페이지 수")
    private final int totalPages;

    @Schema(description = "전체 데이터 수")
    private final long totalElements;
}
package hello.neardeal_server.coupon.controller;


import hello.neardeal_server.coupon.dto.CouponDetailResponse;
import hello.neardeal_server.coupon.dto.CouponListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "고객-쿠폰 매핑 컨트롤러", description = "고객-쿠폰 관련 API 엔드포인트")
@RequestMapping("/api/customer-coupon")
@RestController
public class CustomerCouponController {


    @PostMapping("/{couponId}/save")
    @Operation(summary = "[고객]쿠폰 적립", description = "쿠폰 적립하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "쿠폰 적립 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 쿠폰")
    })
    @Parameter(name = "couponId", description = "쿠폰 ID", required = true)
    public ResponseEntity<Void> saveCoupon(@PathVariable Long couponId) {
        return null;
    }

    @PatchMapping("/{couponId}")
    @Operation(summary = "[고객]쿠폰 사용하기", description = "쿠폰 사용하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "쿠폰 사용 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 쿠폰")
    })
    @Parameter(name = "couponId", description = "쿠폰 ID", required = true)
    public ResponseEntity<Void> useCoupon(@PathVariable Long couponId) {
        return null;
    }

    @PatchMapping("/{couponId}/visible")
    @Operation(summary = "[고객]쿠폰 숨기기 / 보이기", description = "쿠폰 숨김 또는 보임 설정하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "쿠폰 상태 변경 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 쿠폰")
    })
    @Parameter(name = "couponId", description = "쿠폰 ID", required = true)
    public ResponseEntity<Void> updateVisibleCoupon(@PathVariable Long couponId) {
        return null;
    }

    @GetMapping("/my")
    @Operation(summary = "[고객]내가 적립한 쿠폰 목록 조회", description = "내 적립 쿠폰 목록 페이징 조회하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "쿠폰 정보 조회 성공", content = @Content(schema = @Schema(implementation = CouponDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 쿠폰")
    })
    public ResponseEntity<CouponListResponse> getCouponList(
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 사이즈") @RequestParam(defaultValue = "10") int size
    ) {
        // This is a mock implementation
        List<CouponDetailResponse> mockCoupons = new ArrayList<>();

        Page<CouponDetailResponse> CouponPage = new PageImpl<>(mockCoupons, PageRequest.of(page, size), 0);

        CouponListResponse response = new CouponListResponse(
                CouponPage.getContent(),
                CouponPage.getNumber(),
                CouponPage.getSize(),
                CouponPage.getTotalPages(),
                CouponPage.getTotalElements()
        );
        return ResponseEntity.ok(response);
    }
}

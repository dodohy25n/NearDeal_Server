package hello.neardeal_server.coupon.controller;


import hello.neardeal_server.common.PageResponse;
import hello.neardeal_server.coupon.dto.CouponDetailResponse;
import hello.neardeal_server.coupon.dto.MyCouponDetailResponse;
import hello.neardeal_server.coupon.service.CouponService;
import hello.neardeal_server.coupon.service.CustomerCouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "고객-쿠폰 매핑 컨트롤러", description = "고객-쿠폰 관련 API 엔드포인트")
@RequestMapping("/api/customer-coupon")
@RestController
@RequiredArgsConstructor
public class CustomerCouponController {

    private final CustomerCouponService customerCouponService;

    @PostMapping("/{couponId}/save")
    @Operation(summary = "[고객]쿠폰 적립", description = "쿠폰 적립하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "쿠폰 적립 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 쿠폰")
    })
    @Parameter(name = "couponId", description = "쿠폰 ID", required = true)
    public ResponseEntity<Void> saveCoupon(@PathVariable Long couponId) {
        Long savedId = customerCouponService.saveCustomerCoupon(couponId, 1L); // 임시로 사용자 ID 1L 하드코딩
        return ResponseEntity.created(URI.create("/api/customer-coupon/my/" + savedId)).build();
    }

    @PatchMapping("/{couponId}/use")
    @Operation(summary = "[고객]쿠폰 사용하기", description = "쿠폰 사용하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "쿠폰 사용 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 쿠폰")
    })
    @Parameter(name = "couponId", description = "쿠폰 ID", required = true)
    public ResponseEntity<Void> useCoupon(@PathVariable Long couponId) {
        customerCouponService.useCoupon(couponId, 1L); // 임시로 사용자 ID 1L 하드코딩
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{couponId}/visible")
    @Operation(summary = "[고객]쿠폰 숨기기 / 보이기", description = "쿠폰 숨김 또는 보임 설정하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "쿠폰 상태 변경 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 쿠폰")
    })
    @Parameter(name = "couponId", description = "쿠폰 ID", required = true)
    public ResponseEntity<Void> updateVisibleCoupon(@PathVariable Long couponId) {
        customerCouponService.updateVisibleCoupon(couponId, 1L); // 임시로 사용자 ID 1L 하드코딩
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my")
    @Operation(summary = "[고객]내가 적립한 쿠폰 목록 조회", description = "내 적립 쿠폰 목록 페이징 조회하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "쿠폰 정보 조회 성공", content = @Content(schema = @Schema(implementation = PageResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 쿠폰")
    })
    public ResponseEntity<PageResponse<MyCouponDetailResponse>> getCouponList(
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 사이즈") @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MyCouponDetailResponse> myCoupons = customerCouponService.getMyCoupons(1L, pageable); // 임시로 사용자 ID 1L 하드코딩
        return ResponseEntity.ok(PageResponse.pageToResponse(myCoupons));
    }
}

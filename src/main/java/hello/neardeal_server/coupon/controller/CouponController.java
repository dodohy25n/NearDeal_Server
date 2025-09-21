package hello.neardeal_server.coupon.controller;

import hello.neardeal_server.common.PageResponse;
import hello.neardeal_server.coupon.dto.CouponDetailResponse;
import hello.neardeal_server.coupon.dto.CouponRequest;
import hello.neardeal_server.coupon.service.CouponService;
import hello.neardeal_server.member.dto.MemberDetailResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Tag(name = "쿠폰 컨트롤러", description = "쿠폰 관련 API 엔드포인트")
//@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@RequestMapping("/api/coupon")
@RestController
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping
    @Operation(summary = "쿠폰 등록", description = "새 쿠폰 등록 하기")
    //    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "새로운 쿠폰 등록 성공"),
            @ApiResponse(responseCode = "400", description = "쿠폰 등록 실패")
    })
    public ResponseEntity<Void> createCoupon(
            @Schema(implementation = CouponRequest.class) @RequestBody CouponRequest couponRequest
    ) {
        Long couponId = couponService.createCoupon(couponRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(couponId)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{couponId}")
    @Operation(summary = "쿠폰 정보 조회", description = "쿠폰 정보 조회하기")
    //    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "쿠폰 정보 조회 성공", content = @Content(schema = @Schema(implementation = MemberDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 쿠폰")
    })
    @Parameter(name = "couponId", description = "쿠폰 ID", required = true)
    public ResponseEntity<CouponDetailResponse> getCouponDetail(@PathVariable Long couponId) {
        return ResponseEntity.status(HttpStatus.OK).body(couponService.getCoupon(couponId));
    }

    @GetMapping
    @Operation(summary = "쿠폰 목록 조회", description = "쿠폰 목록 페이징 조회하기")
    //    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "쿠폰 목록 조회 성공", content = @Content(schema = @Schema(implementation = PageResponse.class)))
    })
    public ResponseEntity<PageResponse<CouponDetailResponse>> getCouponList(
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 사이즈") @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(couponService.getCouponList(page, size));
    }

    @PutMapping("/{couponId}")
    @Operation(summary = "쿠폰 정보 수정", description = "쿠폰 정보 수정하기")
    //    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "쿠폰 정보 수정 성공", content = @Content(schema = @Schema(implementation = CouponDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 쿠폰")
    })
    @Parameter(name = "couponId", description = "쿠폰 ID", required = true)
    public ResponseEntity<CouponDetailResponse> updateCouponDetail(@PathVariable Long couponId) {
        return null;
    }

    @DeleteMapping("/{couponId}")
    @Operation(summary = "쿠폰 정보 삭제", description = "쿠폰 정보 삭제하기")
    //    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "쿠폰 정보 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 쿠폰")
    })
    @Parameter(name = "couponId", description = "쿠폰 ID", required = true)
    public ResponseEntity<Void> deleteCoupon(@PathVariable Long couponId) {
        couponService.deleteCoupon(couponId);
        return ResponseEntity.noContent().build();
    }
}

package hello.neardeal_server.stamp.controller;

import hello.neardeal_server.common.PageResponse;
import hello.neardeal_server.stamp.dto.response.CustomerStampResponse;
import hello.neardeal_server.stamp.service.CustomerStampService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "고객-스탬프 매핑 컨트롤러", description = "고객-스탬프 관련 API 엔드포인트")
@RequestMapping("/api/customer-stamp")
@RequiredArgsConstructor
@RestController
public class CustomerStampController {

    private final CustomerStampService customerStampService;

    @PostMapping("/{stampId}/save/{customerId}")
    @Operation(summary = "[고객]스탬프 만들기", description = "스탬프 만들기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "스탬프 만들기 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 스탬프")
    })
    public ResponseEntity<Long> saveStamp(
            @Parameter(name = "stampId", description = "스탬프 ID", required = true) @PathVariable Long stampId,
            @Parameter(name = "customerId", description = "고객 ID", required = true) @PathVariable Long customerId
    ) {
        Long customerStampId = customerStampService.requestStamp(stampId, customerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerStampId);
    }


    @PutMapping("/{customerStampId}")
    @Operation(summary = "[고객]스탬프 적립", description = "스탬프 적립하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "스탬프 적립 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 스탬프")
    })
    public ResponseEntity<Integer> addCustomerStamp(
            @Parameter(name = "customerStampId", description = "스탬프 ID", required = true) @PathVariable Long customerStampId
    ) {
        int stampCount = customerStampService.addStamp(customerStampId);
        return ResponseEntity.status(HttpStatus.OK).body(stampCount);
    }

    @PatchMapping("/{customerStampId}/visible")
    @Operation(summary = "[고객]스탬프 숨기기 / 보이기", description = "스탬프 숨김 또는 보임 설정하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "스탬프 상태 변경 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 스탬프")
    })
    public ResponseEntity<String> updateVisibleStamp(
            @Parameter(name = "stampId", description = "고객의 스탬프 ID", required = true) @PathVariable Long customerStampId
    ) {
        customerStampService.toggleStamp(customerStampId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("해당 스탬프의 모습을 토글하였습니다.");
    }

    @GetMapping("/{customerId}")
    @Operation(summary = "[고객]내가 적립한 스탬프 목록 조회", description = "내 적립 스탬프 목록 페이징 조회하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "스탬프 정보 조회 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 스탬프")
    })
    public ResponseEntity<PageResponse<CustomerStampResponse>> getCustomerStampList(
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 사이즈") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "고객ID") @PathVariable Long customerId
    ) {

        Page<CustomerStampResponse> all = customerStampService.findCustomStamp(customerId,page, size);
        PageResponse<CustomerStampResponse> result = PageResponse.pageToResponse(all);
        return ResponseEntity.ok(result);

    }


}

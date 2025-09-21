package hello.neardeal_server.store.controller;

import hello.neardeal_server.common.PageResponse;
import hello.neardeal_server.store.dto.response.StoreDetailResponse;
import hello.neardeal_server.store.service.CustomerStoreService;
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

@Tag(name = "고객 상점 컨트롤러", description = "고객 상점 관련 API 엔드포인트")
@RequestMapping("/api/customer-store")
@RestController
@RequiredArgsConstructor
public class CustomerStoreController {

    private final CustomerStoreService customerStoreService;

    @PostMapping( "/{storeId}/{customerId}")
    @Operation(summary = "상점 좋아요", description = "상점의 찜하기 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "찜하기 성공"),
            @ApiResponse(responseCode = "400", description = "찜하기 실패")
    })
    public ResponseEntity<Long> createCustomStore(
            @PathVariable Long storeId,
            @PathVariable Long customerId
    ) {

        Long customerStoreId = customerStoreService.activeLikeStore(storeId, customerId);

        return ResponseEntity.status(HttpStatus.CREATED).body(customerStoreId);
    }

    @PutMapping("/{storeId}/{customerId}")
    @Operation(summary = "좋아요 해제", description = "상점 찜하기 해제 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "찜해제 성공"),
            @ApiResponse(responseCode = "404", description = "찜해제 실패")
    })
    public ResponseEntity<Long> getCustomStoreDetail(
            @PathVariable Long storeId,
            @PathVariable Long customerId
    ) {

        Long customerStoreId = customerStoreService.cancelLike(storeId, customerId);

        return ResponseEntity.status(HttpStatus.OK).body(customerStoreId);
    }


    @GetMapping("/{customerId}")
    @Operation(summary = "찜한 상점 목록 조회", description = "찜 한 상점 목록 조회 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상점 목록 조회 성공")
    })
    public ResponseEntity<PageResponse<StoreDetailResponse>> getCustomStoreList(
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 사이즈") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "고객 ID") @PathVariable("customerId") Long customerId
    ) {

        Page<StoreDetailResponse> filterCustomerStore = customerStoreService.findMyCustomerStore(customerId, page, size);
        PageResponse<StoreDetailResponse> result = PageResponse.pageToResponse(filterCustomerStore);

        return ResponseEntity.ok(result);
    }


}

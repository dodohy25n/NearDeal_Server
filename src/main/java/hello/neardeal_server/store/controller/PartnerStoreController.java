package hello.neardeal_server.store.controller;

import hello.neardeal_server.common.PageResponse;
import hello.neardeal_server.member.entity.PartnerCategory;
import hello.neardeal_server.store.dto.response.PartnerStoreDetailResponse;
import hello.neardeal_server.store.dto.response.StoreDetailResponse;
import hello.neardeal_server.store.service.PartnerStoreService;
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

@Tag(name = "제휴 상점 컨트롤러", description = "제휴 상점 관련 API (임시, 추후 상점 관련 API와 통합)")
@RequestMapping("/api/partner-store")
@RestController
@RequiredArgsConstructor
public class PartnerStoreController {

    private final PartnerStoreService partnerStoreService;

    @GetMapping
    @Operation(summary = "[소비자]제휴 상점 필터링 목록 조회", description = "상점 필터링 목록 페이징 조회하기, 카테고리 안넣으면 전체 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상점 목록 조회 성공")
    })
    public ResponseEntity<PageResponse<PartnerStoreDetailResponse>> getStoreList(
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 사이즈") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "필터링 할 카테고리") @RequestParam(required = false) PartnerCategory partnerCategory
    ) {

        Page<PartnerStoreDetailResponse> filerStore = partnerStoreService.findFilerStore(partnerCategory, size, page);
        PageResponse<PartnerStoreDetailResponse> result = PageResponse.pageToResponse(filerStore);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{storeId}")
    @Operation(summary = "[소비자]제휴 상점 상세 조회", description = "제휴 상점 상세 조회하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "제휴 상점 상세 조회 성공")
    })
    @Parameter(name = "storeId", description = "상점 ID", required = true)
    public ResponseEntity<PartnerStoreDetailResponse> getStoreDetail(
            @PathVariable Long storeId
    ) {
        PartnerStoreDetailResponse storeDetail = partnerStoreService.findStoreDetail(storeId);
        return ResponseEntity.status(HttpStatus.OK).body(storeDetail);
    }
}

package hello.neardeal_server.stamp.controller;

import hello.neardeal_server.stamp.dto.StampDetailResponse;
import hello.neardeal_server.stamp.dto.StampListResponse;
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

@Tag(name = "고객-스탬프 매핑 컨트롤러", description = "고객-스탬프 관련 API 엔드포인트")
@RequestMapping("/api/customer-stamp")
@RestController
public class CustomerStampController {

    @PostMapping("/{stampId}/save")
    @Operation(summary = "[고객]스탬프 적립", description = "스탬프 적립하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "스탬프 적립 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 스탬프")
    })
    @Parameter(name = "stampId", description = "스탬프 ID", required = true)
    public ResponseEntity<Void> saveStamp(@PathVariable Long stampId) {
        return null;
    }

    @PatchMapping("/{stampId}/visible")
    @Operation(summary = "[고객]스탬프 숨기기 / 보이기", description = "스탬프 숨김 또는 보임 설정하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "스탬프 상태 변경 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 스탬프")
    })
    @Parameter(name = "stampId", description = "스탬프 ID", required = true)
    public ResponseEntity<Void> updateVisibleStamp(@PathVariable Long stampId) {
        return null;
    }

    @GetMapping("/my")
    @Operation(summary = "[고객]내가 적립한 스탬프 목록 조회", description = "내 적립 스탬프 목록 페이징 조회하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "스탬프 정보 조회 성공", content = @Content(schema = @Schema(implementation = StampDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 스탬프")
    })
    public ResponseEntity<StampListResponse> getStampList(
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 사이즈") @RequestParam(defaultValue = "10") int size
    ) {
        // This is a mock implementation
        List<StampDetailResponse> mockStamps = new ArrayList<>();

        Page<StampDetailResponse> stampPage = new PageImpl<>(mockStamps, PageRequest.of(page, size), 0);

        StampListResponse response = new StampListResponse(
                stampPage.getContent(),
                stampPage.getNumber(),
                stampPage.getSize(),
                stampPage.getTotalPages(),
                stampPage.getTotalElements()
        );
        return ResponseEntity.ok(response);
    }

}

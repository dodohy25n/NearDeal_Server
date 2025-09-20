package hello.neardeal_server.stamp.controller;

import hello.neardeal_server.common.PageResponse;
import hello.neardeal_server.stamp.dto.response.StampInfoResponse;
import hello.neardeal_server.stamp.dto.request.StampRequest;
import hello.neardeal_server.stamp.service.StampService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "스탬프 컨트롤러", description = "스탬프 관련 API 엔드포인트")
@RequestMapping("/api/stamp")
@RestController
public class StampController {

    private final StampService stampService;

    public StampController(StampService stampService) {
        this.stampService = stampService;
    }

    @PostMapping("/{storeId}")
    @Operation(summary = "[점주]스탬프 등록", description = "새 스탬프 등록 하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "새로운 스탬프 등록 성공"),
            @ApiResponse(responseCode = "400", description = "스탬프 등록 실패")
    })
    public ResponseEntity<Object> createStamp(
            @RequestBody StampRequest stampRequest,
            @PathVariable(name = "storeId") Long storeId
    ) {
        Long stampId = stampService.createStamp(stampRequest, storeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(stampId);
    }

    @GetMapping("/{stampId}")
    @Operation(summary = "[점주/고객]스탬프 정보 조회", description = "스탬프 정보 조회하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "스탬프 정보 조회 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 스탬프")
    })
    @Parameter(name = "stampId", description = "스탬프 ID", required = true)
    public ResponseEntity<StampInfoResponse> getStampInfo(
            @PathVariable Long stampId
    ) {
        StampInfoResponse stampInfo = stampService.findStampInfo(stampId);
        return ResponseEntity.ok(stampInfo);
    }

    @GetMapping("/list/{storeId}")
    @Operation(summary = "[점주/고객]가게의 전체 스탬프 정보 조회", description = "가게의 전체 스탬프 정보 조회하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "가게의 전체 스탬프 정보 조회 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 가게일지도?")
    })
    @Parameter(name = "stampId", description = "스탬프 ID", required = true)
    public ResponseEntity<PageResponse<StampInfoResponse>> getStampByStore(
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 사이즈") @RequestParam(defaultValue = "10") int size,
            @PathVariable Long storeId
    ) {
        Page<StampInfoResponse> all = stampService.findAllStampInfoByStore(storeId, page, size);
        PageResponse<StampInfoResponse> result = PageResponse.pageToResponse(all);
        return ResponseEntity.ok(result);
    }


    @PutMapping("/{stampId}")
    @Operation(summary = "[점주]스탬프 정보 수정", description = "스탬프 정보 수정하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "스탬프 정보 수정 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 스탬프")
    })
    @Parameter(name = "stampId", description = "스탬프 ID", required = true)
    public ResponseEntity<Long> updateStampDetail(
            @PathVariable Long stampId,
            @RequestBody StampRequest stampRequest
    ) {
        Long result = stampService.updateStampInfo(stampId, stampRequest);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{stampId}/password")
    @Operation(summary = "[점주]스탬프 비밀번호 조회", description = "스탬프 비밀번호 조회하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "스탬프 비밀번호 조회 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 스탬프")
    })
    @Parameter(name = "stampId", description = "스탬프 ID", required = true)
    public ResponseEntity<String> getStampPassword(
            @PathVariable Long stampId
    ) {
        String secretCode = stampService.findSecretCode(stampId);
        return ResponseEntity.ok(secretCode);
    }

    @DeleteMapping("/{stampId}")
    @Operation(summary = "스탬프 삭제", description = "상점 삭제하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "스탬프 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 스탬프")
    })
    @Parameter(name = "stampId", description = "상점 ID", required = true)
    public ResponseEntity<String> deleteStore(
            @PathVariable Long stampId
    ) {
        stampService.deleteStampInfo(stampId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("상점 삭제완료");
    }
}
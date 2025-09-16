package hello.neardeal_server.stamp;

import hello.neardeal_server.stamp.dto.StampDetailResponse;
import hello.neardeal_server.stamp.dto.StampListResponse;
import hello.neardeal_server.stamp.dto.StampRequest;
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

@Tag(name = "스탬프 컨트롤러", description = "스탬프 관련 API 엔드포인트")
@RequestMapping("/api/stamp")
@RestController
public class StampController {

    @PostMapping
    @Operation(summary = "스탬프 등록", description = "새 스탬프 등록 하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "새로운 스탬프 등록 성공"),
            @ApiResponse(responseCode = "400", description = "스탬프 등록 실패")
    })
    public ResponseEntity<Object> createStamp(@RequestBody StampRequest stampRequest) {
        return null;
    }

    @GetMapping("/{stampId}")
    @Operation(summary = "스탬프 정보 조회", description = "스탬프 정보 조회하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "스탬프 정보 조회 성공", content = @Content(schema = @Schema(implementation = StampDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 스탬프")
    })
    @Parameter(name = "stampId", description = "스탬프 ID", required = true)
    public ResponseEntity<StampDetailResponse> getStampDetail(@PathVariable Long stampId) {
        return null;
    }

    @GetMapping
    @Operation(summary = "스탬프 목록 조회", description = "스탬프 목록 페이징 조회하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "스탬프 목록 조회 성공", content = @Content(schema = @Schema(implementation = StampListResponse.class)))
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

    @PutMapping("/{stampId}")
    @Operation(summary = "스탬프 정보 수정", description = "스탬프 정보 수정하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "스탬프 정보 수정 성공", content = @Content(schema = @Schema(implementation = StampDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 스탬프")
    })
    @Parameter(name = "stampId", description = "스탬프 ID", required = true)
    public ResponseEntity<StampDetailResponse> updateStampDetail(@PathVariable Long stampId, @RequestBody StampRequest stampRequest) {
        return null;
    }

    @DeleteMapping("/{stampId}")
    @Operation(summary = "스탬프 정보 삭제", description = "스탬프 정보 삭제하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "스탬프 정보 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 스탬프")
    })
    @Parameter(name = "stampId", description = "스탬프 ID", required = true)
    public ResponseEntity<Void> deleteStamp(@PathVariable Long stampId) {
        return null;
    }
}
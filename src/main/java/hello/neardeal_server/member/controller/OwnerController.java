package hello.neardeal_server.member.controller;


import hello.neardeal_server.member.dto.OwnerDetailResponse;
import hello.neardeal_server.member.dto.OwnerListResponse;
import hello.neardeal_server.member.dto.SignupRequest;
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

@Tag(name = "점주 컨트롤러", description = "점주 관련 API 엔드포인트")
//@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@RequestMapping("/api/owner")
@RestController
public class OwnerController {


    @PostMapping()
    @Operation(summary = "점주 정보 등록", description = "점주 정보 등록하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "점주 가입 성공"),
            @ApiResponse(responseCode = "400", description = "점주 가입 실패")
    })
    public ResponseEntity<Object> register(
            @Schema(implementation = SignupRequest.class) @RequestBody SignupRequest signupRequest
    ) {
        return null;
    }

    @GetMapping("/{ownerId}")
    @Operation(summary = "점주 정보 조회", description = "점주 정보 조회하기")
    //    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "점주 정보 조회 성공", content = @Content(schema = @Schema(implementation = OwnerDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 점주")
    })
    @Parameter(name = "ownerId", description = "점주 ID", required = true)
    public ResponseEntity<OwnerDetailResponse> getOwnerDetail(@PathVariable Long ownerId) {

        return null;
    }

    @GetMapping
    @Operation(summary = "점주 목록 조회", description = "점주 목록 페이징 조회하기")
    //    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "점주 목록 조회 성공", content = @Content(schema = @Schema(implementation = OwnerListResponse.class)))
    })
    public ResponseEntity<OwnerListResponse> getOwnerList(
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 사이즈") @RequestParam(defaultValue = "10") int size
    ) {
        // This is a mock implementation
        List<OwnerDetailResponse> mockOwners = new ArrayList<>();

        Page<OwnerDetailResponse> ownerPage = new PageImpl<>(mockOwners, PageRequest.of(page, size), 0);

        OwnerListResponse response = new OwnerListResponse(
//                ownerPage.getContent(),
//                ownerPage.getNumber(),
//                ownerPage.getSize(),
//                ownerPage.getTotalPages(),
//                ownerPage.getTotalElements()
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{storeId}")
    @Operation(summary = "점주 정보 수정", description = "점주 정보 수정하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "점주 정보 수정 성공", content = @Content(schema = @Schema(implementation = OwnerDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 점주")
    })
    @Parameter(name = "ownerId", description = "점주 ID", required = true)
    public ResponseEntity<OwnerDetailResponse> updateStoreDetail(@PathVariable Long ownerId, @RequestBody SignupRequest signupRequest) {
        return null;
    }
}

package hello.neardeal_server.member.controller;

import hello.neardeal_server.member.dto.MemberListResponse;
import hello.neardeal_server.member.dto.MemberDetailResponse;
import hello.neardeal_server.member.dto.SignupRequest;
import hello.neardeal_server.store.dto.StoreDetailResponse;
import hello.neardeal_server.store.dto.StoreRequest;
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

@Tag(name = "회원 컨트롤러", description = "회원 관련 API 엔드포인트")
//@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@RequestMapping("/api/member")
@RestController
public class MemberController {

    @PostMapping("/signup")
    @Operation(summary = "회원 가입", description = "회원 가입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원 가입 성공"),
            @ApiResponse(responseCode = "400", description = "회원 가입 실패")
    })
    public ResponseEntity<Object> signup(
            @Schema(implementation = SignupRequest.class) @RequestBody SignupRequest signupRequest
    ) {
        return null;
    }

    @GetMapping("/{memberId}")
    @Operation(summary = "회원 정보 조회", description = "회원 정보 조회하기")
    //    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 정보 조회 성공", content = @Content(schema = @Schema(implementation = MemberDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 회원")
    })
    @Parameter(name = "memberId", description = "회원 ID", required = true)
    public ResponseEntity<MemberDetailResponse> getMemberDetail(@PathVariable Long memberId) {

        return null;
    }

    @GetMapping
    @Operation(summary = "회원 목록 조회", description = "회원 목록 페이징 조회하기")
    //    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 목록 조회 성공", content = @Content(schema = @Schema(implementation = MemberListResponse.class)))
    })
    public ResponseEntity<MemberListResponse> getMemberList(
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 사이즈") @RequestParam(defaultValue = "10") int size
    ) {
        // This is a mock implementation
        List<MemberDetailResponse> mockMembers = new ArrayList<>();

        Page<MemberDetailResponse> memberPage = new PageImpl<>(mockMembers, PageRequest.of(page, size), 0);

        MemberListResponse response = new MemberListResponse(
                memberPage.getContent(),
                memberPage.getNumber(),
                memberPage.getSize(),
                memberPage.getTotalPages(),
                memberPage.getTotalElements()
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{storeId}")
    @Operation(summary = "회원 정보 수정", description = "회원 정보 수정하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원 정보 수정 성공", content = @Content(schema = @Schema(implementation = MemberDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 회원")
    })
    @Parameter(name = "memberId", description = "회원 ID", required = true)
    public ResponseEntity<MemberDetailResponse> updateStoreDetail(@PathVariable Long memberId, @RequestBody SignupRequest signupRequest) {
        return null;
    }
}
package hello.neardeal_server.member.controller;

import hello.neardeal_server.member.dto.*;
import hello.neardeal_server.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "회원 컨트롤러", description = "회원 관련 API 엔드포인트")
//@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@RequestMapping("/api/member")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    @Operation(summary = "회원 가입", description = "회원 가입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원 가입 성공"),
            @ApiResponse(responseCode = "400", description = "회원 가입 실패")
    })
    public ResponseEntity<Long> signup(
            @Schema(implementation = SignupRequest.class) @RequestBody SignupRequest signupRequest
    ) {
        Long memberId = memberService.signup(signupRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberId);
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
        MemberDetailResponse memberDetailResponse = memberService.getMemberDetail(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(memberDetailResponse);
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

        return ResponseEntity.status(HttpStatus.OK).body(memberService.getMemberList(page, size));
    }

    @PutMapping("/{memberId}")
    @Operation(summary = "회원 정보 수정", description = "회원 정보 수정하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 정보 수정 성공", content = @Content(schema = @Schema(implementation = MemberDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 회원")
    })
    @Parameter(name = "memberId", description = "회원 ID", required = true)
    public ResponseEntity<MemberDetailResponse> updateMember(@PathVariable Long memberId, @RequestBody MemberUpdateRequest request) {
        MemberDetailResponse memberDetailResponse = memberService.updateMember(memberId, request);
        return ResponseEntity.status(HttpStatus.OK).body(memberDetailResponse);
    }

    @PostMapping("/customer")
    @Operation(summary = "고객 정보 등록", description = "고객 정보 등록하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "고객 가입 성공"),
            @ApiResponse(responseCode = "400", description = "고객 가입 실패")
    })
    public ResponseEntity<Long> register(
            @Schema(implementation = CustomerRequest.class) @RequestBody CustomerRequest customerRequest
    ) {
        Long customerId = memberService.registerCustomer(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerId);
    }

    @PostMapping("/owner")
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
}
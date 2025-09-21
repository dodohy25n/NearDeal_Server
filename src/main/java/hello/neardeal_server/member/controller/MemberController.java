package hello.neardeal_server.member.controller;

import hello.neardeal_server.common.PageResponse;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @ApiResponse(responseCode = "200", description = "회원 목록 조회 성공", content = @Content(schema = @Schema(implementation = PageResponse.class)))
    })
    public ResponseEntity<PageResponse<MemberDetailResponse>> getMemberList(
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


    @GetMapping("/customer/{customerId}")
    @Operation(summary = "고객 정보 조회", description = "고객 정보 조회하기")
    //    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "고객 정보 조회 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 고객")
    })
    @Parameter(name = "customerId", description = "고객 ID", required = true)
    public ResponseEntity<CustomerDetailResponse> getCustomerDetail(@PathVariable Long customerId) {
        CustomerDetailResponse customerDetail = memberService.getCustomerDetail(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetail);
    }

    @GetMapping("/customer")
    @Operation(summary = "고객 목록 조회", description = "고객 목록 페이징 조회하기")
    //    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "고객 목록 조회 성공")
    })
    public ResponseEntity<PageResponse<CustomerDetailResponse>> getCustomerList(
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 사이즈") @RequestParam(defaultValue = "10") int size
    ) {
        Page<CustomerDetailResponse> customerPage = memberService.getCustomerList(page, size);
        PageResponse<CustomerDetailResponse> result = PageResponse.pageToResponse(customerPage);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/customer/{customerId}")
    @Operation(summary = "고객 정보 수정", description = "고객 정보 수정하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "고객 정보 수정 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 고객")
    })
    @Parameter(name = "customerId", description = "고객 ID", required = true)
    public ResponseEntity<String> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerRequest request) {
        memberService.updateCustomer(customerId, request);
        return ResponseEntity.status(HttpStatus.OK).body("정보가 갱신되었습니다");
    }







    @PostMapping("/owner")
    @Operation(summary = "점주 정보 등록", description = "점주 정보 등록하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "점주 가입 성공"),
            @ApiResponse(responseCode = "400", description = "점주 가입 실패")
    })
    public ResponseEntity<Long> register(
            @Schema(implementation = OwnerRequest.class) @RequestBody OwnerRequest request
    ) {
        Long ownerId = memberService.registerOwner(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ownerId);
    }

    @GetMapping("/owner/{ownerId}")
    @Operation(summary = "점주 정보 조회", description = "점주 정보 조회하기")
    //    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "점주 정보 조회 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 점주")
    })
    @Parameter(name = "ownerId", description = "점주 ID", required = true)
    public ResponseEntity<OwnerDetailResponse> getOwnerDetail(@PathVariable Long ownerId) {
        OwnerDetailResponse result = memberService.getOwnerDetail(ownerId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/owner")
    @Operation(summary = "점주 목록 조회", description = "점주 목록 페이징 조회하기")
    //    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "점주 목록 조회 성공")
    })
    public ResponseEntity<PageResponse<OwnerDetailResponse>> getOwnerList(
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 사이즈") @RequestParam(defaultValue = "10") int size
    ) {
        Page<OwnerDetailResponse> ownerPage = memberService.getOwnerList(page, size);
        PageResponse<OwnerDetailResponse> result = PageResponse.pageToResponse(ownerPage);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/owner/{ownerId}")
    @Operation(summary = "점주 정보 수정", description = "점주 정보 수정하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "점주 정보 수정 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 점주")
    })
    @Parameter(name = "ownerId", description = "점주 ID", required = true)
    public ResponseEntity<Long> updateOwner(@PathVariable Long ownerId, @RequestBody OwnerRequest request) {
        Long result = memberService.updateOwner(ownerId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }




}
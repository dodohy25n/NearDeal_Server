package hello.neardeal_server.store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.neardeal_server.stamp.dto.StampDetailResponse;
import hello.neardeal_server.stamp.dto.StampListResponse;
import hello.neardeal_server.store.dto.StoreDetailResponse;
import hello.neardeal_server.store.dto.StoreListResponse;
import hello.neardeal_server.store.dto.StoreRequest;
import hello.neardeal_server.store.service.StoreService;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "상점 컨트롤러", description = "상점 관련 API 엔드포인트")
@RequestMapping("/api/store")
@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final ObjectMapper objectMapper; // LocalDateTime 읽기 위해서

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "[점주]상점 등록", description = "새 상점 등록 하기, form-data로 보내야함, store, files 분리시켜서")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "새로운 상점 등록 성공"),
            @ApiResponse(responseCode = "400", description = "상점 등록 실패")
    })
    public ResponseEntity<Long> createStore(
//            @RequestPart("store") StoreRequest storeRequest,
            @RequestPart("store") String storeJson,
            @RequestPart("files") MultipartFile uploadFiles
    ) throws com.fasterxml.jackson.core.JsonProcessingException  {

//        Long storeId = storeService.createStore(storeRequest, uploadFiles);

        StoreRequest dto = objectMapper.readValue(storeJson, StoreRequest.class);
        Long storeId = storeService.createStore(dto, uploadFiles);

        return ResponseEntity.status(HttpStatus.CREATED).body(storeId);
    }

    @GetMapping("/{storeId}")
    @Operation(summary = "[소비자, 점주]상점 정보 조회", description = "상점 정보 조회하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상점 정보 조회 성공", content = @Content(schema = @Schema(implementation = StoreDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 상점")
    })
    @Parameter(name = "storeId", description = "상점 ID", required = true)
    public ResponseEntity<StoreDetailResponse> getStoreDetail(@PathVariable Long storeId) {

        StoreDetailResponse storeDetail = storeService.findStoreDetail(storeId);

        return ResponseEntity.status(HttpStatus.OK).body(storeDetail);
    }

    @GetMapping
    @Operation(summary = "[소비자]상점 전체 목록 조회", description = "상점 전체 목록 페이징 조회하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상점 목록 조회 성공", content = @Content(schema = @Schema(implementation = StoreListResponse.class)))
    })
    public ResponseEntity<StoreListResponse> getStoreList(
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 사이즈") @RequestParam(defaultValue = "10") int size
    ) {
        // This is a mock implementation
        List<StoreDetailResponse> mockStores = new ArrayList<>();

        Page<StoreDetailResponse> storePage = new PageImpl<>(mockStores, PageRequest.of(page, size), 0);

        StoreListResponse response = new StoreListResponse(
                storePage.getContent(),
                storePage.getNumber(),
                storePage.getSize(),
                storePage.getTotalPages(),
                storePage.getTotalElements()
        );
        return ResponseEntity.ok(response);
    }


    @GetMapping("/filter")
    @Operation(summary = "[소비자]상점 필터링 목록 조회", description = "상점 필터링 목록 페이징 조회하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상점 목록 조회 성공", content = @Content(schema = @Schema(implementation = StoreListResponse.class)))
    })
    public ResponseEntity<StoreListResponse> getStoreList(
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 사이즈") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "필터링 할 카테고리") @RequestParam String category
    ) {
        // This is a mock implementation
        List<StoreDetailResponse> mockStores = new ArrayList<>();

        Page<StoreDetailResponse> storePage = new PageImpl<>(mockStores, PageRequest.of(page, size), 0);

        StoreListResponse response = new StoreListResponse(
                storePage.getContent(),
                storePage.getNumber(),
                storePage.getSize(),
                storePage.getTotalPages(),
                storePage.getTotalElements()
        );
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{storeId}")
    @Operation(summary = "[점주]상점 정보 수정", description = "상점 정보 수정하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "상점 정보 수정 성공", content = @Content(schema = @Schema(implementation = StoreDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 상점")
    })
    @Parameter(name = "storeId", description = "상점 ID", required = true)
    public ResponseEntity<StoreDetailResponse> updateStoreDetail(@PathVariable Long storeId, @RequestBody StoreRequest storeRequest) {
        return null;
    }

    @DeleteMapping("/{storeId}")
    @Operation(summary = "[점주]상점 정보 삭제", description = "상점 정보 삭제하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "상점 정보 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 상점")
    })
    @Parameter(name = "storeId", description = "상점 ID", required = true)
    public ResponseEntity<Void> deleteStore(@PathVariable Long storeId) {
        return null;
    }


    @GetMapping("/{storeId}/stamp")
    @Operation(summary = "[점주]내 가게 스탬프 목록 조회", description = "내 가게 스탬프 목록 페이징 조회하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "스탬프 목록 조회 성공", content = @Content(schema = @Schema(implementation = StampListResponse.class)))
    })
    public ResponseEntity<StampListResponse> getStampList(
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 사이즈") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "가게 ID") @PathVariable Long storeId
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


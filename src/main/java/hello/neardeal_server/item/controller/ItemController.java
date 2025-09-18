package hello.neardeal_server.item.controller;

import hello.neardeal_server.item.dto.ItemDetailResponse;
import hello.neardeal_server.item.dto.ItemListResponse;
import hello.neardeal_server.item.dto.ItemRequest;
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

@Tag(name = "상품 컨트롤러", description = "상품 관련 API 엔드포인트")
@RequestMapping("/api/item")
@RestController
public class ItemController {

    @PostMapping
    @Operation(summary = "상품 등록", description = "새 상품 등록 하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "새로운 상품 등록 성공"),
            @ApiResponse(responseCode = "400", description = "상품 등록 실패")
    })
    public ResponseEntity<Object> createItem(@RequestBody ItemRequest itemRequest) {
        return null;
    }

    @GetMapping("/{itemId}")
    @Operation(summary = "상품 정보 조회", description = "상품 정보 조회하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상품 정보 조회 성공", content = @Content(schema = @Schema(implementation = ItemDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 상품")
    })
    @Parameter(name = "itemId", description = "상품 ID", required = true)
    public ResponseEntity<ItemDetailResponse> getItemDetail(@PathVariable Long itemId) {
        return null;
    }

    @GetMapping
    @Operation(summary = "상품 목록 조회", description = "상품 목록 페이징 조회하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상품 목록 조회 성공", content = @Content(schema = @Schema(implementation = ItemListResponse.class)))
    })
    public ResponseEntity<ItemListResponse> getItemList(
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 사이즈") @RequestParam(defaultValue = "10") int size
    ) {
        // This is a mock implementation
        List<ItemDetailResponse> mockItems = new ArrayList<>();

        Page<ItemDetailResponse> itemPage = new PageImpl<>(mockItems, PageRequest.of(page, size), 0);

        ItemListResponse response = new ItemListResponse(
                itemPage.getContent(),
                itemPage.getNumber(),
                itemPage.getSize(),
                itemPage.getTotalPages(),
                itemPage.getTotalElements()
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{itemId}")
    @Operation(summary = "상품 정보 수정", description = "상품 정보 수정하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "상품 정보 수정 성공", content = @Content(schema = @Schema(implementation = ItemDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 상품")
    })
    @Parameter(name = "itemId", description = "상품 ID", required = true)
    public ResponseEntity<ItemDetailResponse> updateItemDetail(@PathVariable Long itemId, @RequestBody ItemRequest itemRequest) {
        return null;
    }

    @DeleteMapping("/{itemId}")
    @Operation(summary = "상품 정보 삭제", description = "상품 정보 삭제하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "상품 정보 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 상품")
    })
    @Parameter(name = "itemId", description = "상품 ID", required = true)
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId) {
        return null;
    }
}
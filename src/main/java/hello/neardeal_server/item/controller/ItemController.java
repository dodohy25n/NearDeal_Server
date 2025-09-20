package hello.neardeal_server.item.controller;

import hello.neardeal_server.common.PageResponse;
import hello.neardeal_server.item.dto.response.ItemDetailResponse;
import hello.neardeal_server.item.dto.request.ItemRequest;
import hello.neardeal_server.item.service.ItemService;
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

@Tag(name = "상품 컨트롤러", description = "상품 관련 API 엔드포인트")
@RequestMapping("/api/item")
@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/{storeId}")
    @Operation(summary = "상품 등록", description = "새 상품 등록 하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "새로운 상품 등록 성공"),
            @ApiResponse(responseCode = "400", description = "상품 등록 실패")
    })
    public ResponseEntity<Long> createItem(
            @ModelAttribute ItemRequest itemRequest,
            @PathVariable("storeId") Long storeId
    ) {
        Long itemId = itemService.createItem(itemRequest, storeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemId);
    }

//    @GetMapping("/{itemId}")
//    @Operation(summary = "상품 정보 조회", description = "상품 정보 조회하기")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "상품 정보 조회 성공", content = @Content(schema = @Schema(implementation = ItemDetailResponse.class))),
//            @ApiResponse(responseCode = "404", description = "존재하지 않는 상품")
//    })
//    @Parameter(name = "itemId", description = "상품 ID", required = true)
//    public ResponseEntity<ItemDetailResponse> getItemDetail(@PathVariable Long itemId) {
//        return null;
//    }

    @GetMapping("/{storeId}")
    @Operation(summary = "상품 목록 조회", description = "상품 목록 페이징 조회하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상품 목록 조회 성공", content = @Content(schema = @Schema(implementation = PageResponse.class)))
    })
    public ResponseEntity<PageResponse<ItemDetailResponse>> getItemList(
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 사이즈") @RequestParam(defaultValue = "10") int size,
            @PathVariable(name = "storeId") Long storeId
    ) {
        Page<ItemDetailResponse> itemPage = itemService.findList(storeId, page, size);

        PageResponse<ItemDetailResponse> result = PageResponse.pageToResponse(itemPage);

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{itemId}")
    @Operation(summary = "상품 정보 수정", description = "상품 정보 수정하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "상품 정보 수정 성공", content = @Content(schema = @Schema(implementation = ItemDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 상품")
    })
    @Parameter(name = "itemId", description = "상품 ID", required = true)
    public ResponseEntity<Long> updateItemDetail(
            @PathVariable Long itemId, @ModelAttribute ItemRequest itemRequest) {

        Long result = itemService.updateItemInfo(itemId, itemRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/{itemId}")
    @Operation(summary = "상품 정보 삭제", description = "상품 정보 삭제하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "상품 정보 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 상품")
    })
    @Parameter(name = "itemId", description = "상품 ID", required = true)
    public ResponseEntity<String> deleteItem(@PathVariable Long itemId) {

        itemService.deleteItem(itemId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("삭제완료");
    }

    @PatchMapping("/{itemId}/status")
    @Operation(summary = "상품 품절 상태 변경", description = "상품 품절 상태 변경하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "상품 정보 수정 성공", content = @Content(schema = @Schema(implementation = ItemDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 상품")
    })
    @Parameter(name = "itemId", description = "상품 ID", required = true)
    public ResponseEntity<Long> updateItemStatus(@PathVariable Long itemId) {
        Long result = itemService.toggleItemStatus(itemId);
        return ResponseEntity.ok(result);
    }
}
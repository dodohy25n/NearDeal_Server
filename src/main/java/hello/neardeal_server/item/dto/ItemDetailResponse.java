package hello.neardeal_server.item.dto;

import hello.neardeal_server.item.entity.Item;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder @AllArgsConstructor
@Schema(description = "상품 상세 정보 조회")
public class ItemDetailResponse {

    @Schema(description = "상품 ID")
    private Long id;

    @Schema(description = "상점 ID")
    private Long storeId;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "대표 이미지")
    private String imageUrl;

    @Schema(description = "가격")
    private int price;

    @Schema(description = "품절 상태")
    private boolean isSoldOut;

    @Schema(description = "소개")
    private String introduce;

    @Schema(description = "대표 유무")
    private boolean isRepresentative;

    public static ItemDetailResponse entityToResponse(Item item){
        ItemDetailResponse response = new ItemDetailResponse();
        response.id = item.getId();
        response.name = item.getName();
        response.imageUrl = item.getImageUrl();
        response.price = item.getPrice();
        response.isSoldOut = item.getIsSoldOut();
        response.isRepresentative = item.getIsRepresentative();
        response.introduce = item.getIntroduce();
        response.storeId = item.getStore().getId();

        return response;

    }
}

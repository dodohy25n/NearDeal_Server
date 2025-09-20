package hello.neardeal_server.item.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
@Schema(description = "새 상품 등록 요청")
public class ItemRequest {

    @Schema(description = "이름")
    private String name;

    @Schema(description = "대표 이미지")
    private MultipartFile image;

    @Schema(description = "가격")
    private int price;

    @Schema(description = "몇번째 사진")
    private int index;

    @Schema(description = "품절 상태")
    private Boolean isSoldOut;

    @Schema(description = "소개")
    private String introduction;

    @Schema(description = "대표 유무")
    private Boolean isRepresentative;

}

package hello.neardeal_server.store.dto;

import hello.neardeal_server.common.DurationTime;
import hello.neardeal_server.store.StoreCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
@Schema(description = "새 상점 등록 요청")
public class StoreRequest {

    @Schema(description = "상호")
    private String storeName;

    @Schema(description = "카테고리")
    private StoreCategory category;

    private MultipartFile image;

    @Schema(description = "영업시간")
    private DurationTime openingTime;

    @Schema(description = "브레이크 타임")
    private DurationTime breakTime;

    @Schema(description = "주소")
    private String address;

    @Schema(description = "소개")
    private String introduce;

    @Schema(description = "위도")
    private float lat;

    @Schema(description = "경도")
    private float lng;


//    @Schema(description = "대표 이미지")
//    private String mainImage;
}
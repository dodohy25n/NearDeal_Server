package hello.neardeal_server.store.dto;

import hello.neardeal_server.DurationTime;
import hello.neardeal_server.store.StoreCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import lombok.Getter;

@Getter
@Schema(description = "새 상점 등록 요청")
public class StoreRequest {

    @Schema(description = "상호")
    private String storeName;

    @Schema(description = "카테고리")
    private String category;

    @Embedded
    @Schema(description = "영업시간")
    private DurationTime openingTime;

    @Embedded
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
package hello.neardeal_server.store.dto;

import hello.neardeal_server.store.StoreCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "새 상점 등록 요청")
public class StoreRequest {

    @Schema(description = "상호")
    private String name;

    @Schema(description = "카테고리")
    private StoreCategory category;

    @Schema(description = "영업시간")
    private String operatingHours;

    @Schema(description = "대표 이미지")
    private String mainImage;

    @Schema(description = "주소")
    private String address;

    @Schema(description = "소개")
    private String introduction;

    @Schema(description = "브레이크 타임")
    private String breakTime;
}
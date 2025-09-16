package hello.neardeal_server.stamp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "스탬프 상세 정보 조회")
public class StampDetailResponse {

    @Schema(description = "스탬프 ID")
    private Long id;

    @Schema(description = "상점 ID")
    private Long storeId;

    @Schema(description = "총 갯수")
    private int totalCount;

    @Schema(description = "현재 갯수")
    private int currentCount;

    @Schema(description = "적용 내용")
    private String reward;
}

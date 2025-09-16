package hello.neardeal_server.stamp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "새 스탬프 등록 요청")
public class StampRequest {

    @Schema(description = "상점 ID")
    private Long storeId;

    @Schema(description = "총 갯수")
    private int totalCount;

    @Schema(description = "현재 갯수")
    private int currentCount;

    @Schema(description = "적용 내용")
    private String reward;
}

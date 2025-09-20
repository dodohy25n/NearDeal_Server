package hello.neardeal_server.stamp.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "새 스탬프 등록 요청")
public class StampRequest {

    @Schema(description = "완성 갯수")
    private int maxCount;

    @Schema(description = "적용 내용")
    private String reward;

}

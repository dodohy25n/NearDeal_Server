package hello.neardeal_server.stamp.dto.response;

import hello.neardeal_server.stamp.entity.Stamp;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Schema(description = "스탬프 상세 정보 조회")
public class StampInfoResponse {

    @Schema(description = "스탬프 ID")
    private Long id;

    @Schema(description = "상점 ID")
    private Long storeId;

    @Schema(description = "총 갯수")
    private int maxCount;

    @Schema(description = "적용 내용")
    private String reward;

    public static StampInfoResponse entityToResponse(Stamp stamp) {
        return StampInfoResponse.builder()
                .id(stamp.getId())
                .storeId(stamp.getStore().getId())
                .maxCount(stamp.getMaxCount())
                .reward(stamp.getReward())
                .build();
    }

}

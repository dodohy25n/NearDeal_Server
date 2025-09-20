package hello.neardeal_server.stamp.dto.response;

import hello.neardeal_server.stamp.entity.CustomerStamp;
import hello.neardeal_server.stamp.entity.Stamp;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "고객 스탬프 정보 조회")
public class CustomerStampResponse {

    @Schema(description = "꽉 찼을 때 갯수")
    private int maxCount;

    @Schema(description = "현재 갯수")
    private int currentCount;

    @Schema(description = "혜택 내용")
    private String reward;

    @Schema(description = "가게 이름")
    private String storeName;

    @Schema(description = "가게 ID")
    private Long storeId;

    @Schema(description = "고객스탬프 ID")
    private Long customerStampId;

    @Schema(description = "숨김여부")
    private boolean visible;

    public static CustomerStampResponse entityToResponse(CustomerStamp customerStamp){
        Stamp stamp = customerStamp.getStamp();
        CustomerStampResponse response = new CustomerStampResponse();
        response.maxCount = stamp.getMaxCount();
        response.currentCount = customerStamp.getCurrentCount();
        response.reward = stamp.getReward();
        response.storeName = stamp.getStore().getStoreName();
        response.storeId = stamp.getStore().getId();
        response.customerStampId = customerStamp.getId();
        response.visible = customerStamp.getIsVisible();

        return response;

    }

}

package hello.neardeal_server.store.dto.response;

import hello.neardeal_server.common.DurationTime;
import hello.neardeal_server.member.entity.PartnerCategory;
import hello.neardeal_server.store.StoreCategory;
import hello.neardeal_server.store.entity.PartnerStore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embedded;
import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Schema(description = "제휴 상점 상세 정보 조회 (임시 스키마, 추후 상점 상세 정보 조회 스키마와 통합 예정")
public class PartnerStoreDetailResponse {


    @Schema(description = "상점 ID")
    private Long partnerStoreId;

    @Schema(description = "제휴 대상")
    private PartnerCategory partnerCategory;

    @Schema(description = "제휴 혜택")
    private String partnerBenefit;

    @Schema(description = "카테고리")
    private StoreCategory category;

    @Schema(description = "상점 이름")
    private String storeName;
    
    @Schema(description = "주소")
    private String address;    
    
    @Schema(description = "여는 시간")
    private String openingTime;

    @Schema(description = "닫는 시간")
    private String closingTime;

    @Schema(description = "브레이크 타임 시작 시간")
    private String breakStartTime;

    @Schema(description = "브레이크 타임 종료 시간")
    private String breakEndTime;

    @Schema(description = "라스트 오더")
    private String lastOrder;

    @Schema(description = "위도")
    private BigDecimal lat;

    @Schema(description = "경도")
    private BigDecimal lng;

    @Schema(description = "소개")
    private String introduce;

    @Schema(description = "전화번호")
    private String phone;

    @Schema(description = "기타")
    private String etc;

    @Schema(description = "SNS")
    private String sns;

    public static PartnerStoreDetailResponse entityToResponse(PartnerStore store) {

        return PartnerStoreDetailResponse.builder()
                .partnerStoreId(store.getId())
                .partnerCategory(store.getPartnerCategory())
                .partnerBenefit(store.getPartnerBenefit())
                .category(store.getCategory())
                .storeName(store.getStoreName())
                .address(store.getAddress())
                .openingTime(store.getOpeningTime())
                .closingTime(store.getClosingTime())
                .breakStartTime(store.getBreakStartTime())
                .breakEndTime(store.getBreakEndTime())
                .lastOrder(store.getLastOrder())
                .lat(store.getLat())
                .lng(store.getLng())
                .introduce(store.getIntroduce())
                .phone(store.getPhone())
                .etc(store.getEtc())
                .sns(store.getSns())
                .build();
    }
}

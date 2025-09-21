package hello.neardeal_server.store.dto.response;

import hello.neardeal_server.common.DurationTime;
import hello.neardeal_server.store.StoreCategory;
import hello.neardeal_server.store.entity.Store;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embedded;
import lombok.*;

@Getter @NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Schema(description = "상점 상세 정보 조회")
public class StoreDetailResponse {

//    @Schema(description = "아이템 목록")
//    private List<Long> items;

//    @Schema(description = "리뷰 목록")
//    private List<String> reviews;

//    @Schema(description = "쿠폰 목록")
//    private List<Long> coupons;

    @Schema(description = "상점 ID")
    private Long storeId;

    @Schema(description = "좋아요 수")
    private int likeCount;

    @Schema(description = "상호")
    private String name;

    @Schema(description = "카테고리")
    private StoreCategory category;

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

    @Schema(description = "대표 이미지 URL")
    private String mainImageUrl;

    @Schema(description = "전화번호")
    private String phoneNumber;


//    @Schema(description = "별점")
//    private double rating;


    public static StoreDetailResponse entityToResponse(Store store){

        return StoreDetailResponse.builder()
                .storeId(store.getId())
                .likeCount(store.getLikeCount())
                .name(store.getStoreName())
                .category(store.getCategory())
                .openingTime(store.getOpeningTime())
                .breakTime(store.getBreakTime())
                .address(store.getAddress())
                .introduce(store.getIntroduce())
                .phoneNumber(store.getPhoneNumber())
                .mainImageUrl(store.getImageUrl())
                .build();
    }
}
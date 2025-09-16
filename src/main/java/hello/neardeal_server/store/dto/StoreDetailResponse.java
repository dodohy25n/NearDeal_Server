package hello.neardeal_server.store.dto;

import hello.neardeal_server.store.StoreCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
@Schema(description = "상점 상세 정보 조회")
public class StoreDetailResponse {

    @Schema(description = "아이템 목록")
    private List<Long> items;

    @Schema(description = "리뷰 목록")
    private List<String> reviews;

    @Schema(description = "쿠폰 목록")
    private List<Long> coupons;

    @Schema(description = "좋아요 수")
    private int likeCount;

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

    @Schema(description = "별점")
    private double rating;

    @Schema(description = "브레이크 타임")
    private String breakTime;
}
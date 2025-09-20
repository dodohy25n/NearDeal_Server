package hello.neardeal_server.member.dto;

import hello.neardeal_server.member.entity.Owner;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OwnerDetailResponse {

    @Schema(description = "점주 ID")
    private Long id;

    private String businessNumber;

    public static OwnerDetailResponse entityToResponse(Owner owner) {

        return OwnerDetailResponse.builder()
                .id(owner.getId())
                .businessNumber(owner.getBusinessNumber())
                .build();
    }
}
package hello.neardeal_server.member.dto;

import hello.neardeal_server.member.entity.Owner;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OwnerDetailResponse {

    @Schema(description = "점주 ID")
    private Long id;

    public OwnerDetailResponse(Owner owner) {
        this.id = owner.getId();
    }
}
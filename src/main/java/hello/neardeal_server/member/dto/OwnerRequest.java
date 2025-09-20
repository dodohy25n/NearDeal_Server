package hello.neardeal_server.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "점주 등록 요청")
public class OwnerRequest {

    @Schema(description = "사업자번호")
    private int businessNumber;
}

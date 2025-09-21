package hello.neardeal_server.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
@Schema(description = "점주 등록 요청")
public class OwnerRequest {

    @Schema(description = "사업자번호")
    private String businessNumber;
}

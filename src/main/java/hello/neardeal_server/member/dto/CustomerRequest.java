package hello.neardeal_server.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "고객 등록 요청")
public class CustomerRequest {

    @Schema(description = "소속")
    String affiliation;
}

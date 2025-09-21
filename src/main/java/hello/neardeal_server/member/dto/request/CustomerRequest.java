package hello.neardeal_server.member.dto.request;

import hello.neardeal_server.member.entity.PartnerCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter @AllArgsConstructor
@Schema(description = "고객 등록 요청")
public class CustomerRequest {

    @Schema(description = "소속")
    PartnerCategory affiliation;
}

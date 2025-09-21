package hello.neardeal_server.member.dto.response;

import hello.neardeal_server.member.entity.Customer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@Schema(description = "고객 정보 조회")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerDetailResponse {

    @Schema(description = "고객 ID")
    private Long id;
    
    @Schema(description = "소속")
    private String affiliation;

    public static CustomerDetailResponse entityToResponse(Customer customer) {
        return CustomerDetailResponse.builder()
                .id(customer.getId())
                .affiliation(customer.getAffiliation().toString())
                .build();
    }
}
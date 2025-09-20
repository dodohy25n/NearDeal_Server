package hello.neardeal_server.member.dto;

import hello.neardeal_server.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@Schema(description = "회원 정보 조회")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberDetailResponse {

    @Schema(description = "회원 ID")
    private Long id;

    @Schema(description = "이메일")
    private String email;

    @Schema(description = "비밀번호")
    private String password;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "닉네임")
    private String nickName;

    @Schema(description = "전화번호")
    private String phone;

    @Schema(description = "고객 정보")
    private CustomerDetailResponse customer;

    @Schema(description = "점주 정보")
    private OwnerDetailResponse owner;

    public static MemberDetailResponse entityToResponse(Member member) {
        return MemberDetailResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .nickName(member.getNickName())
                .phone(member.getPhone())
                .customer(CustomerDetailResponse.entityToResponse(member.getCustomer()))
                //.owner(OwnerDetailResponse.entityToResponse(member.getOwner()))
                .build();
    }
}
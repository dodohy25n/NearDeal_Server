package hello.neardeal_server.member.dto;

import hello.neardeal_server.member.entity.PartnerCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "회원 정보 수정 요청")
public class MemberUpdateRequest {

    @Schema(description = "이름")
    private String name;

    @Schema(description = "이메일")
    private String email;

    @Schema(description = "비밀번호")
    private String password;

    @Schema(description = "닉네임")
    private String nickName;

    @Schema(description = "전화번호")
    private String phone;

    @Schema(description = "소속 (고객인 경우)")
    private PartnerCategory affiliation;
}

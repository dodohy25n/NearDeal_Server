package hello.neardeal_server.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "회원가입 요청")
public class SignupRequest {

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

    @Schema(description = "고객 ID")
    private Long customerId;

    @Schema(description = "점주 ID")
    private Long ownerId;
}

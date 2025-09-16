package hello.neardeal_server.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "회원가입 DTO")
public class SignupRequest {

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
}

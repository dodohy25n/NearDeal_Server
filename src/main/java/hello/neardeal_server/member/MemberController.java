package hello.neardeal_server.member;

import hello.neardeal_server.member.dto.SignupRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 컨트롤러", description = "회원 관련 API 엔드포인트")
//@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@RequestMapping("/api")
@RestController
public class MemberController {

    @GetMapping("/signup")
    @Operation(summary = "회원 가입", description = "회원 가입")
    @ApiResponse(
            responseCode = "200",
            description = "성공",
            content = @Content(schema = @Schema(implementation = SignupRequest.class))
    )
    public ResponseEntity<Object> signup (
            @RequestBody @Schema(implementation = RequestBody.class) SignupRequest signupRequest
    ) {

    }
}

package hello.neardeal_server.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
@Getter
@Schema(description = "회원 페이징 목록 정보")
@AllArgsConstructor()
public class MemberListResponse {

    @Schema(description = "회원 목록")
    private final List<MemberDetailResponse> members;

    @Schema(description = "현재 페이지 번호")
    private final int page;

    @Schema(description = "페이지 당 데이터 수")
    private final int size;

    @Schema(description = "전체 페이지 수")
    private final int totalPages;

    @Schema(description = "전체 데이터 수")
    private final long totalElements;

    public static MemberListResponse of(Page<MemberDetailResponse> memberDetailResponsePage) {
        return MemberListResponse.builder()
                .members(memberDetailResponsePage.getContent())
                .page(memberDetailResponsePage.getNumber() + 1)
                .size(memberDetailResponsePage.getSize())
                .totalPages(memberDetailResponsePage.getTotalPages())
                .totalElements(memberDetailResponsePage.getTotalElements())
                .build();
    }

}
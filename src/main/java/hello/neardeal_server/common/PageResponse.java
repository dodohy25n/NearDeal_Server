package hello.neardeal_server.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder @AllArgsConstructor
@Schema(description = "페이징 목록 정보")
public class PageResponse<T> {

    @Schema(description = "response 목록")
    private final List<T> content;

    @Schema(description = "현재 페이지 번호")
    private final int page;

    @Schema(description = "페이지 당 데이터 수")
    private final int size;

    @Schema(description = "전체 페이지 수")
    private final int totalPages;

    @Schema(description = "전체 데이터 수")
    private final long totalElements;

    @Schema(description = "마지막 여부")
    private final boolean last;

    public static <T> PageResponse<T> pageToResponse(Page<T> page){
        return PageResponse.<T>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .last(page.isLast())
                .build();

    }
}
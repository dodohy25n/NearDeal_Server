package hello.neardeal_server.common;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Embeddable
@Getter @Setter
@NoArgsConstructor
@Schema(description = "시작/종료 시각")
public class DurationTime {


    private LocalTime startTime;

    private LocalTime endTime;

}

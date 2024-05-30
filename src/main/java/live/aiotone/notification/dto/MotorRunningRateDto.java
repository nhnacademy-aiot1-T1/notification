package live.aiotone.notification.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MotorRunningRateDto {
  private final LocalDateTime timestamp;
  private final Double rate;

}

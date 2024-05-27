package live.aiotone.notification.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MotorRunningRateDto {
  private final LocalDate timestamp;
  private final Double rate;

}

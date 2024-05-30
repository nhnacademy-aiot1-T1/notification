package live.aiotone.notification.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MotorRunningRateResponse {
  private List<MotorRunningRateDto> rates;
}

package live.aiotone.notification.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LogDto {
  private Long motorId;
  private String motorName;
  private String sensorType;
  private Double score;
  private LocalDateTime time;
}

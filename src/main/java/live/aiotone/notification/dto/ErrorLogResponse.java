package live.aiotone.notification.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorLogResponse {
  private Integer total;
  private Integer page;
  private Integer size;
  private List<LogDto> logs;
}

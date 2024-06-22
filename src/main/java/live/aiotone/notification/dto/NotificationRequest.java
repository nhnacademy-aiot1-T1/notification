package live.aiotone.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotificationRequest {
  private final String botName;
  private final String message;
}

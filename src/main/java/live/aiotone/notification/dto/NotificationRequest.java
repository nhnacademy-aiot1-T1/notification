package live.aiotone.notification.dto;

import lombok.Getter;

@Getter
public class NotificationRequest {
  private final String sender;
  private final String message;

  public NotificationRequest(String sender, String message) {
    this.sender = sender;
    this.message = message;
  }
}

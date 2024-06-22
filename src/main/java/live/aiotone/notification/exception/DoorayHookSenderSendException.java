package live.aiotone.notification.exception;

import org.springframework.http.HttpStatus;

public class DoorayHookSenderSendException extends RuntimeException {

  public DoorayHookSenderSendException(String message) {
    super(message);
  }

  public HttpStatus getStatus() {
    return HttpStatus.INTERNAL_SERVER_ERROR;
  }
}

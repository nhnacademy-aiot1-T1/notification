package live.aiotone.notification.controller;

import com.nhnacademy.common.dto.CommonResponse;
import live.aiotone.notification.dto.NotificationRequest;
import live.aiotone.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification-api")
@RequiredArgsConstructor
public class NotificationController {
  private final NotificationService notificationService;

  @PostMapping("/issue/dooray")
  public <T> CommonResponse<T> sendToDooray(@RequestBody NotificationRequest request) {
    return notificationService.sendToDooray(request);
  }

  @PostMapping("/report/email")
  public <T> CommonResponse<T> sendToEmail() throws Exception {
    return notificationService.sendToEmail();
  }

}

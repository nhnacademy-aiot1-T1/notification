package live.aiotone.notification.service;

import com.nhnacademy.common.dto.CommonResponse;
import live.aiotone.notification.dto.NotificationRequest;

public interface NotificationService {

  <T> CommonResponse<T> sendToDooray(NotificationRequest request);

  <T> CommonResponse<T> sendToEmail() throws Exception;
}

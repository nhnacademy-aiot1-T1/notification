package live.aiotone.notification.config;

import com.nhn.dooray.client.DoorayHookSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SenderConfig {
  @Value("${dooray.hookUrl}")
  private String doorayUrl;

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public DoorayHookSender doorayHookSender() {
    return new DoorayHookSender(restTemplate(), doorayUrl);
  }

}

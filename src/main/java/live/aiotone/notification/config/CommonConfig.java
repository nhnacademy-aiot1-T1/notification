package live.aiotone.notification.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ComponentScan(basePackageClasses = com.nhnacademy.common.config.MessageSenderConfig.class)
@Component
public class CommonConfig {
}

package live.aiotone.notification.adapter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class Deserializer extends JsonDeserializer<LocalDateTime> {

  @Override
  public LocalDateTime deserialize(JsonParser jsonParser,
                                   DeserializationContext deserializationContext) throws IOException {
    DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern(
                    "[yyyy-MM-dd HH:mm:ss][yyyy-MM-dd'T'HH:mm:ss.SSSSSS][yyyy-MM-dd'T'HH:mm:ss.SSSSS][yyyy-MM-dd'T'HH:mm:ss.SSSS][yyyy-MM-dd'T'HH:mm:ss.SSS][yyyy-MM-dd'T'HH:mm:ss.SS][yyyy-MM-dd'T'HH:mm:ss.S]");

    return LocalDateTime.parse(jsonParser.getValueAsString(), formatter);
  }
}

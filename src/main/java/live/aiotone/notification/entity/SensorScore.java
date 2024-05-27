package live.aiotone.notification.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sensor_score")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SensorScore {
  @Id
  private String id;

  @Column(name = "sensor_id")
  private String sensorId;

  @Column
  private LocalDateTime time;

  @Column
  private BigDecimal score;

}

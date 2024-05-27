package live.aiotone.notification.repository;

import java.math.BigDecimal;
import java.util.List;
import live.aiotone.notification.entity.SensorScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorScoreRepository extends JpaRepository<SensorScore, Long> {

  List<SensorScore> findAllByScoreLessThan(BigDecimal score);
}

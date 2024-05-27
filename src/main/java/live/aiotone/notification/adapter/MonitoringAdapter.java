package live.aiotone.notification.adapter;

import com.nhnacademy.common.dto.CommonResponse;
import live.aiotone.notification.dto.MotorRunningRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "monitoring-service", path = "/api/monitor/motors")
public interface MonitoringAdapter {

  @GetMapping("/running-rate")
  public CommonResponse<MotorRunningRateResponse> getMotorRunningRate(@RequestParam("duration") String duration);
}

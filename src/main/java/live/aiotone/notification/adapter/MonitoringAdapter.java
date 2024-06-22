package live.aiotone.notification.adapter;

import com.nhnacademy.common.dto.CommonResponse;
import live.aiotone.notification.dto.ErrorLogResponse;
import live.aiotone.notification.dto.MotorRunningRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "monitoring-service", path = "/api/monitor")
public interface MonitoringAdapter {

  @GetMapping("/motors/running-rate")
  public CommonResponse<MotorRunningRateResponse> getMotorRunningRate(@RequestParam("duration") String duration);

  @GetMapping("/logs/error")
  public CommonResponse<ErrorLogResponse> getLogs(@RequestParam("page") Integer page, @RequestParam("size") Integer size);

}

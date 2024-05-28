package live.aiotone.notification.service;

import com.nhn.dooray.client.DoorayHook;
import com.nhn.dooray.client.DoorayHookSender;
import com.nhnacademy.common.dto.CommonResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import live.aiotone.notification.adapter.MonitoringAdapter;
import live.aiotone.notification.dto.LogDto;
import live.aiotone.notification.dto.MotorRunningRateDto;
import live.aiotone.notification.dto.NotificationRequest;
import live.aiotone.notification.entity.Account;
import live.aiotone.notification.entity.enumfield.AccountRole;
import live.aiotone.notification.exception.DoorayHookSenderSendException;
import live.aiotone.notification.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

  private final JavaMailSender javaMailSender;
  private final DoorayHookSender doorayHookSender;
  private final SpringTemplateEngine templateEngine;
    private final MonitoringAdapter monitoringAdapter;

  private final AccountRepository accountRepository;

  @Override
  public <T> CommonResponse<T> sendToDooray(NotificationRequest request) {
    try {
      doorayHookSender.send(
          DoorayHook.builder()
              .botName(request.getSender())
              .text(request.getMessage())
              .build()
      );
    } catch (Exception e) {
      throw new DoorayHookSenderSendException(e.getMessage());
    }
    return CommonResponse.success(null, "message sent to Dooray successfully");
  }

  @Override
  public <T> CommonResponse<T> sendToEmail() throws Exception {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();

    List<Account> admins = accountRepository.findAllByRole(AccountRole.ADMIN);
    if (admins.isEmpty()) {
      return CommonResponse.success(null, "no admin account");
    }
    for (Account admin : admins) {
      if (admin.getEmail() == null) {
        continue;
      }
      mimeMessage.addRecipients(RecipientType.TO, admin.getEmail());
      mimeMessage.setSubject(getLastWeek(LocalDate.now().minusWeeks(1)) + " 리포트");
      mimeMessage.setText(getContent(), "utf-8", "html");

      javaMailSender.send(mimeMessage);
    }
    return CommonResponse.success(null, "report sent to Email successfully");
  }

  private String getLastWeek(LocalDate localDate) {
    WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 4);
    int weekOfMonth = localDate.get(weekFields.weekOfMonth());
    if (weekOfMonth == 0) {
      LocalDate lastDayOfLastMonth = localDate.with(TemporalAdjusters.firstDayOfMonth())
          .minusDays(1);
      return getLastWeek(lastDayOfLastMonth);
    }

    LocalDate lastDayOfLastMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
    if (weekOfMonth == lastDayOfLastMonth.get(weekFields.weekOfMonth())
        && lastDayOfLastMonth.getDayOfWeek().compareTo(DayOfWeek.THURSDAY) < 0) {
      LocalDate firstDayOfNextMonth = lastDayOfLastMonth.plusDays(1);
      return getLastWeek(firstDayOfNextMonth);
    }

    return localDate.getMonthValue() + "월 " + weekOfMonth + "주차";
  }

  private String getContent() {
    Context context = new Context();

    context.setVariable("week", getLastWeek(LocalDate.now().minusWeeks(1)));

    List<MotorRunningRateDto> runningRateList = monitoringAdapter.getMotorRunningRate("WEEK")
        .getData().getRates();

    List<LogDto> logList = monitoringAdapter.getLogs(0, 10).getData().getLogs();

    double weekTotalRate = 0;
    for (MotorRunningRateDto runningRate : runningRateList) {
      weekTotalRate += runningRate.getRate();
    }
    weekTotalRate = Math.round(weekTotalRate / runningRateList.size() * 10) / 10.0;

    String donutChartUrl = createDonutChartUrl(weekTotalRate);
    String barChartUrl = createBarChartUrl(runningRateList);


    context.setVariable("donutChartUrl", donutChartUrl);
    context.setVariable("barChartUrl", barChartUrl);
    context.setVariable("errorLogs", logList);

    return templateEngine.process("report", context);
  }

  private String createDonutChartUrl(double weekTotalRate) {
    String jsonChartData = "{\n"
        + "  type: 'radialGauge',\n"
        + "  data: { datasets: [{ data: ["
        + weekTotalRate
        + "], backgroundColor: '#8cabd9' }] },\n"
        + "}";

    log.error(jsonChartData);

    String encodedChartData = URLEncoder.encode(jsonChartData, StandardCharsets.UTF_8);
    return "https://quickchart.io/chart?bkg=white&c=" + encodedChartData;
  }

  public String createBarChartUrl(List<MotorRunningRateDto> motorRunningRateList) {

    List<String> labels = new ArrayList<>();
    List<Double> data = new ArrayList<>();

    for (MotorRunningRateDto dataPoint : motorRunningRateList) {
      labels.add(dataPoint.getTimestamp().toLocalDate().toString());
      data.add(Math.round(dataPoint.getRate() * 10) / 10.0);
    }

    String jsonChartData = "{\n"
        + "  type: 'bar',\n"
        + "  data: {\n"
        + "    labels: "
        + "['"
        + String.join("', '", labels)
        + "']" + ",\n"
        + "    datasets: [\n"
        + "      { label: 'Running Rate (%)', data: "
        + Arrays.toString(data.toArray()) + ",\n"
        + "  backgroundColor : '#8cabd9' }\n"
        + "    ]\n"
        + "  }\n"
        + "}";

    log.error(jsonChartData);

    String encodedChartData = URLEncoder.encode(jsonChartData, StandardCharsets.UTF_8);
    return "https://quickchart.io/chart?bkg=white&c=" + encodedChartData;
  }

}

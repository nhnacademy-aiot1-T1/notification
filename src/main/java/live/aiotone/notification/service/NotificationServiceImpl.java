package live.aiotone.notification.service;

import com.nhn.dooray.client.DoorayHook;
import com.nhn.dooray.client.DoorayHookSender;
import com.nhnacademy.common.dto.CommonResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.List;
import live.aiotone.notification.dto.NotificationRequest;
import live.aiotone.notification.entity.Account;
import live.aiotone.notification.entity.enumfield.AccountRole;
import live.aiotone.notification.exception.DoorayHookSenderSendException;
import live.aiotone.notification.repository.AccountRepository;
import live.aiotone.notification.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

  private final JavaMailSender javaMailSender;
  private final DoorayHookSender doorayHookSender;
  private final IssueRepository issueRepository;
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
  public <T> CommonResponse<T> sendToEmail() {
    SimpleMailMessage message = new SimpleMailMessage();
    List<Account> admins = accountRepository.findAllByRole(AccountRole.ADMIN);
    for (Account admin : admins) {
      if (admin.getEmail() == null) {
        continue;
      }
      message.setTo(admin.getEmail());
      message.setSubject(getLastWeek(LocalDate.now()) + " 리포트");
      message.setText(getContent());

      javaMailSender.send(message);
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
    return null;
  }
}

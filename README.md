# Notification API
Notification API 서버를 관리하는 리포지토리입니다<br/>
Dooray 메시지 전송 요청과 Email 전송 요청에 대한 처리를 합니다

+ 담당자 : <a href="https://github.com/qaw302">박미정<a/>
<br />

## 사용기술
- Dooray 메시지 전송 : DoorayHookSender
- Email 전송 : Spring Mail - JavaMailSender
<br />

## 주요기능
<p align="center">
  <img src="https://github.com/nhnacademy-aiot1-T1/notification/assets/118845947/cfd0e30d-eaa2-49cc-94ac-061e558a6dc0" height="100" alt="text" />
</p>

+ Dooray Message 전송
  + /notification-api/issue/dooray 경로로 POST 요청이 오면 지정해둔 hook url로 Body 내용(botName, message)을 전달
<br/>

<p align="center">
  <img src="https://github.com/nhnacademy-aiot1-T1/notification/assets/118845947/f50deb28-0b3d-4840-8bd4-b3894985eaf5" height="500" alt="text" />
  <img src="https://github.com/nhnacademy-aiot1-T1/notification/assets/118845947/74ca9abb-c9ea-4622-bbd8-03cbe039dce3" height="500" alt="text" />
</p>

- EMail 전송
  - /notification-api/report/email 경로로 POST 요청이 오면 계정을 관리하는 DB에서 ADMIN 유저의 email목록을 불러와 미리 작성된 html 형태의 내용을 메일로 전송
<br />



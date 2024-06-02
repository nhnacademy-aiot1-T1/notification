# Notification API
Notification API 서버를 관리하는 리포지토리입니다<br/>
Dooray 메시지 전송 요청과 Email 전송 요청에 대한 처리를 합니다
<br />
## 사용기술
- Dooray 메시지 전송 : DoorayHookSender
- Email 전송 : Spring Mail - JavaMailSender
<br />

## 주요기능
- Dooray Message 전송
  - /notification-api/issue/dooray 경로로 POST 요청이 오면 지정해둔 hook url로 Body 내용(botName, message)을 전달
<br/>

- EMail 전송
  - /notification-api/report/email 경로로 POST 요청이 오면 계정을 관리하는 DB에서 ADMIN 유저의 email목록을 불러와 미리 작성된 html 형태의 내용을 메일로 전송
<br />

## 담당자
박미정

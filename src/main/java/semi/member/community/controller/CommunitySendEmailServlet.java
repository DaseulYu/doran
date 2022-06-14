package semi.member.community.controller;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@WebServlet("/community/admin/sendEmail")
public class CommunitySendEmailServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String[] memberEmail = req.getParameterValues("memberEmail");
		String subject ="[*필독*] 모임이 삭제될 예정입니다.";
		String fromEmail = "kh.dorandoran@gmail.com";
		String fromUsername = "관리자";
		String toEmail = String.join(",", memberEmail);
		
		final String smtpEmail = "kh.dorandoran@gmail.com";
		final String password = "cvejhzlhoopfxzfg";

		// 메일 옵션 설정
		Properties props = new Properties();
      
		// 중요
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587"); //465, 587
		props.put("mail.smtp.auth", "true");

		// 추가 옵션
		props.put("mail.smtp.quitwait", "false");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "true");
		props.put("mail.smtp.starttls.enable", "true");

      
		try {
			// 메일 세션 생성
			Session session = Session.getDefaultInstance(props);

			// 메일 송/수신 옵션 설정(여러명 보내기)
			Message message = new MimeMessage(session);
      
			message.setFrom(new InternetAddress(fromEmail, fromUsername));    // 송신자(보내는 사람) 지정
         
			message.setRecipients(RecipientType.TO, InternetAddress.parse(toEmail, false)); // 수신자(받는사람) 지정
         
			message.setSubject(subject); // 이메일 제목 지정
         

			// 메일 콘텐츠 설정
			Multipart mParts = new MimeMultipart();
			MimeBodyPart mTextPart = new MimeBodyPart();

			// 메일에 출력할 텍스트
			StringBuffer sb = new StringBuffer(); // 가변성 문자열 저장 객체
			sb.append("<h3 style='color:red'>10일 뒤 모임이 삭제될 예정입니다.</h3>\n");
			sb.append("<h3>모임장을 위임받으실 멤버가 있으면 현재 모임장한테 연락 부탁드립니다. 모임장을 위임받으실 경우 해당 모임은 유지됩니다.</h3>\n");

			String mailContent = sb.toString(); // 문자열로 반환
         
			// 메일 콘텐츠 - 내용 , 메일인코딩, "html" 추가 시 HTML 태그가 해석됨
			mTextPart.setText(mailContent, "UTF-8", "html");
			mParts.addBodyPart(mTextPart);

         
			// 메일 콘텐츠 지정
			message.setContent(mParts);
 
			// MIME 타입 설정 (이메일 내용이 깨질 때 사용)
			/*MailcapCommandMap MailcapCmdMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
         	MailcapCmdMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
         	MailcapCmdMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
         	MailcapCmdMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
         	MailcapCmdMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
         	MailcapCmdMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
         	CommandMap.setDefaultCommandMap(MailcapCmdMap);*/

			// 메일 발송
			Transport t = session.getTransport("smtp");
			t.connect(smtpEmail, password);
			t.sendMessage(message, message.getAllRecipients());
			t.close();

		} catch (Exception e) {
			e.printStackTrace();
         
			// ajax error 속성 활용을 위한 500에러를 응답으로 전달.
			resp.setStatus(500);
			resp.getWriter().print(e.getMessage());
      }	
		
	}
}

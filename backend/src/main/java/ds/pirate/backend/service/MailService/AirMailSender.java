package ds.pirate.backend.service.MailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class AirMailSender {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String FROM_ADDRESS;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private String port;

    public void send(String mailAdress, String title, String context) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailAdress);
        message.setFrom(FROM_ADDRESS);
        message.setSubject(title);
        message.setText(context);
        mailSender.send(message);
        log.info("메일이 발송되었습니다"+message);
    }
}
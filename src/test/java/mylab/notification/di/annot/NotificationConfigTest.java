package mylab.notification.di.annot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class NotificationConfigTest {

    @Test
    public void testNotificationConfig() {
        // Spring 컨텍스트 로드
        ApplicationContext context = new AnnotationConfigApplicationContext(NotificationConfig.class);
        
        // NotificationManager 빈 주입
        NotificationManager notificationManager = context.getBean(NotificationManager.class);
        assertNotNull(notificationManager, "NotificationManager가 생성되지 않았습니다.");

        // 이메일 서비스 검증
        NotificationService emailService = notificationManager.getEmailService();
        assertNotNull(emailService, "EmailNotificationService가 주입되지 않았습니다.");
        assertTrue(emailService instanceof EmailNotificationService);
        EmailNotificationService email = (EmailNotificationService) emailService;
        assertEquals("smtp.gmail.com", email.getSmtpServer(), "SMTP 서버 정보가 올바르지 않습니다.");
        assertEquals(587, email.getPort(), "포트 번호가 올바르지 않습니다.");

        // SMS 서비스 검증
        NotificationService smsService = notificationManager.getSmsService();
        assertNotNull(smsService, "SmsNotificationService가 주입되지 않았습니다.");
        assertTrue(smsService instanceof SmsNotificationService);
        SmsNotificationService sms = (SmsNotificationService) smsService;
        assertEquals("SKT", sms.getProvider(), "SMS 제공업체 정보가 올바르지 않습니다.");

        // NotificationManager 메서드 실행 테스트
        notificationManager.sendNotificationByEmail("테스트 이메일");
        notificationManager.sendNotificationBySms("테스트 SMS");
    }
}

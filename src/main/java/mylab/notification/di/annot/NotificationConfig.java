package mylab.notification.di.annot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

    @Bean
    public EmailNotificationService emailNotificationService() {
        // SMTP 서버와 포트 설정
        return new EmailNotificationService("smtp.gmail.com", 587);
    }

    @Bean
    public SmsNotificationService smsNotificationService() {
        // SMS 제공업체 설정
        return new SmsNotificationService("SKT");
    }

    @Bean
    public NotificationManager notificationManager() {
        // 이메일 서비스와 SMS 서비스를 주입
        return new NotificationManager(emailNotificationService(), smsNotificationService());
    }
}

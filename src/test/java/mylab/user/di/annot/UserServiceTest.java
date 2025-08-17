package mylab.user.di.annot;

import mylab.user.di.annot.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class) // JUnit 5용 Extension
@ContextConfiguration(locations = "classpath:mylab-user-di.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testUserServiceDI() {
        // UserService의 레퍼런스가 Not Null인지 검증
        assertNotNull(userService, "UserService should not be null");

        // userService.getUserRepository()가 Not Null인지 검증
        UserRepository userRepository = userService.getUserRepository();
        assertNotNull(userRepository, "UserRepository should not be null");

        // userService.getUserRepository().getDbType() 값이 MySQL인지 비교
        assertEquals("MySQL", userRepository.getDbType(), "DB type should be MySQL");

        // userService.getSecurityService()가 Not Null인지 검증
        SecurityService securityService = userService.getSecurityService();
        assertNotNull(securityService, "SecurityService should not be null");
    }

    @Test
    public void testRegisterUser() {
        // userService.registerUser() 메서드가 True인지 검증
        boolean result = userService.registerUser("hia", "hyun", "1234");
        assertTrue(result, "User registration should return true");
    }
}

package ua.epam.spring.hometask.domain;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import ua.epam.spring.hometask.service.UserService;
import ua.epam.spring.hometask.service.serviceImpl.UserServiceImpl;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@ContextConfiguration(locations = {"spring.xml"})
public class UserTest {

    private static final String EMAIL = "ivan@gmail.com";
    private static final String FIRST_NAME = "Ivan";
    private static final String LAST_NAME = "Ivanov";

    private User user;
    private UserService userService;

    @Before
    public void initUser() {

        ApplicationContext appContext = new ClassPathXmlApplicationContext("spring.xml");

        userService = appContext.getBean(UserService.class);

        user = new User();
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setEmail(EMAIL);
        userService.save(user);
    }

    @Test
    public void testGetUserByEmail() {
        assertEquals(userService.getUserByEmail(EMAIL), user);
    }

    @Test
    public void testGetUserById() {
        assertEquals(userService.getById(0L), user);
    }
}

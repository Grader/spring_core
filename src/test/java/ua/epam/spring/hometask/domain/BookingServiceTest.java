package ua.epam.spring.hometask.domain;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.hometask.service.AuditoriumService;
import ua.epam.spring.hometask.service.BookingService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class BookingServiceTest {

    private static final String EVENT_NAME = "GoodEvent";
    private static final long EVENT_PRICE = 100L;
    private static final String AUDITORIUM_NAME = "auditoriumRed";
    private static final String USERS_MAIL = "user@gmail.com";
    private static final String FIRST_USERS_NAME = "Ivan";
    private static final String LAST_USERS_NAME = "Ivanov";
    private static final LocalDateTime EVENT_DATE_TIME = LocalDateTime.of(2019, 5, 15, 12, 0);
    private static final LocalDate USERS_BIRTHDAY = LocalDate.of(1999, 5, 16);

    private BookingService bookingService;
    private AuditoriumService auditoriumService;

    @Before
    public void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        bookingService = context.getBean(BookingService.class);
        auditoriumService = context.getBean(AuditoriumService.class);
    }

    private User createUser() {
        return new User() {{
            setEmail(USERS_MAIL);
            setFirstName(FIRST_USERS_NAME);
            setLastName(LAST_USERS_NAME);
            setBirthDay(USERS_BIRTHDAY);
        }};
    }

    private Event createEvent() {
        return new Event() {{
            setAirDates(new TreeSet<LocalDateTime>() {{
                add(EVENT_DATE_TIME);
            }});
            setName(EVENT_NAME);
            setBasePrice(EVENT_PRICE);
            setRating(EventRating.MID);
            assignAuditorium(EVENT_DATE_TIME, auditoriumService.getByName(AUDITORIUM_NAME));
        }};
    }

    @Test
    public void testGetTicketsPrice() {
        Set<Long> seats = new HashSet<Long>() {{
            add(4L);
            add(5L);
            add(59L);
        }};
        double ticketsPrice = bookingService.getTicketsPrice(createEvent(), EVENT_DATE_TIME, createUser(), seats);
        assertEquals(380.0, ticketsPrice, 0.01);
    }
}

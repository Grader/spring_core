package ua.epam.spring.hometask.domain;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.epam.spring.hometask.configuration.SpringConfiguration;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.DiscountService;
import ua.epam.spring.hometask.service.EventService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeMap;

public class AspectTest {

    private static final LocalDateTime EVENT_DATE_TIME = LocalDateTime.of(2019, 5, 15, 12, 0);

    @Before
    public void init() {


        ApplicationContext appContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        System.out.println("\n\n\n------------------AOP TESTS------------------------------------");
        System.out.println("\n-------------COUNTER ASPECT TEST-------------------------------");

        EventService eventService = appContext.getBean("eventService", EventService.class);
        eventService.getByName("test1");
        eventService.getByName("test1");
        eventService.getByName("test1");
        eventService.getByName("test1");
        eventService.getByName("test2");

        System.out.println("\n\n\n-------------TEST GET_TICKET_PRICE-------------------------------");

        BookingService bookingService = appContext.getBean("bookingService", BookingService.class);
        Event event = appContext.getBean("event", Event.class);
        Auditorium auditoriumRed = appContext.getBean("auditoriumRed", Auditorium.class);
        event.setName("testEvent");
        event.setRating(EventRating.MID);
        event.setAuditoriums(new TreeMap<LocalDateTime, Auditorium>() {{
            put(EVENT_DATE_TIME, auditoriumRed);
        }});
        bookingService.getTicketsPrice(event, EVENT_DATE_TIME, null, new HashSet<>(Arrays.asList(3L, 4L)));
        bookingService.getTicketsPrice(event, EVENT_DATE_TIME, null, new HashSet<>(Arrays.asList(5L, 6L)));

        System.out.println("\n\n\n-------------TEST BOOK_TICKETS-------------------------------");

        Ticket ticket = new Ticket(null, event, EVENT_DATE_TIME, 5L);
        Ticket ticket2 = new Ticket(null, event, EVENT_DATE_TIME, 6L);
        Ticket ticket3 = new Ticket(null, event, EVENT_DATE_TIME, 7L);
        bookingService.bookTickets(new HashSet<>(Arrays.asList(ticket, ticket2, ticket3)));

        System.out.println("\n\n\n-------------TEST GET_DISCOUNT-------------------------------");

        DiscountService discountService =appContext.getBean("discountService", DiscountService.class);
        discountService.getDiscount(new User(), event, EVENT_DATE_TIME, 5L);
    }

    @Test
    public void testCounterAspect() {
    }

    @Test
    public void testDiscountAspect() {

    }
}

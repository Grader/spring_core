package ua.epam.spring.hometask.domain;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.hometask.service.EventService;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class EventServiceTest {

    private static final String EVENT_NAME = "EventName";
    private static final long EVENT_PRICE = 100L;


    private EventService eventService;

    @Before
    public void init() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("spring.xml");
        eventService = appContext.getBean(EventService.class);
        eventService.save(createEvent());
    }

    private Event createEvent() {
        return new Event() {{
            setName(EVENT_NAME);
            setBasePrice(EVENT_PRICE);
            setRating(EventRating.HIGH);
        }};
    }

    @Test
    public void testGetByName() {
        assertEquals(eventService.getByName(EVENT_NAME), createEvent());
    }

    @Test
    public void testGetById() {
        assertEquals(eventService.getById(0L), createEvent());
    }

    @Test
    public void testGetAll() {
        assertEquals(eventService.getAll(), new HashSet<Event>() {{
            add(createEvent());
        }});
    }
}

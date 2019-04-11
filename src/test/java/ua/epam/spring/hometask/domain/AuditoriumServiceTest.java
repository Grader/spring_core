package ua.epam.spring.hometask.domain;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.hometask.service.AuditoriumService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class AuditoriumServiceTest {

    private AuditoriumService auditoriumService;

    @Before
    public void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        auditoriumService = context.getBean(AuditoriumService.class);
    }

    @Test
    public void testGetByName() {
        Auditorium auditorium = new Auditorium();
        auditorium.setName("auditoriumRed");
        assertEquals(auditoriumService.getByName("auditoriumRed"), auditorium);
    }

    @Test
    public void testGetAll() {
        Set<Auditorium> auditoriumSet = new HashSet<>();
        Auditorium auditoriumRed = new Auditorium();
        auditoriumRed.setName("auditoriumRed");
        auditoriumRed.setNumberOfSeats(50);
        auditoriumRed.setVipSeats(new HashSet<Long>() {{
            add(58L);
            add(59L);
            add(56L);
        }});
        Auditorium auditoriumBlack = new Auditorium();
        auditoriumBlack.setName("auditoriumBlack");
        auditoriumBlack.setNumberOfSeats(80);
        auditoriumBlack.setVipSeats(new HashSet<Long>() {{
            add(75L);
            add(76L);
            add(77L);
            add(78L);
            add(79L);
            add(80L);
        }});
        auditoriumSet.add(auditoriumRed);
        auditoriumSet.add(auditoriumBlack);

        assertEquals(auditoriumService.getAll(), auditoriumSet);
    }
}

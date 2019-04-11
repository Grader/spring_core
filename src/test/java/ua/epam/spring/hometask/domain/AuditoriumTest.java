package ua.epam.spring.hometask.domain;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import ua.epam.spring.hometask.service.AuditoriumService;

public class AuditoriumTest {



    @Test
    public void testCountVips() {
        Auditorium auditorium = new Auditorium();
        auditorium.setVipSeats(Stream.of(1L, 2L, 3L).collect(Collectors.toSet()));
        assertEquals(0, auditorium.countVipSeats(Arrays.asList(10L, 20L, 30L)));
        assertEquals(1, auditorium.countVipSeats(Arrays.asList(10L, 2L, 30L)));
        assertEquals(2, auditorium.countVipSeats(Arrays.asList(10L, 2L, 3L, 4L, 5L, 6L)));
    }

    @Test
    public void testGetAllSeats() {
        Auditorium auditorium = new Auditorium();
        auditorium.setNumberOfSeats(10);
        assertEquals(10, auditorium.getAllSeats().size());
    }
}

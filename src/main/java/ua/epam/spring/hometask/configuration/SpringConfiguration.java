package ua.epam.spring.hometask.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ua.epam.spring.hometask.aspects.CounterAspect;
import ua.epam.spring.hometask.aspects.DiscountAspect;
import ua.epam.spring.hometask.dao.*;
import ua.epam.spring.hometask.dao.daoImpl.*;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.service.*;
import ua.epam.spring.hometask.service.serviceImpl.*;

import java.util.HashSet;
import java.util.Set;

@Configuration
@Import(DbConfiguration.class)
@EnableAspectJAutoProxy
@PropertySource(
        value = {"classpath:${property:auditorium}.properties"},
        ignoreResourceNotFound = true)
public class SpringConfiguration {

    @Value("${auditoriumRed.name}")
    private String nameAuditoriumRed;
    @Value("#{'${auditoriumRed.vipSeats}'.split(',')}")
    private Set<Long> vipSeatsRed;
    @Value("${auditoriumRed.numberOfSeats}")
    private Long numberOfSeatsRed;

    @Value("${auditoriumBlack.name}")
    private String nameAuditoriumBlack;
    @Value("#{'${auditoriumBlack.vipSeats}'.split(',')}")
    private Set<Long> vipSeatsBlack;
    @Value("${auditoriumBlack.numberOfSeats}")
    private Long numberOfSeatsBlack;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public UserDao userDao() {
        return new UserDaoImpl();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userDao());
    }

    @Bean
    public AuditoriumDao auditoriumDao() {
        return new AuditoriumDaoImpl() {{
            setAuditoriumSet(new HashSet<Auditorium>() {{
                add(auditoriumRed());
                add(auditoriumBlack());
            }});
        }};
    }

    @Bean
    public Auditorium auditoriumRed() {
        return new Auditorium() {{
            setName(nameAuditoriumRed);
            setVipSeats(vipSeatsRed);
            setNumberOfSeats(numberOfSeatsRed);
        }};
    }

    @Bean
    public Auditorium auditoriumBlack() {
        return new Auditorium() {{
            setName(nameAuditoriumBlack);
            setVipSeats(vipSeatsBlack);
            setNumberOfSeats(numberOfSeatsBlack);
        }};
    }

    @Bean
    AuditoriumService auditoriumService() {
        return new AuditoriumServiceImpl(auditoriumDao());
    }

    @Bean
    EventDao eventDao() {
        return new EventDaoImpl();
    }

    @Bean
    EventService eventService() {
        return new EventServiceImpl(eventDao());
    }

    @Bean
    TicketDao ticketDao() {
        return new TicketDaoImpl();
    }

    @Bean
    DiscountStrategy discountStrategy() {
        return new DiscountStrategyImpl() {{
            setDiscountForBirthday(5);
            setDifferentOfDay(5);
            setDiscountForNumberTicket(50);
            setNumberTicketForDiscount(10);
        }};
    }

    @Bean
    DiscountService discountService() {
        return new DiscountServiceImpl(discountStrategy());
    }

    @Bean
    BookingService bookingService() {
        return new BookingServiceImpl(discountService(), ticketDao(), userService()) {{
            setRiserForRating(1.2);
            setRiserForVip(2);
        }};
    }

    @Bean
    CounterDao counterDao() {
        return new CounterDaoImpl();
    }

    @Bean
    CounterService counterService() {
        return new CounterServiceImpl(counterDao());
    }

    @Bean
    Event event() {
        return new Event();
    }

    @Bean
    CounterAspect counterAspect() {
        return new CounterAspect();
    }

    @Bean
    DiscountAspect discountAspect() {
        return new DiscountAspect();
    }
}

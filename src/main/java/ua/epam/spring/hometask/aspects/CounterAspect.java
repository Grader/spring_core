package ua.epam.spring.hometask.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import ua.epam.spring.hometask.dao.CounterDao;
import ua.epam.spring.hometask.dao.daoImpl.CounterDaoImpl;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;

import java.util.Iterator;
import java.util.Set;

@Aspect
public class CounterAspect {

    private CounterDao counterDao = new CounterDaoImpl();

    @Pointcut("execution(public * ua.epam.spring.hometask.service.EventService.getByName(..))")
    public void callAtEventServiceGetByName() {
    }

    @AfterReturning("callAtEventServiceGetByName()")
    public void callAtEventServiceGetByName(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        String eventName = (String) arguments[0];

        counterDao.incrementCounter(eventName);
        System.out.println(String.format("[ASPECT]: %s was called by name %dth", eventName, counterDao
                .getCounter(eventName)));
    }

    @Pointcut("execution(public * ua.epam.spring.hometask.service.BookingService.getTicketsPrice(..))")
    public void callAtBookingServiceGetTicketsPrice() {
    }

    @AfterReturning("callAtBookingServiceGetTicketsPrice()")
    public void callAtBookingServiceGetTicketsPrice(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        Event event = (Event) arguments[0];
        String eventName = event.getName();
        counterDao.incrementCounter(eventName);
        System.out.println(String.format("[ASPECT]: %s price was required %dth", eventName, counterDao
                .getCounter(eventName)));
    }

    @Pointcut("execution(public * ua.epam.spring.hometask.service.BookingService.bookTickets(..))")
    public void callAtBookingServiceBookTickets() {
    }

    @AfterReturning("callAtBookingServiceBookTickets()")
    @SuppressWarnings("unchecked")
    public void callAtBookingServiceBookTickets(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        Set<Ticket> tickets = (Set<Ticket>) arguments[0];
        Iterator<Ticket> ticketsIterator = tickets.iterator();
        String eventName;
        while (ticketsIterator.hasNext()) {
            eventName = ticketsIterator.next().getEvent().getName();
            counterDao.incrementCounter(eventName);
            System.out.println(String.format("[ASPECT]: %s tickets were booked %dth", eventName, counterDao
                    .getCounter(eventName)));
        }
    }
}

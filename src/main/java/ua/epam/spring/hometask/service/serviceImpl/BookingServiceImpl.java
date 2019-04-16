package ua.epam.spring.hometask.service.serviceImpl;

import ua.epam.spring.hometask.dao.TicketDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.DiscountService;
import ua.epam.spring.hometask.service.UserService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {

    private DiscountService discountService;
    private TicketDao ticketDao;
    private UserService userService;
    private double riserForRating;
    private double riserForVip;

    public BookingServiceImpl(DiscountService discountService, TicketDao ticketDao, UserService userService) {
        this.discountService = discountService;
        this.ticketDao = ticketDao;
        this.userService = userService;
    }

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user,
                                  @Nonnull Set<Long> seats) {
        double ticketsPrice = 0;
        double basePrice = event.getBasePrice();
        if (event.getRating().equals(EventRating.HIGH)) {
            basePrice *= riserForRating;
        }
        for (Long seat : seats) {
            double price = basePrice;
            if (event.getAuditoriums().get(dateTime).getVipSeats().contains(seat)) {
                price *= riserForVip;
            }
            byte discount = discountService.getDiscount(user, event, dateTime, seats.size());
            if (discount != 0) {
                price -= price * discount / 100;
            }
            ticketsPrice += price;
        }
        return ticketsPrice;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            User user = ticket.getUser();
            if (user == null || ticket.getEvent() == null || ticket.getDateTime() == null) {
                return;
            }
            if (user.getId() == null) {
                userService.save(user);
            }
            if (userService.getById(user.getId()) != null) {
                Ticket storedTicket =
                        new Ticket(ticket.getUser(), ticket.getEvent(), ticket.getDateTime(), ticket.getSeat());
                user.getTickets().add(storedTicket);
                ticketDao.add(storedTicket);
            }
        }
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        Set<Ticket> tickets = ticketDao.getTicketsByEvent(event);
        if (tickets != null) {
            return tickets.stream().filter(e -> e.getDateTime() != null && e.getDateTime().equals(dateTime)).
                    collect(Collectors.toSet());
        }
        return new HashSet<>();
    }

    public void setRiserForRating(double riserForRating) {
        this.riserForRating = riserForRating;
    }

    public void setRiserForVip(double riserForVip) {
        this.riserForVip = riserForVip;
    }
}

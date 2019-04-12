package ua.epam.spring.hometask.dao.daoImpl;

import ua.epam.spring.hometask.dao.TicketDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TicketDaoImpl implements TicketDao {

    private Map<Event, Set<Ticket>> tickets;

    @Override
    public void add(Ticket ticket) {
        Event event = ticket.getEvent();
        tickets.computeIfAbsent(event, k -> new HashSet<>()).add(ticket);
    }

    @Override
    public Set<Ticket> getTicketsByUser(User user) {
        Set<Ticket> ticketSet = new HashSet<>();
        tickets.forEach((k, v) -> v.forEach(t -> {
            if (t.getUser().equals(user)) {
                ticketSet.add(t);
            }
        }));
        return ticketSet;
    }

    @Override
    public Set<Ticket> getTicketsByEvent(Event event) {
        return tickets.get(event);
    }
}

package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import java.util.Set;

public interface TicketDao {

    void add(Ticket ticket);

    Set<Ticket> getTicketsByUser(User user);

    Set<Ticket> getTicketsByEvent(Event event);
}

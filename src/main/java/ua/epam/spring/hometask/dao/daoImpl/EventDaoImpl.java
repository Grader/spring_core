package ua.epam.spring.hometask.dao.daoImpl;

import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.domain.DomainObject;
import ua.epam.spring.hometask.domain.Event;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;

public class EventDaoImpl implements EventDao {

    private Collection<Event> eventList = new HashSet<>();

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return eventList.stream().filter(e -> name.equals(e.getName())).findFirst().orElse(null);
    }

    @Override
    public Event save(@Nonnull Event event) {
        if (!eventList.isEmpty()) {
            Long maxId = eventList.stream().max(Comparator.comparing(DomainObject::getId)).orElse(null).getId();
            event.setId(++maxId);
        } else {
            event.setId(0L);
        }
        eventList.add(event);
        return event;
    }

    @Override
    public void remove(@Nonnull Event event) {
        eventList.remove(event);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return eventList.stream().filter(e -> id.equals(e.getId())).findFirst().orElse(null);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return eventList;
    }
}

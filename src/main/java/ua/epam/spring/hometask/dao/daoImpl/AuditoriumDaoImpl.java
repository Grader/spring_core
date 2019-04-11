package ua.epam.spring.hometask.dao.daoImpl;

import ua.epam.spring.hometask.dao.AuditoriumDao;
import ua.epam.spring.hometask.domain.Auditorium;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AuditoriumDaoImpl implements AuditoriumDao {

    private Set<Auditorium> auditoriumSet = new HashSet<>();

    public void setAuditoriumSet(Set<Auditorium> auditoriumSet) {
        this.auditoriumSet = auditoriumSet;
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return auditoriumSet;
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return auditoriumSet.stream().filter(e -> name.equals(e.getName())).findFirst().orElse(null);
    }
}

package ua.epam.spring.hometask.dao.daoImpl;

import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.DomainObject;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class UserDaoImpl implements UserDao {

    private Collection<User> userList = new HashSet<>();

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return userList.stream().filter(e -> email.equals(e.getEmail())).findFirst().orElse(null);
    }

    @Override
    public User save(@Nonnull User user) {
        if (!userList.isEmpty()) {
            Long maxId = userList.stream().max(Comparator.comparing(DomainObject::getId)).orElse(null).getId();
            user.setId(++maxId);
        } else user.setId(0L);
        userList.add(user);
        return user;
    }

    @Override
    public void remove(@Nonnull User user) {
        userList.remove(user);
    }

    @Override
    public User getById(@Nonnull Long id) {
        return userList.stream().filter(e -> id.equals(e.getId())).findFirst().orElse(null);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return userList;
    }
}

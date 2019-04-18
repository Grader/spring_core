package ua.epam.spring.hometask.dao;

import java.util.Map;

public interface CounterDao {

    void incrementCounter(String name);

    int getCounter(String name);
}

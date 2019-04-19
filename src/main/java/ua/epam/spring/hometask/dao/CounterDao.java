package ua.epam.spring.hometask.dao;

public interface CounterDao {

    void incrementCounter(String name);

    int getCounter(String name);
}

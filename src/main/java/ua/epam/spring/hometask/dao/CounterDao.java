package ua.epam.spring.hometask.dao;

public interface CounterDao {

    void incrementCounter(String name);

    Integer getCounter(String name);
}

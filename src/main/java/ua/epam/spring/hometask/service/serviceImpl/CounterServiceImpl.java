package ua.epam.spring.hometask.service.serviceImpl;

import ua.epam.spring.hometask.dao.CounterDao;
import ua.epam.spring.hometask.service.CounterService;

public class CounterServiceImpl implements CounterService {

    private CounterDao counterDao;

    public CounterServiceImpl(CounterDao counterDao) {
        this.counterDao = counterDao;
    }

    @Override
    public int getCounter(String counterName) {
        return counterDao.getCounter(counterName);
    }
}

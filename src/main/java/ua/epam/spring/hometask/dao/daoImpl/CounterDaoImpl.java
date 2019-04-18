package ua.epam.spring.hometask.dao.daoImpl;

import ua.epam.spring.hometask.dao.CounterDao;

import java.util.HashMap;
import java.util.Map;

public class CounterDaoImpl implements CounterDao {

    private Map<String, Integer> counterStorage = new HashMap<>();

    @Override
    public void incrementCounter(String counterName) {
        if(!counterStorage.containsKey(counterName)){
            counterStorage.put(counterName, 0);
        }
        counterStorage.put(counterName, counterStorage.get(counterName) + 1);
    }

    @Override
    public int getCounter(String counterName) {
        if(counterStorage.containsKey(counterName)){
            return counterStorage.get(counterName);
        }
        return 0;
    }
}

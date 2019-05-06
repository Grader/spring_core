package ua.epam.spring.hometask.dao.daoImpl;

import org.springframework.jdbc.core.JdbcTemplate;
import ua.epam.spring.hometask.dao.CounterDao;

import java.util.List;

public class CounterDaoImpl implements CounterDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void incrementCounter(String counterName) {
        List name = jdbcTemplate
                .queryForObject("SELECT NAMECOUNTER FROM ASPECTSCOUNTER WHERE NAMECOUNTER = ?", List.class);
        Integer count = jdbcTemplate
                .queryForObject("SELECT NUMBERCOUNT FROM ASPECTSCOUNTER WHERE NAMECOUNTER = ?", Integer.class);
        if (!(name != null && name.contains(counterName))) {
            jdbcTemplate
                    .update("INSERT INTO ASPECTSCOUNTER (NAMECOUNTER, NUMBERCOUNT) VALUES (?, ?)", counterName, 0);
        }
        jdbcTemplate
                .update("INSERT INTO ASPECTSCOUNTER (NAMECOUNTER, NUMBERCOUNT) VALUES (?, ?)", counterName, count + 1);
    }

    @Override
    public Integer getCounter(String counterName) {
        String name = jdbcTemplate
                .queryForObject("SELECT NAMECOUNTER FROM ASPECTSCOUNTER WHERE NAMECOUNTER = ?", String.class);
        if (counterName.equals(name)) {
            return jdbcTemplate
                    .queryForObject("SELECT NUMBERCOUNT FROM ASPECTSCOUNTER WHERE NAMECOUNTER = ?", Integer.class);
        }
        return 0;
    }
}


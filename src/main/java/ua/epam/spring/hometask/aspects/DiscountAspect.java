package ua.epam.spring.hometask.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import ua.epam.spring.hometask.dao.CounterDao;
import ua.epam.spring.hometask.dao.daoImpl.CounterDaoImpl;
import ua.epam.spring.hometask.domain.User;

@Aspect
public class DiscountAspect {

    private CounterDao counterDao = new CounterDaoImpl();

    @Pointcut("execution(public * ua.epam.spring.hometask.service.DiscountService.getDiscount(..))")
    public void callAtDiscountServiceGetDiscount() {
    }

    @AfterReturning("callAtDiscountServiceGetDiscount()")
    public void callAtDiscountServiceGetDiscount(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        User user = (User) arguments[0];
        Class clazz = joinPoint.getTarget().getClass();
        if (user != null) {
            counterDao.incrementCounter(user.getLastName());
            System.out.println(String.format("[ASPECT]: %s discount for %s %s user were given %d times",
                    clazz.getSimpleName(), user.getFirstName(), user.getLastName(), counterDao
                            .getCounter(user.getLastName())));
        }
        counterDao.incrementCounter(clazz.getSimpleName());
        System.out.println(String.format("[ASPECT]: %s discount were given %d times", clazz.getSimpleName(),
                counterDao.getCounter(clazz.getSimpleName())));
    }
}

package ua.epam.spring.hometask.service.serviceImpl;

import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountStrategy;

import java.time.LocalDateTime;
import java.time.Period;

public class DiscountStrategyImpl implements DiscountStrategy {

    private static final int DISCOUNT_FOR_BIRTHDAY = 5;
    private static final int DIFFERENT_OF_DAY = 5;
    private static final int DISCOUNT_FOR_NUMBER_TICKET = 50;
    private static final int NUMBER_TICKET_FOR_DISCOUNT = 10;

    @Override
    public int getDiscountOfBirthDay(User user, LocalDateTime localDateTime) {

        if (user == null || user.getBirthDay() == null) {
            return 0;
        }

        if (Period.between(localDateTime.toLocalDate(), user.getBirthDay().toLocalDate()).getDays() <
                DIFFERENT_OF_DAY) {
            return DISCOUNT_FOR_BIRTHDAY;
        }
        return 0;
    }

    @Override
    public int getDiscountOfTenthTicket(User user, int numberOfTickets) {

        if (user != null) {
            for (int i = 0; i < numberOfTickets; i++) {
                if ((user.getTickets().size() + i) % NUMBER_TICKET_FOR_DISCOUNT == 0) {
                    return getDiscount(numberOfTickets + NUMBER_TICKET_FOR_DISCOUNT);
                }
            }
        } else if (numberOfTickets / NUMBER_TICKET_FOR_DISCOUNT >= 1) {
            return getDiscount(numberOfTickets);
        }
        return 0;
    }

    private int getDiscount(int tickets) {
        return (tickets / NUMBER_TICKET_FOR_DISCOUNT) * DISCOUNT_FOR_NUMBER_TICKET / NUMBER_TICKET_FOR_DISCOUNT;
    }
}

package ua.epam.spring.hometask.service.serviceImpl;

import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountStrategy;

import java.time.LocalDateTime;

public class DiscountStrategyImpl implements DiscountStrategy {

    private int discountForBirthday;
    private int differentOfDay;
    private int discountForNumberTicket;
    private int numberTicketForDiscount;

    @Override
    public int getDiscountOfBirthDay(User user, LocalDateTime localDateTime) {

        if (user == null || user.getBirthDay() == null) {
            return 0;
        }
        if (user.getBirthDay().getDayOfYear() - localDateTime.toLocalDate().getDayOfYear() < differentOfDay) {
            return discountForBirthday;
        }
        return 0;
    }

    @Override
    public int getDiscountOfTenthTicket(User user, int numberOfTickets) {

        if (user != null && user.getTickets().size() != 0) {
            for (int i = 0; i < numberOfTickets; i++) {
                if ((user.getTickets().size() + i) % numberTicketForDiscount == 0) {
                    return getDiscount(numberOfTickets + numberTicketForDiscount);
                }
            }
        } else if (numberOfTickets / numberTicketForDiscount >= 1) {
            return getDiscount(numberOfTickets);
        }
        return 0;
    }

    private int getDiscount(int tickets) {
        return (tickets / numberTicketForDiscount) * discountForNumberTicket / numberTicketForDiscount;
    }

    public void setDiscountForBirthday(int discountForBirthday) {
        this.discountForBirthday = discountForBirthday;
    }

    public void setDifferentOfDay(int differentOfDay) {
        this.differentOfDay = differentOfDay;
    }

    public void setDiscountForNumberTicket(int discountForNumberTicket) {
        this.discountForNumberTicket = discountForNumberTicket;
    }

    public void setNumberTicketForDiscount(int numberTicketForDiscount) {
        this.numberTicketForDiscount = numberTicketForDiscount;
    }
}

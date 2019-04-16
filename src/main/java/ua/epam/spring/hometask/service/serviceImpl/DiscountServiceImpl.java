package ua.epam.spring.hometask.service.serviceImpl;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountService;
import ua.epam.spring.hometask.service.DiscountStrategy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

public class DiscountServiceImpl implements DiscountService {

    private DiscountStrategy discountStrategy;

    public DiscountServiceImpl(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime,
                            long numberOfTickets) {

        int discountOfBirthDay = discountStrategy.getDiscountOfBirthDay(user, airDateTime);
        int discountOfOfTenthTicket = discountStrategy.getDiscountOfTenthTicket(user, (int) numberOfTickets);
        if (discountOfBirthDay > discountOfOfTenthTicket) {
            return (byte) discountOfBirthDay;
        } else {
            return (byte) discountOfOfTenthTicket;
        }
    }
}

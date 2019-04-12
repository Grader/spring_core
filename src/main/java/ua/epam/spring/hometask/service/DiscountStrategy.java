package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;

public interface DiscountStrategy {

    int getDiscountOfBirthDay(User user, LocalDateTime localDateTime);

    int getDiscountOfTenthTicket(User user, int numberOfTickets);

}

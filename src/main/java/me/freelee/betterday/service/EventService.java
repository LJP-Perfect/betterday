package me.freelee.betterday.service;

import me.freelee.betterday.model.Event;

import java.util.List;

public interface EventService {
    List<Event> selectAllEventsByUserId(Integer userId);

    int insertEvent(Event event);

    Event selectEventById(Integer id);

    void updateEvent(Event event);

    void deleteEvent(Integer id);
}

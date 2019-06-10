package me.freelee.betterday.service;

import me.freelee.betterday.model.Time;

import java.util.List;

public interface TimeService {
    List<Time> selectAllTimes(Integer userId);

    void insertTime(Time time);
}

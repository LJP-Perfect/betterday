package me.freelee.betterday.service;

import me.freelee.betterday.dto.HabitInsertParam;
import me.freelee.betterday.model.Habit;
import me.freelee.betterday.model.Time;
import me.freelee.betterday.model.UserHabitRecord;

import java.sql.Date;
import java.util.List;

public interface HabitService {
    List<Habit> getHabitsByUserId(Integer userId, Date date);

    void updateHabitCheckedStatus(Integer userId, Integer habitId, int i);

    List<UserHabitRecord> getHabitDataByUserId(Integer userId);

    int insertHabit(HabitInsertParam habitInsertParam);

    List<Habit> selectPublicHabits();

    Integer getHabitTodayCheckStatus(Integer userId, Integer id, Date date);

    Habit getHabitById(Integer habitId);
}

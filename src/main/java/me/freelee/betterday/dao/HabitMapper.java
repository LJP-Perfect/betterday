package me.freelee.betterday.dao;

import me.freelee.betterday.model.Habit;
import me.freelee.betterday.util.IBaseMapper;

import java.util.List;
import java.util.Map;

public interface HabitMapper extends IBaseMapper<Habit> {

    Integer insertHabit(Habit habit);
}
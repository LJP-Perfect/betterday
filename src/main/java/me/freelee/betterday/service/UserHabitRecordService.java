package me.freelee.betterday.service;

import me.freelee.betterday.model.UserHabitRecord;

import java.util.List;

public interface UserHabitRecordService {

    List<UserHabitRecord> selectAll();
}

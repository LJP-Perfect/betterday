package me.freelee.betterday.dao;

import me.freelee.betterday.model.UserHabitCheckLog;
import me.freelee.betterday.util.IBaseMapper;

import java.util.List;
import java.util.Map;

public interface UserHabitCheckLogMapper extends IBaseMapper<UserHabitCheckLog> {

    void updateHabitCheckedStatus(Map<String,Object> queryMap);

    List<Integer> selectHabitIdsByUserId(Map<String,Object> queryMap);
}
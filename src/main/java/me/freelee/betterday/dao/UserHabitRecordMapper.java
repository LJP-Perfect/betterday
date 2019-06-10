package me.freelee.betterday.dao;

import me.freelee.betterday.model.UserHabitRecord;
import me.freelee.betterday.util.IBaseMapper;

import java.util.Map;

public interface UserHabitRecordMapper extends IBaseMapper<UserHabitRecord> {

    void subCheckedDays(Map<String,Object> queryMap);

    void addCheckedDays(Map<String,Object> queryMap);

}
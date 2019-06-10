package me.freelee.betterday.service.impl;

import me.freelee.betterday.dao.UserHabitCheckLogMapper;
import me.freelee.betterday.model.UserHabitCheckLog;
import me.freelee.betterday.service.UserHabitCheckLogService;
import me.freelee.betterday.service.UserHabitRecordService;
import me.freelee.betterday.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * Description:
 * Date:2019/6/7
 *
 * @author:Lee
 */
@Service
public class UserHabitCheckLogServiceImpl implements UserHabitCheckLogService {

    @Autowired
    UserHabitCheckLogMapper userHabitCheckLogMapper;


    @Override
    public Integer insertCheckLog(UserHabitCheckLog userHabitCheckLog) {
        userHabitCheckLog.setChecked(0);
        userHabitCheckLog.setTime(new Date(DateUtil.getCurrentTime().getTime()));
        return userHabitCheckLogMapper.insertSelective(userHabitCheckLog);
    }
}

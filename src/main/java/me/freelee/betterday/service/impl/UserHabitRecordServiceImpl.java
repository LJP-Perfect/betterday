package me.freelee.betterday.service.impl;

import me.freelee.betterday.dao.UserHabitRecordMapper;
import me.freelee.betterday.model.UserHabitRecord;
import me.freelee.betterday.service.UserHabitRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Date:2019/6/7
 *
 * @author:Lee
 */
@Service
public class UserHabitRecordServiceImpl implements UserHabitRecordService {

    @Autowired
    UserHabitRecordMapper userHabitRecordMapper;


    @Override
    public List<UserHabitRecord> selectAll() {
        return userHabitRecordMapper.selectAll();
    }
}

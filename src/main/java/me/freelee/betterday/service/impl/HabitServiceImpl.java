package me.freelee.betterday.service.impl;

import me.freelee.betterday.dao.HabitMapper;
import me.freelee.betterday.dao.UserHabitCheckLogMapper;
import me.freelee.betterday.dao.UserHabitRecordMapper;
import me.freelee.betterday.dto.HabitInsertParam;
import me.freelee.betterday.model.Habit;
import me.freelee.betterday.model.Time;
import me.freelee.betterday.model.UserHabitCheckLog;
import me.freelee.betterday.model.UserHabitRecord;
import me.freelee.betterday.service.HabitService;
import me.freelee.betterday.service.UserHabitCheckLogService;
import me.freelee.betterday.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Description:
 * Date:2019/5/30
 *
 * @author:Lee
 */
@Service
public class HabitServiceImpl implements HabitService {

    Logger logger= LoggerFactory.getLogger(HabitServiceImpl.class);

    @Autowired
    HabitMapper habitMapper;

    @Autowired
    UserHabitCheckLogMapper userHabitCheckLogMapper;

    @Autowired
    UserHabitRecordMapper userHabitRecordMapper;

    @Autowired
    UserHabitCheckLogService userHabitCheckLogService;


    @Override
    public List<Habit> getHabitsByUserId(Integer userId, Date date) {
        HashMap<String,Object> queryMap=new HashMap<>();
        List<Habit> queryHabits=new ArrayList<>();
        queryMap.put("userId",userId);
        queryMap.put("time",date);
        List<Integer> habitIds=userHabitCheckLogMapper.selectHabitIdsByUserId(queryMap);
        if(habitIds!=null){
            for(Integer habitId:habitIds){
                Example example =new Example(Habit.class);
                example.createCriteria().andEqualTo("id",habitId).andEqualTo("isDelete",0);
                Habit habit=habitMapper.selectOneByExample(example);
                if(habit!=null){
                    queryHabits.add(habit);
                }
            }
        }else{
            return null;
        }

        return queryHabits;
    }

    @Override
    public void updateHabitCheckedStatus(Integer userId, Integer habitId, int i) {
        HashMap<String,Object> queryMap=new HashMap<>();
        queryMap.put("userId",userId);
        queryMap.put("habitId",habitId);
        queryMap.put("status",i);
        queryMap.put("checkTime",DateUtil.getCurrentTime());
        userHabitCheckLogMapper.updateHabitCheckedStatus(queryMap);
        if(i==0){
            userHabitRecordMapper.subCheckedDays(queryMap);
        }else{
            userHabitRecordMapper.addCheckedDays(queryMap);
        }
    }

    @Override
    public List<UserHabitRecord> getHabitDataByUserId(Integer userId) {
        Example example=new Example(UserHabitRecord.class);
        example.createCriteria().andEqualTo("userId",userId);
        return userHabitRecordMapper.selectByExample(example);
    }

    @Override
    public int insertHabit(HabitInsertParam habitInsertParam) {
        Habit habit=habitInsertParam.getHabit();
        habit.setCreateTime(DateUtil.getCurrentTime());
        habit.setModifyTime(DateUtil.getCurrentTime());
        habit.setIsPublic(0);
        habit.setIsDelete(0);
        int affectRow=habitMapper.insertHabit(habit);

        UserHabitRecord userHabitRecord=new UserHabitRecord();
        userHabitRecord.setCheckedDays(0);
        userHabitRecord.setHabitId(habit.getId());
        userHabitRecord.setUserId(habitInsertParam.getUserId());
        userHabitRecordMapper.insertSelective(userHabitRecord);

        if(-1!=habit.getWeekdays().indexOf(String.valueOf(DateUtil.judgeWeekday(DateUtil.getCurrentTime().toString().split(" ")[0])))){
            UserHabitCheckLog userHabitCheckLog=new UserHabitCheckLog();
            userHabitCheckLog.setUserId(habitInsertParam.getUserId());
            userHabitCheckLog.setHabitId(habit.getId());
            userHabitCheckLogService.insertCheckLog(userHabitCheckLog);
        }

        return affectRow;
    }

    @Override
    public List<Habit> selectPublicHabits() {
        Example example=new Example(Habit.class);
        example.createCriteria().andEqualTo("isPublic",1);
        return habitMapper.selectByExample(example);
    }

    @Override
    public Integer getHabitTodayCheckStatus(Integer userId, Integer id, Date date) {
        Example example=new Example(UserHabitCheckLog.class);
        example.createCriteria().andEqualTo("userId",userId).andEqualTo("habitId",id).andEqualTo("time",date);
        return userHabitCheckLogMapper.selectOneByExample(example).getChecked();
    }

    @Override
    public Habit getHabitById(Integer habitId) {
        return habitMapper.selectByPrimaryKey(habitId);
    }


}

package me.freelee.betterday.job;

import me.freelee.betterday.model.Habit;
import me.freelee.betterday.model.UserHabitCheckLog;
import me.freelee.betterday.model.UserHabitRecord;
import me.freelee.betterday.service.HabitService;
import me.freelee.betterday.service.UserHabitCheckLogService;
import me.freelee.betterday.service.UserHabitRecordService;
import me.freelee.betterday.util.DateUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * Date:2019/6/7
 *
 * @author:Lee
 */
public class HabitInitTask extends QuartzJobBean {

    @Autowired
    UserHabitRecordService userHabitRecordService;

    @Autowired
    HabitService habitService;

    @Autowired
    UserHabitCheckLogService userHabitCheckLogService;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<UserHabitRecord> userHabitRecordList=userHabitRecordService.selectAll();
        if(userHabitRecordList!=null&&userHabitRecordList.size()!=0){
            Date today= DateUtil.getCurrentTime();
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(today);
            //把日期往后增加一天,整数  往后推,负数往前移动
            calendar.add(Calendar.DATE,1);
            calendar.getTime();
            for(UserHabitRecord record:userHabitRecordList){
                String weekdaysStr=habitService.getHabitById(record.getHabitId()).getWeekdays();
                if(-1!=weekdaysStr.indexOf(String.valueOf(DateUtil.judgeWeekday(DateUtil.df_ymd.format(calendar.getTime()))))){
                    UserHabitCheckLog userHabitCheckLog=new UserHabitCheckLog();
                    userHabitCheckLog.setUserId(record.getUserId());
                    userHabitCheckLog.setHabitId(record.getHabitId());
                    userHabitCheckLogService.insertCheckLog(userHabitCheckLog);
                }
            }
        }
    }
}

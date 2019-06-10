package me.freelee.betterday.controller;

import me.freelee.betterday.dto.HabitCheckParam;
import me.freelee.betterday.dto.HabitInsertParam;
import me.freelee.betterday.model.Habit;
import me.freelee.betterday.model.Time;
import me.freelee.betterday.model.UserHabitCheckLog;
import me.freelee.betterday.model.UserHabitRecord;
import me.freelee.betterday.service.HabitService;
import me.freelee.betterday.service.TimeService;
import me.freelee.betterday.util.AliyunOSSClientUtil;
import me.freelee.betterday.util.DateUtil;
import me.freelee.betterday.util.ResultUtil;
import me.freelee.betterday.vo.HabitDataVO;
import me.freelee.betterday.vo.HabitVO;
import me.freelee.betterday.vo.TodayHabitVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Date:2019/5/30
 *
 * @author:Lee
 */
@RequestMapping("/habit")
@RestController
public class HabitController {

    Logger logger= LoggerFactory.getLogger(HabitController.class);

    @Autowired
    HabitService habitService;

    @Autowired
    TimeService timeService;

    /** 
    * @Description: 获取所有Icons 
    * @Param: [] 
    * @return: me.freelee.betterday.util.ResultUtil 
    * @Author: freelee 
    * @Date: 2019/6/5 
    */ 
    @GetMapping("/icons")
    public ResultUtil getIcons(){
        return ResultUtil.success("", AliyunOSSClientUtil.getBucketObjectsByPrefix("habit/icon/"),null);
    }

    /** 
    * @Description: 根据用户ID获取今天的习惯 
    * @Param: [userId] 
    * @return: me.freelee.betterday.util.ResultUtil 
    * @Author: freelee 
    * @Date: 2019/6/5 
    */ 
    @GetMapping("")
    public ResultUtil getTodayHabitsByUserId(@RequestParam("userId") Integer userId){
        List<Habit> habits=habitService.getHabitsByUserId(userId, new Date(DateUtil.getCurrentTime().getTime()));
        List<TodayHabitVO> todayHabitVOList=new ArrayList<>();
        for(Time time:timeService.selectAllTimes(userId)){
            int count=0;
            TodayHabitVO todayHabitVO=new TodayHabitVO();
            List<HabitVO> habitVOList=new ArrayList<>();
            for(Habit habit:habits){
                if(habit.getTimeId().equals(time.getId())){
                    todayHabitVO.setAction(time.getIcon());
                    todayHabitVO.setActive(false);
                    todayHabitVO.setTitle(time.getName());
                    HabitVO habitVO=new HabitVO();
                    habitVO.setId(habit.getId());
                    habitVO.setTitle(habit.getName());
                    habitVO.setIcon(habit.getIcon());
                    habitVO.setChecked(habitService.getHabitTodayCheckStatus(userId,habit.getId(),new Date(DateUtil.getCurrentTime().getTime())));
                    habitVOList.add(habitVO);
                    count++;
                }
            }
            if(count>0){
                todayHabitVO.setItems(habitVOList);
                todayHabitVOList.add(todayHabitVO);
            }
        }
        return ResultUtil.success("",todayHabitVOList,null);
    }

    /** 
    * @Description: 习惯打卡 
    * @Param: [habitCheckParam] 
    * @return: me.freelee.betterday.util.ResultUtil 
    * @Author: freelee 
    * @Date: 2019/6/5 
    */ 
    @PutMapping("/check")
    public ResultUtil checkHabit(@RequestBody HabitCheckParam habitCheckParam){
        habitService.updateHabitCheckedStatus(habitCheckParam.getUserId(),habitCheckParam.getHabitId(),1);
        return ResultUtil.success("打卡成功",null,null);
    }

    /** 
    * @Description: 习惯取消打卡 
    * @Param: [habitCheckParam] 
    * @return: me.freelee.betterday.util.ResultUtil 
    * @Author: freelee 
    * @Date: 2019/6/5 
    */ 
    @PutMapping("/uncheck")
    public ResultUtil uncheckHabit(@RequestBody HabitCheckParam habitCheckParam){
        habitService.updateHabitCheckedStatus(habitCheckParam.getUserId(),habitCheckParam.getHabitId(),0);
        return ResultUtil.success("取消打卡成功",null,null);
    }

    /** 
    * @Description: 获取用户的习惯打卡数据 
    * @Param: [userId] 
    * @return: me.freelee.betterday.util.ResultUtil 
    * @Author: freelee 
    * @Date: 2019/6/5 
    */ 
    @GetMapping("/data")
    public ResultUtil getHabitDataByUserId(@RequestParam("userId") Integer userId){
        List<UserHabitRecord> userHabitRecords=habitService.getHabitDataByUserId(userId);
        List<HabitDataVO> habitDataVOList=new ArrayList<>();
        if(userHabitRecords!=null){
            for(UserHabitRecord userHabitRecord: userHabitRecords){
                HabitDataVO habitDataVO=new HabitDataVO();
                habitDataVO.setDays(userHabitRecord.getCheckedDays());
                Habit habit=habitService.getHabitById(userHabitRecord.getHabitId());
                habitDataVO.setIcon(habit.getIcon());
                habitDataVO.setId(habit.getId());
                habitDataVO.setName(habit.getName());
                habitDataVOList.add(habitDataVO);
            }
        }
        return ResultUtil.success("",habitDataVOList,null);
    }

    /** 
    * @Description: 添加习惯 
    * @Param: [habit] 
    * @return: me.freelee.betterday.util.ResultUtil 
    * @Author: freelee 
    * @Date: 2019/6/5 
    */ 
    @PostMapping("")
    public ResultUtil addHabit(@RequestBody HabitInsertParam habitInsertParam){
        int affectRow=habitService.insertHabit(habitInsertParam);
        if(affectRow>=1){
            return ResultUtil.success("添加成功",habitInsertParam,null);
        }else{
            return ResultUtil.fail(11001,"添加失败，系统出现错误，请重试",habitInsertParam);
        }
    }

    /** 
    * @Description: 获取系统公共习惯 
    * @Param: [] 
    * @return: me.freelee.betterday.util.ResultUtil 
    * @Author: freelee 
    * @Date: 2019/6/5 
    */ 
    @GetMapping("/public")
    public ResultUtil getPublicHabits(){
        List<Habit> habitList=habitService.selectPublicHabits();
        return ResultUtil.success("",habitList,null);
    }

    /** 
    * @Description: 获取所有习惯对应的时间类别 
    * @Param: [] 
    * @return: me.freelee.betterday.util.ResultUtil 
    * @Author: freelee 
    * @Date: 2019/6/5 
    */ 
    @GetMapping("/times")
    public ResultUtil getAllTimes(@RequestParam("userId") Integer userId){
        List<Time> timeList=timeService.selectAllTimes(userId);
        return ResultUtil.success("",timeList,null);
    }

    /** 
    * @Description: 添加习惯时间类别
    * @Param: [time] 
    * @return: me.freelee.betterday.util.ResultUtil 
    * @Author: freelee 
    * @Date: 2019/6/5 
    */ 
    @PostMapping("/time/add")
    public ResultUtil addTime(@RequestBody Time time){
        time.setCreateTime(DateUtil.getCurrentTime());
        time.setIsPublic(0);
        timeService.insertTime(time);
        return ResultUtil.success("",null,null);
    }

}

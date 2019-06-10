package me.freelee.betterday.config;

import me.freelee.betterday.job.HabitInitTask;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Date:2019/3/30
 *
 * @author:Lee
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail initHabitCheckLog(){
        return JobBuilder.newJob(HabitInitTask.class).withIdentity("HabitInitTask").storeDurably().build();
    }

    @Bean
    public Trigger initHabitCheckLogTrigger() {
        //TODO 这里设定执行方式
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 1 0 * * ?");
        // 返回任务触发器
        return TriggerBuilder.newTrigger().forJob(initHabitCheckLog())
                .withIdentity("HabitInitTask")
                .withSchedule(scheduleBuilder)
                .build();
    }

}


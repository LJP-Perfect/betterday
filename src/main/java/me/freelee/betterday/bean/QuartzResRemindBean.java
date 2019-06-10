package me.freelee.betterday.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * Description:
 * Date:2019/3/29
 *
 * @author:Lee
 */
@Getter
@Setter
public class QuartzResRemindBean {

    /** 任务 id */
    private String  id;

    /** 任务名称 */
    private String jobName;

    /** 任务执行类 */
    private String jobClass;

    /** 任务状态 启动还是暂停 */
    private Integer status;

    /** 任务运行时间表达式 */
    private String cronExpression;

    private Integer userId;

    private Integer eqptId;

    private String resTime;
}

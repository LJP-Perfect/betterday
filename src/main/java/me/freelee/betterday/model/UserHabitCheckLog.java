package me.freelee.betterday.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_habit_check_log")
public class UserHabitCheckLog {
    @Id
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "habit_id")
    private Integer habitId;

    private Date time;

    /**
     * 0:未打卡；1：已打卡
     */
    private Integer checked;

    @Column(name = "check_time")
    private Date checkTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return habit_id
     */
    public Integer getHabitId() {
        return habitId;
    }

    /**
     * @param habitId
     */
    public void setHabitId(Integer habitId) {
        this.habitId = habitId;
    }

    /**
     * @return time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 获取0:未打卡；1：已打卡
     *
     * @return check - 0:未打卡；1：已打卡
     */
    public Integer getChecked() {
        return checked;
    }

    /**
     * 设置0:未打卡；1：已打卡
     *
     * @param checked 0:未打卡；1：已打卡
     */
    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    /**
     * @return check_time
     */
    public Date getCheckTime() {
        return checkTime;
    }

    /**
     * @param checkTime
     */
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
}
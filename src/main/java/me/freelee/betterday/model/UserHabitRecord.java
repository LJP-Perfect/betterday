package me.freelee.betterday.model;

import javax.persistence.*;

@Table(name = "user_habit_record")
public class UserHabitRecord {
    @Id
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "habit_id")
    private Integer habitId;

    /**
     * 打卡天数
     */
    @Column(name = "checked_days")
    private Integer checkedDays;

    @Column(name = "unchecked_days")
    private Integer uncheckedDays;

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
     * 获取打卡天数
     *
     * @return checked_days - 打卡天数
     */
    public Integer getCheckedDays() {
        return checkedDays;
    }

    /**
     * 设置打卡天数
     *
     * @param checkedDays 打卡天数
     */
    public void setCheckedDays(Integer checkedDays) {
        this.checkedDays = checkedDays;
    }

    /**
     * @return unchecked_days
     */
    public Integer getUncheckedDays() {
        return uncheckedDays;
    }

    /**
     * @param uncheckedDays
     */
    public void setUncheckedDays(Integer uncheckedDays) {
        this.uncheckedDays = uncheckedDays;
    }
}
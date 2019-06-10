package me.freelee.betterday.model;

import java.util.Date;
import javax.persistence.*;

public class Habit {
    @Id
    private Integer id;

    private String name;

    private String icon;

    @Column(name = "time_id")
    private Integer timeId;

    /**
     * [1,2,3,4,5,6,7]
     */
    private String weekdays;

    private String description;

    /**
     * 0:个人习惯；1：公共习惯
     */
    @Column(name = "is_public")
    private Integer isPublic;

    /**
     * 0:未删除；1：已删除
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "modify_time")
    private Date modifyTime;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * @return time_id
     */
    public Integer getTimeId() {
        return timeId;
    }

    /**
     * @param timeId
     */
    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }

    /**
     * 获取[1,2,3,4,5,6,7]
     *
     * @return weekdays - [1,2,3,4,5,6,7]
     */
    public String getWeekdays() {
        return weekdays;
    }

    /**
     * 设置[1,2,3,4,5,6,7]
     *
     * @param weekdays [1,2,3,4,5,6,7]
     */
    public void setWeekdays(String weekdays) {
        this.weekdays = weekdays == null ? null : weekdays.trim();
    }

    /**
     * @return desc
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取0:个人习惯；1：公共习惯
     *
     * @return is_public - 0:个人习惯；1：公共习惯
     */
    public Integer getIsPublic() {
        return isPublic;
    }

    /**
     * 设置0:个人习惯；1：公共习惯
     *
     * @param isPublic 0:个人习惯；1：公共习惯
     */
    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    /**
     * 获取0:未删除；1：已删除
     *
     * @return is_delete - 0:未删除；1：已删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 设置0:未删除；1：已删除
     *
     * @param isDelete 0:未删除；1：已删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return modify_time
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
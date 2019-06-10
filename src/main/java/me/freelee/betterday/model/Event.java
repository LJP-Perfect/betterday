package me.freelee.betterday.model;

import java.util.Date;
import javax.persistence.*;

public class Event {
    @Id
    private Integer id;

    private String title;

    private String start;

    private String end;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "create_time")
    private Date createTime;

    private String description;

    @Column(name = "user_name")
    private String userName;

    private Integer sort;

    /**
     * 0:公开；1：私密
     */
    @Column(name = "is_private")
    private Integer isPrivate;

    private String priority;

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
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * @return start
     */
    public String getStart() {
        return start;
    }

    /**
     * @param start
     */
    public void setStart(String start) {
        this.start = start == null ? null : start.trim();
    }

    /**
     * @return end
     */
    public String getEnd() {
        return end;
    }

    /**
     * @param end
     */
    public void setEnd(String end) {
        this.end = end == null ? null : end.trim();
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
     * @return description
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
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * @return sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取0:公开；1：私密
     *
     * @return is_private - 0:公开；1：私密
     */
    public Integer getIsPrivate() {
        return isPrivate;
    }

    /**
     * 设置0:公开；1：私密
     *
     * @param isPrivate 0:公开；1：私密
     */
    public void setIsPrivate(Integer isPrivate) {
        this.isPrivate = isPrivate;
    }

    /**
     * @return priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority
     */
    public void setPriority(String priority) {
        this.priority = priority == null ? null : priority.trim();
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", description='" + description + '\'' +
                ", userName='" + userName + '\'' +
                ", sort=" + sort +
                ", isPrivate=" + isPrivate +
                ", priority='" + priority + '\'' +
                '}';
    }
}
package me.freelee.betterday.model;

import java.util.Date;
import javax.persistence.*;

public class Notice {
    @Id
    private Integer id;

    private String title;

    private String content;

    /**
     * 0：为系统自动发送
     */
    @Column(name = "sender_id")
    private Integer senderId;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 0：对象为全部用户
     */
    @Column(name = "receiver_id")
    private Integer receiverId;

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
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取0：为系统自动发送
     *
     * @return sender_id - 0：为系统自动发送
     */
    public Integer getSenderId() {
        return senderId;
    }

    /**
     * 设置0：为系统自动发送
     *
     * @param senderId 0：为系统自动发送
     */
    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
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
     * 获取0：对象为全部用户
     *
     * @return receiver_id - 0：对象为全部用户
     */
    public Integer getReceiverId() {
        return receiverId;
    }

    /**
     * 设置0：对象为全部用户
     *
     * @param receiverId 0：对象为全部用户
     */
    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }
}
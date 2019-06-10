package me.freelee.betterday.model;

import java.util.Date;
import javax.persistence.*;

public class User {
    @Id
    private Integer id;

    private String name;

    private String password;

    private String email;

    private String phone;

    private String avatar;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "modefy_time")
    private Date modefyTime;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    private String realname;

    /**
     * 0:女；1：男
     */
    private Integer sex;

    @Column(name = "statusStr")
    private String statusstr;

    @Column(name = "user_sn")
    private String userSn;

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
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * @return avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
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
     * @return modefy_time
     */
    public Date getModefyTime() {
        return modefyTime;
    }

    /**
     * @param modefyTime
     */
    public void setModefyTime(Date modefyTime) {
        this.modefyTime = modefyTime;
    }

    /**
     * @return last_login_time
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * @param lastLoginTime
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * @return realname
     */
    public String getRealname() {
        return realname;
    }

    /**
     * @param realname
     */
    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    /**
     * 获取0:女；1：男
     *
     * @return sex - 0:女；1：男
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置0:女；1：男
     *
     * @param sex 0:女；1：男
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * @return statusStr
     */
    public String getStatusstr() {
        return statusstr;
    }

    /**
     * @param statusstr
     */
    public void setStatusstr(String statusstr) {
        this.statusstr = statusstr == null ? null : statusstr.trim();
    }

    /**
     * @return user_sn
     */
    public String getUserSn() {
        return userSn;
    }

    /**
     * @param userSn
     */
    public void setUserSn(String userSn) {
        this.userSn = userSn == null ? null : userSn.trim();
    }
}
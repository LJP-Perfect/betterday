package me.freelee.betterday.model;

import java.util.Date;
import javax.persistence.*;

public class Team {
    @Id
    private Integer id;

    private String name;

    @Column(name = "team_sn")
    private String teamSn;

    /**
     * 队长ID
     */
    @Column(name = "capital_id")
    private Integer capitalId;

    @Column(name = "teammate_ids")
    private String teammateIds;

    private String password;

    @Column(name = "create_time")
    private Date createTime;

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
     * @return team_sn
     */
    public String getTeamSn() {
        return teamSn;
    }

    /**
     * @param teamSn
     */
    public void setTeamSn(String teamSn) {
        this.teamSn = teamSn == null ? null : teamSn.trim();
    }

    /**
     * 获取队长ID
     *
     * @return capital_id - 队长ID
     */
    public Integer getCapitalId() {
        return capitalId;
    }

    /**
     * 设置队长ID
     *
     * @param capitalId 队长ID
     */
    public void setCapitalId(Integer capitalId) {
        this.capitalId = capitalId;
    }

    /**
     * @return teammate_ids
     */
    public String getTeammateIds() {
        return teammateIds;
    }

    /**
     * @param teammateIds
     */
    public void setTeammateIds(String teammateIds) {
        this.teammateIds = teammateIds == null ? null : teammateIds.trim();
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
}
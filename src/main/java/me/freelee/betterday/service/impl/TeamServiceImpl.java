package me.freelee.betterday.service.impl;

import me.freelee.betterday.dao.TeamMapper;
import me.freelee.betterday.model.Team;
import me.freelee.betterday.model.User;
import me.freelee.betterday.service.TeamService;
import me.freelee.betterday.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Date:2019/6/5
 *
 * @author:Lee
 */
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    UserService userService;

    @Override
    public int insertTeam(Team team) {
        return teamMapper.insertSelective(team);
    }

    @Override
    public List<Team> selectTeamsByCapitalId(Integer userId) {
        Example example=new Example(Team.class);
        example.createCriteria().andEqualTo("capitalId",userId);
        return teamMapper.selectByExample(example);
    }

    @Override
    public List<Team> selectTeamsByTeammate(Integer userId) {
        List<Team> teams=new ArrayList<>();
        for(Team team:selectAll()){
            String[] teammateIds=team.getTeammateIds().split(",");
            for(String teammateId:teammateIds){
                if(teammateId.equals(userId.toString())){
                    teams.add(team);
                    break;
                }
            }
        }
        return teams;
    }

    @Override
    public List<Team> selectAll() {
        return teamMapper.selectAll();
    }

    @Override
    public Team selectTeamByName(String teamName) {
        Example example=new Example(Team.class);
        example.createCriteria().andEqualTo("name",teamName);
        return teamMapper.selectOneByExample(example);
    }

    @Override
    public void updateTeam(Team team) {
        teamMapper.updateByPrimaryKeySelective(team);
    }

    @Override
    public Team selectTeamByID(Integer teamId) {
        return teamMapper.selectByPrimaryKey(teamId);
    }

    @Override
    public void deleteTeam(Integer teamId) {
        teamMapper.deleteByPrimaryKey(teamId);
    }

    @Override
    public List<User> selectTeammateIds(Integer teamId) {
        List<User> teammates=new ArrayList<>();
        String teammateIdsStr=teamMapper.selectByPrimaryKey(teamId).getTeammateIds();
        if(teammateIdsStr.equals("")){
            return null;
        }else{
            String[] teammateIdsArray=teammateIdsStr.split(",");
            for(String teammateId:teammateIdsArray){
                teammates.add(userService.selectUserById(Integer.valueOf(teammateId)));
            }
        }
        return teammates;
    }
}

package me.freelee.betterday.controller;

import me.freelee.betterday.dto.JoinTeamParam;
import me.freelee.betterday.model.Team;
import me.freelee.betterday.model.User;
import me.freelee.betterday.service.TeamService;
import me.freelee.betterday.service.UserService;
import me.freelee.betterday.util.DateUtil;
import me.freelee.betterday.util.ResultUtil;
import me.freelee.betterday.util.TransferUtil;
import me.freelee.betterday.vo.TeamDataVO;
import me.freelee.betterday.vo.TeamListVO;
import me.freelee.betterday.vo.TeamVO;
import me.freelee.betterday.vo.TeammateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Description:
 * Date:2019/6/5
 *
 * @author:Lee
 */
@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @Autowired
    UserService userService;

    /** 
    * @Description: 创建团队 
    * @Param: [team] 
    * @return: me.freelee.betterday.util.ResultUtil 
    * @Author: freelee 
    * @Date: 2019/6/5 
    */ 
    @PostMapping("")
    public ResultUtil createTeam(@RequestBody Team team){
        team.setCreateTime(DateUtil.getCurrentTime());
        team.setTeamSn("T"+team.getCapitalId().toString().substring(0,1)+(String.valueOf(DateUtil.getCurrentTime().getTime()).substring(0,5)));
        team.setTeammateIds("");
        int affectRows=teamService.insertTeam(team);
        if(affectRows>=1){
            return ResultUtil.success("创建团队成功",team,null);
        }else{
            return ResultUtil.fail(20002,"创建团队失败",null);
        }
    }

    /** 
    * @Description: 根据用户ID获取与用户有关的所有团队 
    * @Param: [userId] 
    * @return: me.freelee.betterday.util.ResultUtil 
    * @Author: freelee 
    * @Date: 2019/6/5 
    */ 
    @GetMapping("")
    public ResultUtil getAllTeams(@RequestParam("userId") Integer userId){
        List<Team> capitalTeams=teamService.selectTeamsByCapitalId(userId);
        List<TeamVO> capitalTeamVOs=new ArrayList<>();
        for(Team team:capitalTeams){
            TeamVO teamVO=new TeamVO();
            try {
                TransferUtil.fatherToChild(team,teamVO);
            } catch (Exception e) {
                e.printStackTrace();
            }
            teamVO.setEditable(true);
            capitalTeamVOs.add(teamVO);
        }

        List<TeamVO> teammateTeamVOs=new ArrayList<>();
        List<Team> teammateTeams=teamService.selectTeamsByTeammate(userId);
        for(Team team:teammateTeams){
            TeamVO teamVO=new TeamVO();
            try {
                TransferUtil.fatherToChild(team,teamVO);
            } catch (Exception e) {
                e.printStackTrace();
            }
            teamVO.setEditable(false);
            teammateTeamVOs.add(teamVO);
        }
        TeamListVO teamListVO=new TeamListVO();
        teamListVO.setCapitalTeams(capitalTeamVOs);
        teamListVO.setTeammateTeams(teammateTeamVOs);
        return ResultUtil.success("",teamListVO,null);
    }


    @GetMapping("/teammates")
    public ResultUtil getTeammates(@RequestParam("teamId") Integer teamId){
        List<User> teammates=teamService.selectTeammateIds(teamId);
        List<TeammateVO> teammateVOList=new ArrayList<>();
        if(teammates!=null){
            for(User user:teammates){
                TeammateVO teammateVO=new TeammateVO();
                teammateVO.setName(user.getName());
                teammateVO.setRealname(user.getRealname());
                teammateVO.setStatus(user.getStatusstr());
                teammateVO.setId(user.getId());
                teammateVO.setAvatar(user.getAvatar());
                teammateVOList.add(teammateVO);
            }
        }
        TeamDataVO teamDataVO=new TeamDataVO();
        teamDataVO.setTeammateVOList(teammateVOList);
        teamDataVO.setCapitalUser(userService.selectUserById(teamService.selectTeamByID(teamId).getCapitalId()));
        return ResultUtil.success("",teamDataVO,null);
    }

    /** 
    * @Description: 加入团队 
    * @Param: [joinTeamParam] 
    * @return: me.freelee.betterday.util.ResultUtil 
    * @Author: freelee 
    * @Date: 2019/6/5 
    */ 
    @PostMapping("/join")
    public ResultUtil joinTeam(@RequestBody JoinTeamParam joinTeamParam){
        Team team=teamService.selectTeamByName(joinTeamParam.getTeamName());
        if(!team.getPassword().equals(joinTeamParam.getPassword())){
            return ResultUtil.fail(20003,"团队密码错误",null);
        }else{
            team.setTeammateIds((team.getTeammateIds().equals("")? String.valueOf(joinTeamParam.getUserId()):team.getTeammateIds()+","+joinTeamParam.getUserId()));
            teamService.updateTeam(team);
        }
        return ResultUtil.success("加入团队成功",null,null);
    }

    /** 
    * @Description: 退出/踢出团队 
    * @Param: [userId, teamId] 
    * @return: me.freelee.betterday.util.ResultUtil 
    * @Author: freelee 
    * @Date: 2019/6/5 
    */ 
    @PutMapping("/dropout")
    public ResultUtil dropoutTeamMate(@RequestParam("userId") Integer userId,@RequestParam("teamId") Integer teamId){
        Team team =teamService.selectTeamByID(teamId);
        List<String> teammateIds= new ArrayList<String>(Arrays.asList(team.getTeammateIds().split(",")));
        teammateIds.remove(teammateIds.indexOf(userId.toString()));
        String teammateIdsStr="";
        if(teammateIds.size()!=0){
            for(int i=0;i<teammateIds.size();i++){
                if(i==0){
                    teammateIdsStr+=userId;
                }else{
                    teammateIdsStr=teammateIdsStr+","+userId;
                }
            }
        }
        team.setTeammateIds(teammateIdsStr);
        teamService.updateTeam(team);
        return ResultUtil.success("",null,null);
    }

    /** 
    * @Description: 解散团队
    * @Param: [teamId] 
    * @return: me.freelee.betterday.util.ResultUtil 
    * @Author: freelee 
    * @Date: 2019/6/5 
    */ 
    @DeleteMapping("")
    public ResultUtil deleteTeam(@RequestParam("teamId") Integer teamId){
        teamService.deleteTeam(teamId);
        return ResultUtil.success("",null,null);
    }

    /** 
    * @Description: 修改加入团队密码 
    * @Param: [teamId, oldPassword, newPassword] 
    * @return: me.freelee.betterday.util.ResultUtil 
    * @Author: freelee 
    * @Date: 2019/6/5 
    */ 
    @PutMapping("")
    public ResultUtil updateTeamPassword(@RequestParam("teamId") Integer teamId,@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword){
        Team team=teamService.selectTeamByID(teamId);
        if(!team.getPassword().equals(oldPassword)){
            return ResultUtil.fail(20004,"修改失败，原密码错误",null);
        }else{
            team.setPassword(newPassword);
            teamService.updateTeam(team);
        }
        return ResultUtil.success("",null,null);
    }

}

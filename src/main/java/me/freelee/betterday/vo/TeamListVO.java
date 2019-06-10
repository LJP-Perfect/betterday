package me.freelee.betterday.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Description:
 * Date:2019/6/5
 *
 * @author:Lee
 */
@Setter
@Getter
public class TeamListVO {

    List<TeamVO> capitalTeams;
    List<TeamVO> teammateTeams;
}

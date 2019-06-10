package me.freelee.betterday.vo;

import lombok.Getter;
import lombok.Setter;
import me.freelee.betterday.model.User;

import java.util.List;

/**
 * Description:
 * Date:2019/6/6
 *
 * @author:Lee
 */
@Getter
@Setter
public class TeamDataVO {

    User capitalUser;
    List<TeammateVO> teammateVOList;
}

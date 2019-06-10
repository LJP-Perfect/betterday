package me.freelee.betterday.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Description:
 * Date:2019/6/5
 *
 * @author:Lee
 */
@Setter
@Getter
public class JoinTeamParam {

    Integer userId;
    String teamName;
    String password;
}

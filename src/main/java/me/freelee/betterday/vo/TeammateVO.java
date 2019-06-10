package me.freelee.betterday.vo;

import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.GET;

/**
 * Description:
 * Date:2019/6/6
 *
 * @author:Lee
 */
@Getter
@Setter
public class TeammateVO {
    Integer id;
    String status;
    String name;
    String realname;
    String avatar;
}

package me.freelee.betterday.vo;

import lombok.Getter;
import lombok.Setter;
import me.freelee.betterday.model.User;

import javax.persistence.Column;
import java.util.Date;

/**
 * Description:
 * Date:2019/5/30
 *
 * @author:Lee
 */
@Getter
@Setter
public class UserLoginVO  {

    private Integer id;

    private String name;

    private String password;

    private String email;

    private String phone;

    private String avatar;

    private String realname;

    /**
     * 0:女；1：男
     */
    private Integer sex;

    private String statusstr;
}

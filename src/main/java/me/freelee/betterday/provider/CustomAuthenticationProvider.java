package me.freelee.betterday.provider;

import me.freelee.betterday.bean.GrantedAuthorityImpl;
import me.freelee.betterday.model.User;
import me.freelee.betterday.service.UserService;
import me.freelee.betterday.util.DateUtil;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

/**
 * Description:
 * Date:2019/6/7
 *
 * @author:Lee
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    Logger logger= LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user =userService.selectUserByName(name);
        if(user==null){
            throw new BadCredentialsException("用户名不存在");
        }
        // 认证逻辑
        if (bCryptPasswordEncoder.matches(password,user.getPassword())) {
            user.setLastLoginTime(DateUtil.getCurrentTime());
            userService.updateUser(user);
            logger.info("###Test LastLoginTime:"+user.getLastLoginTime());
            // 这里设置权限和角色
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add( new GrantedAuthorityImpl("AUTH_WRITE") );
            // 生成令牌
            Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorities);
            return auth;
        }else {
            throw new BadCredentialsException("密码错误");
        }
    }

    // 是否可以提供输入类型的认证服务
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}


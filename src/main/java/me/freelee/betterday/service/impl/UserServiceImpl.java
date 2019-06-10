package me.freelee.betterday.service.impl;

import lombok.Setter;
import me.freelee.betterday.dao.UserMapper;
import me.freelee.betterday.model.User;
import me.freelee.betterday.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * Description:
 * Date:2019/5/30
 *
 * @author:Lee
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int selectCountByName(String name) {
        Example example=new Example(User.class);
        example.createCriteria().andEqualTo("name",name);
        int count=userMapper.selectCountByExample(example);
        return count;
    }

    @Override
    public int insertUser(User user) {
        int affectRow=userMapper.insertSelective(user);
        return affectRow;
    }

    @Override
    public User selectUserByName(String username) {
        Example example=new Example(User.class);
        example.createCriteria().andEqualTo("name",username);
        return userMapper.selectOneByExample(example);
    }

    @Override
    public User selectUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }
}

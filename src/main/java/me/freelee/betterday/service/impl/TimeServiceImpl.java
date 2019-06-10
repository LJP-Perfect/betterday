package me.freelee.betterday.service.impl;

import me.freelee.betterday.dao.TimeMapper;
import me.freelee.betterday.model.Time;
import me.freelee.betterday.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Description:
 * Date:2019/5/30
 *
 * @author:Lee
 */
@Service
public class TimeServiceImpl implements TimeService {

    @Autowired
    TimeMapper timeMapper;

    @Override
    public List<Time> selectAllTimes(Integer userId){

        return timeMapper.selectTimes(userId);
    }

    @Override
    public void insertTime(Time time) {

    }


}

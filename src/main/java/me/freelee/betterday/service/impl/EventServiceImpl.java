package me.freelee.betterday.service.impl;

import me.freelee.betterday.dao.EventMapper;
import me.freelee.betterday.model.Event;
import me.freelee.betterday.service.EventService;
import me.freelee.betterday.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Description:
 * Date:2019/6/5
 *
 * @author:Lee
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventMapper eventMapper;

    @Autowired
    UserService userService;

    @Override
    public List<Event> selectAllEventsByUserId(Integer userId) {
        Example example=new Example(Event.class);
        example.createCriteria().andEqualTo("userId",userId);
        example.setOrderByClause("sort desc");
        return eventMapper.selectByExample(example);
    }

    @Override
    public int insertEvent(Event event) {
        event.setUserName(userService.selectUserById(event.getUserId()).getName());
        return eventMapper.insertSelective(event);
    }

    @Override
    public Event selectEventById(Integer id) {
        return eventMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateEvent(Event event) {
        eventMapper.updateByPrimaryKeySelective(event);
    }

    @Override
    public void deleteEvent(Integer id) {
        eventMapper.deleteByPrimaryKey(id);
    }
}

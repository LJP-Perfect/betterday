package me.freelee.betterday.dao;

import me.freelee.betterday.model.Time;
import me.freelee.betterday.util.IBaseMapper;

import java.util.List;

public interface TimeMapper extends IBaseMapper<Time> {
    List<Time> selectTimes(Integer userId);
}
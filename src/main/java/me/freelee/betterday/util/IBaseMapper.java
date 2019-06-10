package me.freelee.betterday.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface IBaseMapper<T> extends MySqlMapper<T>, Mapper<T> {
}

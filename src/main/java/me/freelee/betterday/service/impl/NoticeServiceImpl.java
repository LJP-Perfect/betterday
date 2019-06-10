package me.freelee.betterday.service.impl;

import me.freelee.betterday.dao.NoticeMapper;
import me.freelee.betterday.model.Notice;
import me.freelee.betterday.service.NoticeService;
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
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public List<Notice> selectNoticeByUserId(Integer userId) {
        Example example=new Example(Notice.class);
        example.createCriteria().orEqualTo("receiverId",userId).orEqualTo("receiverId",0);
        return noticeMapper.selectByExample(example);
    }

    @Override
    public Integer insertNotice(Notice notice) {
        return noticeMapper.insertSelective(notice);
    }
}

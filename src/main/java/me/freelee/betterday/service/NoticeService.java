package me.freelee.betterday.service;

import me.freelee.betterday.model.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> selectNoticeByUserId(Integer userId);

    Integer insertNotice(Notice notice);
}

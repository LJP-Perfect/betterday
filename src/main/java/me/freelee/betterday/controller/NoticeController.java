package me.freelee.betterday.controller;

import me.freelee.betterday.model.Notice;
import me.freelee.betterday.service.NoticeService;
import me.freelee.betterday.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Description:
 * Date:2019/6/5
 *
 * @author:Lee
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    /** 
    * @Description: 根据用户ID获取系统通知和个人通知
    * @Param: [userId] 
    * @return: me.freelee.betterday.util.ResultUtil 
    * @Author: freelee 
    * @Date: 2019/6/5 
    */ 
    @GetMapping("")
    public ResultUtil getAllNotice(@RequestParam("userId") Integer userId){
        List<Notice> noticeList=noticeService.selectNoticeByUserId(userId);
        return ResultUtil.success("",noticeList,null);
    }

}

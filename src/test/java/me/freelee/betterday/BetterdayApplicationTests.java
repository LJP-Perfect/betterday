package me.freelee.betterday;

import me.freelee.betterday.model.Notice;
import me.freelee.betterday.service.NoticeService;
import me.freelee.betterday.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BetterdayApplicationTests {

    @Autowired
    NoticeService noticeService;

    @Test
    public void testJudgeWeekday() throws ParseException {
        Date today=DateUtil.df.parse(DateUtil.getCurrentTime().toString().split(" ")[0] +" 00:00:00");
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, 1);

        System.out.println(calendar.getTime());
    }

    @Test
    public void testInsertTime(){
        Notice notice=new Notice();
        notice.setCreateTime(DateUtil.getCurrentTime());
        noticeService.insertNotice(notice);
    }
}

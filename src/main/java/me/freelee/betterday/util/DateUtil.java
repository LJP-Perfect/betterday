package me.freelee.betterday.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Description:
 * 日期工具类
 * Date:2019/1/20
 *
 * @author:Lee
 */
public class DateUtil {
    public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static DateFormat df_ymd=new SimpleDateFormat("yyyy-MM-dd");
    public static Date getCurrentTime() {
        Date date = new Date();          // 获取一个Date对象
        Timestamp timeStamp = new Timestamp(date.getTime());
        return timeStamp;
    }

    public static Date dateStrToDate(String dateStr){
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date dateStrToSimpleDate(String dateStr){
        try {
            return df_ymd.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Integer judgeWeekday(String todayTime){
        Calendar c=Calendar.getInstance();
        try {
            c.setTime(df_ymd.parse(todayTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int dayOfWeek=0;
        if(c.get(Calendar.DAY_OF_WEEK)==1){
            dayOfWeek=7;
        }else{
            dayOfWeek=c.get(Calendar.DAY_OF_WEEK)-1;
        }
        return dayOfWeek;
    }

}

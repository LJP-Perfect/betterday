package me.freelee.betterday.controller;

import me.freelee.betterday.enums.PriorityEnums;
import me.freelee.betterday.model.Event;
import me.freelee.betterday.service.EventService;
import me.freelee.betterday.util.DateUtil;
import me.freelee.betterday.util.ResultUtil;
import me.freelee.betterday.util.TransferUtil;
import me.freelee.betterday.vo.EventTableVO;
import me.freelee.betterday.vo.EventVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * Date:2019/6/5
 *
 * @author:Lee
 */
@RequestMapping("/event")
@RestController
public class EventController {

    Logger logger= LoggerFactory.getLogger(EventController.class);

    @Autowired
    EventService eventService;

    /** 
    * @Description: 根据用户ID获取所有日程event 
    * @Param: [userId] 
    * @return: me.freelee.betterday.util.ResultUtil 
    * @Author: freelee 
    * @Date: 2019/6/5 
    */ 
    @GetMapping("")
    public ResultUtil getAllEventByUserId(@RequestParam("userId") Integer userId){
        List<Event> eventList=eventService.selectAllEventsByUserId(userId);
        List<EventVO> eventVOList=new ArrayList<>();
        if(eventList!=null){
            for(Event event:eventList){
                EventVO eventVO=eventToVO(event);
                eventVO.setEditable(true);
                eventVO.setBackgroundColor("");
                eventVOList.add(eventVO);
            }
            return ResultUtil.success("",eventVOList,null);
        }else{
            return ResultUtil.success("",null,null);
        }
    }

    @GetMapping("/eventVo/{id}")
    public ResultUtil getEventVOById(@PathVariable("id") Integer id){
        Event event=eventService.selectEventById(id);
        EventVO eventVO=eventToVO(event);
        return ResultUtil.success("",eventVO,null);
    }


    @GetMapping("/today/events")
    public ResultUtil getTodayEvents(@RequestParam("userId") Integer userId){
        Date today=null;
        Date tomorrow=null;
        try {
            today=DateUtil.df.parse(DateUtil.getCurrentTime().toString().split(" ")[0] +" 00:00:00");
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(today);
            calendar.add(Calendar.DATE, 1);
            tomorrow=calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Event> events=eventService.selectAllEventsByUserId(userId);
        List<Event> todayEvents=new ArrayList<>();
        List<EventTableVO> tableVOList=new ArrayList<>();
        for(Event event:events){
            String eventBegin="";
            String eventEnd="";
            if(event.getStart().split(" ").length==1){
                eventBegin=event.getStart()+" 00:00:00";
                eventEnd=event.getEnd()+" 00:00:00";
            }else if(event.getStart().split(" ")[1].split(":").length==2){
                eventBegin=event.getStart()+":00";
                eventEnd=event.getEnd()+":00";
            }else{
                eventBegin=event.getStart();
                eventEnd=event.getEnd();
            }

            Date eventBeginDate= null;
            Date eventEndDate= null;

            try {
                eventBeginDate = DateUtil.df.parse(eventBegin);
                eventEndDate = DateUtil.df.parse(eventEnd);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(eventBeginDate.getTime()<tomorrow.getTime()&&eventEndDate.getTime()>today.getTime()){
                EventTableVO eventTableVO=new EventTableVO();
                try {
                    TransferUtil.fatherToChild(event,eventTableVO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(eventBeginDate.getTime()>=today.getTime()&&eventBeginDate.getTime()<=tomorrow.getTime()){
                    eventTableVO.setBeginToday(true);
                }
                if(eventEndDate.getTime()>=today.getTime()&&eventEndDate.getTime()<=tomorrow.getTime()){
                    eventTableVO.setEndToday(true);
                }
                tableVOList.add(eventTableVO);
            }


        }
        return ResultUtil.success("",tableVOList,null);
    }

    @GetMapping("/event/{id}")
    public ResultUtil getEventById(@PathVariable("id") Integer id){
        Event event=eventService.selectEventById(id);
        return ResultUtil.success("",event,null);
    }

    /**
    * @Description: 创建event
    * @Param: [event] 
    * @return: me.freelee.betterday.util.ResultUtil 
    * @Author: freelee 
    * @Date: 2019/6/5 
    */ 
    @PostMapping("")
    public ResultUtil addEvent(@RequestBody Event event){
        event.setCreateTime(DateUtil.getCurrentTime());
        event.setSort(PriorityEnums.getCodeByMessage(event.getPriority()));
        int affectRows=eventService.insertEvent(event);
        if(affectRows>=1){
            return ResultUtil.success("创建行程成功",event,null);
        }else{
            return ResultUtil.fail(20001,"创建行程失败",null);
        }
    }

    @PutMapping("")
    public ResultUtil updateEvent(@RequestBody Event event){
        System.out.println(event.toString());
        eventService.updateEvent(event);
        return ResultUtil.success("",event,null);
    }

    @DeleteMapping("")
    public ResultUtil deleteEvent(@RequestParam("id") Integer id){
        eventService.deleteEvent(id);
        return ResultUtil.success("",null,null);
    }

    /** 
    * @Description: event类转eventVO类 
    * @Param: [event] 
    * @return: me.freelee.betterday.vo.EventVO 
    * @Author: freelee 
    * @Date: 2019/6/5 
    */ 
    public EventVO eventToVO(Event event){
        EventVO eventVO=new EventVO();
        try {
            TransferUtil.fatherToChild(event,eventVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventVO;
    }

}

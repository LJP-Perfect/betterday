package me.freelee.betterday.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Description:
 * Date:2019/5/30
 *
 * @author:Lee
 */
@Getter
@Setter
public class TodayHabitVO {

    String action;
    Boolean active;
    String title;
    List<HabitVO> items;
}

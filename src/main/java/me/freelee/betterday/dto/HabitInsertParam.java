package me.freelee.betterday.dto;

import lombok.Getter;
import lombok.Setter;
import me.freelee.betterday.model.Habit;

/**
 * Description:
 * Date:2019/6/5
 *
 * @author:Lee
 */
@Getter
@Setter
public class HabitInsertParam {

    Habit habit;
    Integer userId;
}

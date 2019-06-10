package me.freelee.betterday.vo;

import lombok.Getter;
import lombok.Setter;
import me.freelee.betterday.model.Event;

/**
 * Description:
 * Date:2019/6/5
 *
 * @author:Lee
 */
@Setter
@Getter
public class EventVO extends Event {

    String backgroundColor;
    Boolean editable;
}

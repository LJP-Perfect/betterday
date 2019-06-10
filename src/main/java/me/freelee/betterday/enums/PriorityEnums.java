package me.freelee.betterday.enums;

/**
 * Description:
 * Date:2019/6/5
 *
 * @author:Lee
 */
public enum  PriorityEnums {

    HIGH_PRIORITY(10,"高"),
    MEDIUM_PRIORITY(5,"中"),
    LOW_PRIORITY(1,"低");

    private Integer code;

    private String message;

    PriorityEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String getMessageStrByCode(Integer code){
        for(PriorityEnums priorityEnum:PriorityEnums.values()){
            if(priorityEnum.getCode().equals(code)){
                return priorityEnum.getMessage();
            }
        }
        return null;
    }

    public static Integer getCodeByMessage(String message){
        for(PriorityEnums priorityEnums:PriorityEnums.values()){
            if(priorityEnums.getMessage().equals(message)){
                return priorityEnums.getCode();
            }
        }
        return null;
    }
}

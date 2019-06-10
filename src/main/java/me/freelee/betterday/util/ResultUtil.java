package me.freelee.betterday.util;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;
import me.freelee.betterday.model.User;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * Description:
 * Date:2019/3/25
 *
 * @author:Lee
 */
@Getter
@Setter
public class ResultUtil {

    private int code;
    private String message;
    private String status;
    private Object data;
    private PageInfo pageInfo;

    private static ResultUtil resultUtil=new ResultUtil();

    public static ResultUtil success(String message,Object data,PageInfo pageInfo){
        resultUtil.setMessage(message);
        resultUtil.setData(data);
        resultUtil.setPageInfo(pageInfo);
        resultUtil.setCode(200);
        resultUtil.setStatus("请求成功");
        return resultUtil;
    }

    public static ResultUtil fail(int code,String message,Object data){
        resultUtil.setMessage(message);
        resultUtil.setData(data);
        resultUtil.setCode(code);
        resultUtil.setStatus("请求失败");
        return resultUtil;
    }

    public String loginJson(String username) {
        JSONObject jsonObject = new JSONObject(){{
            try {
                put("code",code);
                put("message", message);
                put("data", data);
                put("username",username);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }};
        return jsonObject.toString();

    }
}

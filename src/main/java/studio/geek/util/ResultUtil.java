package studio.geek.util;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 袁阳
 * @version 2017/4/15.
 */
public class ResultUtil {

    public static final Result SUCCESS_RESULT = new Result("1", "success", null);
    public static final Result FAIL_RESULT = new Result("0", "fail", null);
    public static final Result UNHANDED_RESULT = new Result("0", "未处理异常", null);

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    public static Result respond(HttpServletResponse response,Object object){
        Result result = new Result();
        result.setStatus("1");
        result.setMessage(SUCCESS);
        if (object != null){
            result.setData(object);
        }
        return result;
    }

    public static Result failResult(Object o){
        Result result = new Result();
        result.setStatus("0");
        result.setMessage(FAIL);
        if (o != null){
            result.setData(o);
        }
        return result;
    }

}

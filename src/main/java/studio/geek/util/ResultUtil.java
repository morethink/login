package studio.geek.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class ResultUtil {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    public static void success(HttpServletResponse response, Object object) throws IOException {
        Result result = new Result();
        result.setStatus("0");
        result.setMessage(SUCCESS);
        if (object != null){
            result.setData(object);
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(JsonUtil.toJson(result));
        out.flush();
        out.close();
    }

    public static void fail(HttpServletResponse response, Object object) throws IOException {
        Result result = new Result();
        result.setStatus("1");
        result.setMessage(FAIL);
        if (object != null) {
            result.setData(object);
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(JsonUtil.toJson(result));
        out.flush();
        out.close();
    }


}

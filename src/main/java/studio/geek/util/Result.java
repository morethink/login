package studio.geek.util;

/**
 * 返回结果
 *
 * @author 李文浩
 * @version 2017/4/6.
 */
public class Result {
    private String status;
    private String message;
    private Object data;
    public Result() {
    }

    public Result(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}

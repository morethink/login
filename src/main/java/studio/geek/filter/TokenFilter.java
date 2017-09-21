package studio.geek.filter;

import studio.geek.util.JWTUtil;
import studio.geek.util.ResultUtil;
import studio.geek.util.TokenState;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


public class TokenFilter implements Filter {
    public void doFilter(ServletRequest argo, ServletResponse arg1,
                         FilterChain chain) throws IOException, ServletException {
        System.out.println("haiiiddkkdkd");
        HttpServletRequest request = (HttpServletRequest) argo;
        HttpServletResponse response = (HttpServletResponse) arg1;
        if (request.getRequestURI().endsWith("/login.html")) {
            chain.doFilter(request, response);
            return;
        }
        //其他API接口一律校验token
        System.out.println("开始校验token");
        String token = null;
        //从cookie中获取token
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        } else {
            //从请求头中获取token
            token = request.getHeader("token");
        }

        if (token != null && !"".equals(token)) {
            Map<String, String> resultMap = JWTUtil.verifyToken(token);
            TokenState state = TokenState.getTokenState(resultMap.get("state"));
            System.out.println(state.toString());
            switch (state) {
                case VALID:
                    //取出payload中数据,放入到request作用域中
                    request.setAttribute("username", resultMap.get("username"));
                    break;
                case EXPIRED:
                    ResultUtil.fail(response,"登录过期,请使用refresh_token重新登录");
                    break;
                case INVALID:
                    ResultUtil.fail(response,"登录过期,请重新登录");
                    break;
            }

        } else {
            ResultUtil.fail(response,"登录过期,请重新登录");
        }
    }

    public void init(FilterConfig arg0) throws ServletException {
        System.out.println("token过滤器初始化了");
    }

    public void destroy() {

    }
}

package studio.geek.servelet;

import studio.geek.dao.DBdao;
import studio.geek.entity.User;
import studio.geek.util.JWTUtil;
import studio.geek.util.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username:" + username);
        System.out.println("password:" + password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        DBdao dBdao = new DBdao();
        //登录成功
        try {
            if (dBdao.login(user)) {
                if (request.getParameter("remember").equals("true")) {
                    String token = JWTUtil.createToken(user);
                    Cookie tokenCookie = new Cookie("token", token);
                    response.addCookie(tokenCookie);
                    tokenCookie.setMaxAge(10 * 24 * 60 * 60);
                    System.out.println("token添加成功");
                } else {
                    Cookie[] cookies = request.getCookies();
                    if (cookies != null && cookies.length > 0) {
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("token")) {
                                cookie.setMaxAge(0);
                                response.addCookie(cookie);
                            }
                        }
                    }
                }
                ResultUtil.success(response, null);
            } else {
                ResultUtil.fail(response, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

//    private static void updateCookies(User user, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
//        String isUseCookie = request.getParameter("isUseCookie");
//        isUseCookie = URLDecoder.decode(isUseCookie, "UTF-8");
//        System.out.println(isUseCookie);
//        if (isUseCookie.equals("true")) {
//            System.out.println("isUseCookie");
//            String username = user.getUsername();
//            String password = user.getPassword();
//
//            Cookie usernameCookie = new Cookie("username", username);
//            Cookie passwordCookie = new Cookie("password", password);
//            usernameCookie.setMaxAge(864000);
//            passwordCookie.setMaxAge(864000);
//            response.addCookie(usernameCookie);
//            response.addCookie(passwordCookie);
//        } else {
//            System.out.println("no - isUseCookie");
//            Cookie[] cookies = request.getCookies();
//            if (cookies != null && cookies.length > 0) {
//                for (Cookie cookie : cookies) {
//                    if (cookie.getName().equals("username") || cookie.getName().equals("password")) {
//                        cookie.setMaxAge(0);
//                        response.addCookie(cookie);
//                    }
//                }
//            }
//        }
//    }

}
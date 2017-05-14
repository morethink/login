package studio.geek.servelet;

import studio.geek.dao.DBdao;
import studio.geek.entity.User;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
//        username = URLDecoder.decode(username, "UTF-8");
        String password = request.getParameter("password");
//        password = URLDecoder.decode(password, "UTF-8");
        System.out.println("username:" + username);
        System.out.println("password:" + password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
//        System.out.println("ddd");
//        String json = request.getParameter("json");
//        System.out.println(json);

//        User entity = gson.fromJson(json,User.class);
//        System.out.println(entity + "json");
        DBdao dBdao = new DBdao();
        String result = "";

        try {
            if (dBdao.login(user)) {
                result = "success";
                request.getSession().setAttribute("username", username);
                request.getSession().setAttribute("password", password);
                updateCookies(user, request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        Map<String, String> map = new HashMap<String, String>();
        map.put("result", result);

        JSONObject json = new JSONObject(map);
        out.print(json);
        out.flush();
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    private static void updateCookies(User user, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String isUseCookie = request.getParameter("isUseCookie");
        isUseCookie = URLDecoder.decode(isUseCookie, "UTF-8");
        System.out.println(isUseCookie);
        if (isUseCookie.equals("true")) {
            System.out.println("isUseCookie");
            String username = user.getUsername();
            String password = user.getPassword();

            Cookie usernameCookie = new Cookie("username", username);
            Cookie passwordCookie = new Cookie("password", password);
            usernameCookie.setMaxAge(864000);
            passwordCookie.setMaxAge(864000);
            response.addCookie(usernameCookie);
            response.addCookie(passwordCookie);
        } else {
            System.out.println("no - isUseCookie");
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("username") || cookie.getName().equals("password")) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
        }
    }

}
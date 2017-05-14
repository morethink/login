package studio.geek.servelet;

import studio.geek.dao.DBdao;
import studio.geek.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

/**
 * Created by think on 2016/10/25.
 */
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        username = URLDecoder.decode(username, "UTF-8");
        String password = request.getParameter("password");
        password = URLDecoder.decode(password, "UTF-8");
        System.out.println("username:" + username);
        System.out.println("password:" + password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        DBdao dBdao = new DBdao();
        String result = "false";
        try {
            if (!dBdao.register(user)) {
                result ="true";
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }


        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //将数据拼接成JSON格式
//        out.print("{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}");
        System.out.println(result);
        out.print("{\"success\":\"" + result + "\"}");
        out.flush();
        out.close();
        //System.out.println(request.getSession().getId());
    }
}

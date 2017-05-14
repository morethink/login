package studio.geek.servelet;

import studio.geek.dao.DBdao;

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
public class VerifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        username = URLDecoder.decode(username, "UTF-8");
        DBdao dBdao = new DBdao();
        String result = "false";
        System.out.println(username);
        try {
            if (dBdao.verify(username)) {
                result = String.valueOf(dBdao.verify(username));
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }


        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //将数据拼接成JSON格式
//        out.print("{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}");

        out.print("{\"success\":\"" + result + "\"}");
        out.flush();
        out.close();
        //System.out.println(request.getSession().getId());
    }
}

package studio.geek.servelet;


import studio.geek.dao.DBdao;
import studio.geek.entity.Records;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by think on 2016/10/17.
 */
public class IndexServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBdao dBdao = new DBdao();
        List<Records> recordses = null;
        try {
            recordses = dBdao.index("44");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JSONObject allJson = new JSONObject();
        JSONArray jsonArray = new JSONArray(recordses);
        try {
            allJson.put("username", "123");
            allJson.put("book",jsonArray);
            System.out.println(allJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print(allJson);
        out.flush();
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

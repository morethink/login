package studio.geek.dao;


import studio.geek.util.DBConnection;
import studio.geek.entity.Records;
import studio.geek.entity.User;
import org.json.JSONException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2016/10/14.
 */
public class DBdao {
    public boolean login(User user) throws Exception {
        boolean result = false;
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM readers WHERE username = ? AND password = ?";
        PreparedStatement ptmt = connection.prepareStatement(sql);
        ptmt.setString(1, user.getUsername());
        ptmt.setString(2, user.getPassword());
        ResultSet rs = ptmt.executeQuery();
        if (rs.next()) {
            result = true;
        }
        return result;
    }

    public boolean verify(String username) throws SQLException, SQLException {
        boolean result = false;
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM readers WHERE username = ?";
        PreparedStatement ptmt = connection.prepareStatement(sql);
        ptmt.setString(1, username);
        ResultSet rs = ptmt.executeQuery();
        if (rs.next()) {
            result = true;
        }
        return result;
    }

    public boolean register(User user) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "INSERT INTO readers(username,password) VALUES(?,?)";
        PreparedStatement ptmt = connection.prepareStatement(sql);
        ptmt.setString(1, user.getUsername());
        ptmt.setString(2, user.getPassword());
        boolean result = ptmt.execute();
        return result;
    }

    public List<Records> index(String username) throws SQLException {
        boolean result = false;
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT book_name,outtime,intime FROM records,books WHERE reader_id = (SELECT reader_id FROM readers WHERE username = ?) AND books.book_id  = records.book_id";
        PreparedStatement ptmt = connection.prepareStatement(sql);
        ptmt.setString(1, username);
        ResultSet rs = ptmt.executeQuery();
        Records record;
        List<Records> list = new ArrayList<Records>();
        while (rs.next()) {
            record = new Records();
            record.setBookName(rs.getString("book_name"));
            record.setOutTime(rs.getString("outtime"));
            record.setInTime(rs.getString("intime"));

            list.add(record);
        }
        return list;
    }

    public static void main(String[] args) throws SQLException, JSONException {
        DBdao dBdao = new DBdao();
//        List<Records> recordses = dBdao.index("44");
//        JSONObject allJson = new JSONObject();
//        JSONArray jsonArray = new JSONArray(recordses);
//        allJson.put("username", "123");
//        allJson.put("book",jsonArray);
//        System.out.println(allJson);
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT book_name,outtime,intime FROM records,books WHERE reader_id = (SELECT reader_id FROM readers WHERE username = ?) AND books.book_id  = records.book_id";
        PreparedStatement ptmt = connection.prepareStatement(sql);
        ptmt.setString(1, "44");
        ResultSet rs = ptmt.executeQuery();

        List<Records> list = new ArrayList<Records>();
//        while (rs.next()) {
//            Records record = new Records();
//            record.setBookName(rs.getString("book_name"));
//            record.setOutTime(rs.getString("outtime"));
//            record.setInTime(rs.getString("intime"));
//            System.out.println(record.getBookName());
//        }
        System.out.println(rs.isFirst());
        rs.next();
        System.out.println(rs.getRow());
        System.out.println(rs.isFirst());

    }
}

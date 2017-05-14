package studio.geek.servelet;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by think on 2016/11/11.
 */
public class CodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        BufferedImage bufferedImage = new BufferedImage(68,22,BufferedImage.TYPE_INT_RGB);
//
//        Graphics graphics = bufferedImage.getGraphics();
//
//        Color color = new Color(200,150,255);
//
//        graphics.setColor(color);
//
//        graphics.fillRect(0,0,68,22);

        BufferedImage bi = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        Color c = new Color(200, 150, 255);
        g.setColor(c);
        g.fillRect(0, 0, 68, 22);
        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Random r = new Random();
        int len = ch.length, index;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            index = r.nextInt(len);
            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
            g.drawString(ch[index] + "", (i * 15) + 3, 18);
            sb.append(ch[index]);
        }
        request.getSession().setAttribute("piccode", sb.toString());
//        ImageIO.write(bi, "JPG", response.getOutputStream());
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(response.getOutputStream());
        encoder.encode(bi);


        System.out.println("Checkcode is successful");

//        PrintWriter out = response.getWriter();
//        out.print("jj");
//        out.flush();
//        out.close();
    }
}

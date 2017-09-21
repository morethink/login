package studio.geek.util;

import org.junit.Test;
import studio.geek.entity.User;

/**
 * @author 李文浩
 * @version 2017/5/20.
 */
public class JWTUtilTest {

    @Test
    public void testCreatToken() {
        JWTUtil jwtUtil = new JWTUtil();
//        jwtUtil.createToken();
        User user = new User();
        user.setUsername("2015210992");
        jwtUtil.verifyToken(jwtUtil.createToken(user));

    }
}

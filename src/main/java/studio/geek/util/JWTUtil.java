package studio.geek.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import studio.geek.entity.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 李文浩
 * @version 2017/5/20.
 */
public class JWTUtil {
    /**
     * 秘钥
     */
    private static final byte[] SECRET = "liwenhao".getBytes();

    /**
     * 头部
     */

    private static final Map<String, Object> headerClaims = new HashMap<String, Object>();

    static {
        headerClaims.put("alg", "HS256");
    }

    public static String createToken(User user) {
        String token = null;
        try {
            Date date = new Date();
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            token = JWT.create()
                    .withHeader(headerClaims) // 头部
                    .withIssuer("auth0")
                    .withClaim("username", user.getUsername())  //用户ID
                    .withIssuedAt(date) //生成时间
                    .withExpiresAt(new Date(date.getTime()+10 * 24 * 60 * 60))
                    .sign(algorithm); // 签名
        } catch (JWTCreationException exception) {
            System.out.println("Couldn't convert Claims");
        }
        return token;
    }

    public static Map<String, String> verifyToken(String token) {
        Map<String, String> resultMap = new HashMap<String, String>();
        String state = TokenState.VALID.toString();
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);

            // token校验成功（此时没有校验是否过期）
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            // 若payload包含exp字段，则校验是否过期
            if (claims.containsKey("exp")) {
                long extTime = claims.get("exp").asLong();
                long currentTime = new Date().getTime();
                // 过期
                System.out.println(currentTime);
                System.out.println(extTime);
                System.out.println(currentTime > extTime);
                System.out.println(new Date().getTime()+10 * 24 * 60 * 60);
                if (currentTime > extTime) {
                    state = TokenState.EXPIRED.toString();
                } else {
                    String username = claims.get("username").asString();
                    resultMap.put("username", username);
                }

            }

        } catch (JWTVerificationException exception) {
            state = TokenState.INVALID.toString();
        }
        resultMap.put("state", state);
        return resultMap;
    }
}

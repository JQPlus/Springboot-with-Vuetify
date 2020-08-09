package com.explore.galaxy.basic.utils.token;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.explore.galaxy.basic.consts.response.Codes;
import com.explore.galaxy.basic.enums.token.EClaim;
import com.explore.galaxy.basic.modules.user.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Token 工具类
 */
public class TokenUtils {

    //通过HMAC256算法将字符串编码为一个字节序列，结果存储到新的字节数组中
    private static Algorithm algorithm = Algorithm.HMAC256("ProjectStructure");


    /**
     * @return: Token
     * @description: get Token
     */
    public static String initToken(UserEntity userEntity) {
        return createToken(userEntity);
    }

    /**
     * @return: Token
     * @description: 自定义创建Token
     */
    public static String createToken(UserEntity userEntity) {
        String token = "";
        Date effictTime = new Date();
        Date expireTime = new Date(System.currentTimeMillis() + (30 * 60 * 1000));
        try {
            token = JWT.create().withIssuedAt(effictTime) //签署token时间
                    .withExpiresAt(expireTime) // 过期时间（毫秒）
                    .withClaim(EClaim.USER_ID.toString(), userEntity.getUserID())// 将 user id 声明到 token 里面
                    .sign(algorithm);//用algorithm来签署token令牌，验证时需要一致
        } catch (JWTCreationException exception) {
            // Invalid Signing configuration / Couldn't convert Claims.
            exception.printStackTrace();
        }
        return token;
    }

    /**
     * @return:注意:如果验证token已失效或者不正确，则会通过JWTVerificationException异常来抛出
     * @description: 验证token是否正确(时效或者是否与生成的的token一致)
     */
    public static Map<String, Object> verifyToken(String token, UserEntity userEntity) {
        Map<String, Object> map = new HashMap<>();
        try {
            JWTVerifier verifier = JWT.require(algorithm).build(); // Reusable verifier instance
            verifier.verify(token);// 对token进行有效期验证，错误则抛出异常
            token = createToken(userEntity);//重新创建token
            map.put("code", Codes.CODE_ALL_CORRECT);
            map.put("token", token);
            map.put("userName", userEntity.getUserName());
        } catch (JWTVerificationException exception) {
            map.put("code", Codes.CODE_TOKEN_OVERVUE);
            map.put("token", "");
        }
        return map;
    }

    /**
     * @param type:  Token中所申明需要的交互信息
     * @return:
     * @auther: Huang Jiaqi
     * @date 4/19/2020 11:48 PM
     * @description: 根据枚举值，返回特定的交互信息
     */
    public static String getClaimMessage(HttpServletRequest request, EClaim type) {
        String resultMessage;
        String token = getToken(request);
        resultMessage = JWT.decode(token).getClaim(type.toString()).asString();
        return resultMessage;
    }

    /**
     * @return:token
     * @auther: Huang Jiaqi
     * @date 4/20/2020 1:37 AM
     * @description: 根据header返回token
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return token;
    }
}

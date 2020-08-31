package com.example.mall.common.untils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * 生成JwtToken的工具类
 * JWT token的格式：header.payload.signature (header中用于存放签名的生成算法 \ payload中用于存放用户名、token的生成时间和过期时间 \ signature为以header和payload生成的签名，一旦header和payload被篡改，验证将失败
 * @author hsqzs
 * date 2020/8/31 17:13
 */
@Component
public class JwtTokenUntil {
    private static final Logger LOGGER =  LoggerFactory.getLogger(JwtTokenUntil.class);
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 生成 jwt token
     * @param claims 实体（通常指的用户）的状态和额外的元数据
     * @return token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

    }

    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                       //载荷为claims（即Json），已签名 (parseClaimsJwt 载荷为claims（即Json），未签名)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e) {
            LOGGER.info("JWT格式验证失败: {}", token);
        }
        return claims;
    }
    /**
     * 生成过期时间
     * @return 当前时间 + 过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }
}

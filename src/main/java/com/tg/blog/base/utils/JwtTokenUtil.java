package com.tg.blog.base.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;


@Slf4j
public class JwtTokenUtil {

    public String createToken(){
        return null;
    }

    public static Optional<Claim> getCliamInfo(String token , String cliamName){
        try {
          return Optional.ofNullable(JWT.decode(token).getClaim(cliamName));
        }catch (Exception e){
          log.error("JwtTokenUtil获取Claim失败",e);
          return Optional.empty();
        }
    }
}

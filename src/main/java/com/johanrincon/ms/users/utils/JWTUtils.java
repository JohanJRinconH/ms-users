package com.johanrincon.ms.users.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public final class JWTUtils {

  private JWTUtils() {
  }

  public static String generateJWT(String email, String secretKey) {
    Date now = DateUtils.getDate();
    Date exp = DateUtils.getDatePlusDays(1);

    return JWT.create()
            .withSubject(email)
            .withIssuedAt(now)
            .withExpiresAt(exp)
            .sign(Algorithm.HMAC256(secretKey));
  }
}

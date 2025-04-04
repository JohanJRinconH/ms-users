package com.johanrincon.ms.users.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityUtils {

  private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  /**
   * Constructor privado para evitar instanciación
   */
  private SecurityUtils() {}

  public static String encryptPassword(String password) {
    return passwordEncoder.encode(password);
  }

  public static boolean checkPassword(String rawPassword, String encodedPassword) {
    return passwordEncoder.matches(rawPassword, encodedPassword);
  }

}

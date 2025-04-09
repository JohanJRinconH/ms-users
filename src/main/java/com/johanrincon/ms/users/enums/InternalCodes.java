package com.johanrincon.ms.users.enums;

import org.springframework.http.HttpStatusCode;

/**
 * Internal errors constants enum
 */
public enum InternalCodes {

  REQUIRED_DATA_EMPTY(1, "Campos obligatorios vacios", HttpStatusCode.valueOf(400)),
  REQUIRED_HEADERS_EMPTY(2, "Headers obligatorios vacios", HttpStatusCode.valueOf(400)),
  OPERATION_FAILED(2, "La operacion fallo", HttpStatusCode.valueOf(400)),
  USER_NOT_AUTHORIZED(3, "Usuario no autorizado", HttpStatusCode.valueOf(401)),
  FORBIDDEN(4, "No tiene permisos para acceder a la informacion", HttpStatusCode.valueOf(403)),
  USER_NOT_FOUND(5, "No se encuentran datos", HttpStatusCode.valueOf(404)),
  INTERNAL_SERVER_ERROR(4, "Error interno del servidor", HttpStatusCode.valueOf(500)),
  SERVICE_UNAVAILABLE(5, "Servicio no disponible", HttpStatusCode.valueOf(503)),
  GATEWAY_TIMEOUT(6, "Tiempo de respuesta mayor a lo esperado", HttpStatusCode.valueOf(504)),
  SQL_EXCEPTION(7, "Error en motor de base de datos", HttpStatusCode.valueOf(404));

  private final int value;
  private final String reasonPhrase;
  private final HttpStatusCode httpStatusCode;

  /**
   * Constructor
   *
   * @param value
   * @param reasonPhrase
   * @param httpStatusCode
   */
  InternalCodes(int value, String reasonPhrase, HttpStatusCode httpStatusCode) {
    this.value = value;
    this.reasonPhrase = reasonPhrase;
    this.httpStatusCode = httpStatusCode;
  }

  /**
   * Returns the value
   *
   * @return {@link String} The object's string
   */
  public int value() {
    return this.value;
  }

  /**
   * Returns the reason phrase
   * @return {@link String} The error's reason phrase
   */
  public String getReasonPhrase() {
    return this.reasonPhrase;
  }

  /**
   * Returns the object's String
   * @return {@link String} The error's string
   */
  @Override
  public String toString() {
    return this.value + " " + name();
  }

  /**
   * Returns the HTTP status code
   * @return {@link HttpStatusCode} The error's HTTP status code
   */
  public HttpStatusCode getHttpStatusCode() {
    return httpStatusCode;
  }

}
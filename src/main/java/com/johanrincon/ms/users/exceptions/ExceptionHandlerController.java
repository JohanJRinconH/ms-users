package com.johanrincon.ms.users.exceptions;

import com.johanrincon.ms.users.dtos.MessageDTO;
import com.johanrincon.ms.users.utils.HeadersUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.johanrincon.ms.users.enums.InternalCodes.INTERNAL_SERVER_ERROR;
import static com.johanrincon.ms.users.enums.InternalCodes.OPERATION_FAILED;

@ControllerAdvice
public class ExceptionHandlerController {

  static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

  @ExceptionHandler(UsuarioException.class)
  @ResponseBody
  ResponseEntity<MessageDTO<String>> customException(final UsuarioException ex) {
    return new ResponseEntity<>(
            new MessageDTO<>(ex.getInternalCodes().getHttpStatusCode().value(), ex.getMessage()),
            HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  @ResponseBody
  ResponseEntity<MessageDTO<String>> handleException(final Exception ex) {
    logger.info("handleException - init");
    logger.error("Se ha capturado un error", ex);
    MessageDTO<String> response = handleResponseMessage(ex);
    logger.info("handleException - end");
    return new ResponseEntity<>(response, HeadersUtils.getGenericHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * This method should handle every kind of exception and map it to an HTTP response
   *
   * @param ex {@link Exception} An exception to be handled
   * @return {@link MessageDTO} A response message mapped from the handled exception
   */
  private MessageDTO<String> handleResponseMessage(final Exception ex) {
    if (ex instanceof MethodArgumentNotValidException) {
      return new MessageDTO<>(OPERATION_FAILED.getHttpStatusCode().value(), ((MethodArgumentNotValidException) ex)
              .getBindingResult().getAllErrors().get(0).getDefaultMessage());
    } else {
      return new MessageDTO<>(INTERNAL_SERVER_ERROR.getHttpStatusCode().value(),
              INTERNAL_SERVER_ERROR.getReasonPhrase());
    }
  }

}
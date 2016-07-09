package com.laundryguy.controller;

import com.laundryguy.model.apiResponse.MessageApiResponse;
import com.laundryguy.model.apiResponse.RestApiResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by maninder on 9/7/16.
 */
@Component
public class ExceptionController {

    private static final Logger logger = LogManager.getLogger(ExceptionController.class);

    private String getErrorMessage(BindingResult bindingResult) {
        FieldError fieldError = bindingResult.getFieldError();
        String defaultMessage = fieldError.getDefaultMessage();
        return fieldError.getField() + " " + defaultMessage;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected
    @ResponseBody
    RestApiResponse invalidRequestExceptionHandler(MethodArgumentNotValidException exception) {
        String code = "401";
        String message = getErrorMessage(exception.getBindingResult());
        return RestApiResponse.buildFail(MessageApiResponse.build(code, message));
    }

    /**
     * This method handles general DB Exception
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected
    @ResponseBody
    RestApiResponse genericExceptionHandler(Exception exception) {
        logger.error(exception.toString(), exception);
        return RestApiResponse.buildFail(MessageApiResponse.build("500", "Sorry ,try again later"));
    }

}

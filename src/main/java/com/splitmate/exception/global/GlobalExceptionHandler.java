package com.splitmate.exception.global;

import com.splitmate.enums.ErrorCodes;
import com.splitmate.exception.error.BadRequestException;
import com.splitmate.exception.error.ErrorMessage;
import com.splitmate.exception.error.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorMessage handleResourceNotFound(
            ResourceNotFoundException ex) {

        return new ErrorMessage(
                ErrorCodes.USERS_NOT_FOUND.name(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ErrorMessage handleBadRequest(
            BadRequestException ex) {

        return new ErrorMessage(
                ErrorCodes.INVALID_USER_LIST.name(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(Exception.class)
    public ErrorMessage handleGenericException(
            Exception ex) {

        return new ErrorMessage(
            ErrorCodes.INTERNAL_SERVER_ERROR.name(),
                ex.getMessage()
        );
    }
}


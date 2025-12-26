package com.splitmate.exception.error;

import com.splitmate.enums.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private LocalDateTime timestamp = LocalDateTime.now();
    private String code;
    private String message;

    public ErrorMessage(String errorCode, String message) {
        this.code = errorCode;
        this.message = message;
    }

}

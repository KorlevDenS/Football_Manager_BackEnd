package com.den.korolev.football_manager.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {

    private String exception;

    private String description;
    public ExceptionResponse(String exception, String description) {
        this.exception = exception;
        this.description = description;
    }
}

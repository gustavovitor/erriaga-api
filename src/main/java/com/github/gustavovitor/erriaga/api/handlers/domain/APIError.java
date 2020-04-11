package com.github.gustavovitor.erriaga.api.handlers.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class APIError {
    private Integer errorCount;
    private List<ErrorHandler> errors;

    public APIError(Integer errorCount, List<ErrorHandler> errors) {
        this.errorCount = errorCount;
        this.errors = errors;
    }
}
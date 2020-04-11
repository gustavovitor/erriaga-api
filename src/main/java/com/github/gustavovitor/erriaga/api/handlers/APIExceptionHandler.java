package com.github.gustavovitor.erriaga.api.handlers;

import com.github.gustavovitor.erriaga.api.handlers.domain.APIError;
import com.github.gustavovitor.erriaga.api.handlers.domain.ErrorCodes;
import com.github.gustavovitor.erriaga.api.handlers.domain.ErrorHandler;
import com.github.gustavovitor.util.MessageUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String mensagem = MessageUtil.getMessage("invalid.message.exception");
        List<ErrorHandler> erros = Arrays.asList(new ErrorHandler(mensagem, ex.getCause() != null ? ex.getCause().toString() : ex.toString()));
        return handleExceptionInternal(ex, new APIError(erros.size(), erros), headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ErrorHandler> erros = createErrorList(ex.getBindingResult());
        return handleExceptionInternal(ex, new APIError(erros.size(), erros), headers, HttpStatus.BAD_REQUEST, request);
    }

//    @ExceptionHandler({RuntimeException.class})
//    public ResponseEntity<Object> defaultError(RuntimeException ex, WebRequest request) {
//        String mensagem = MessageUtil.getMessage("generic.exception");
//        List<ErrorHandler> erros = Arrays.asList(new ErrorHandler(mensagem, ex.toString(), ErrorCodes.GENERIC, ex.getMessage()));
//        return handleExceptionInternal(ex, new APIError(erros.size(), erros), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
//    }

    private List<ErrorHandler> createErrorList(BindingResult bindingResult) {
        List<ErrorHandler> errs = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errs.add(new ErrorHandler(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return errs;
    }
}

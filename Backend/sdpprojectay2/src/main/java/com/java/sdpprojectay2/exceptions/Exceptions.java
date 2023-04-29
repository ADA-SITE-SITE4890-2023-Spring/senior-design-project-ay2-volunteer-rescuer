package com.java.sdpprojectay2.exceptions;


import com.java.sdpprojectay2.dto.Response;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class Exceptions extends Throwable {

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NotFoundException.class)
    public Response notFoundException() {
        return new Response().setCode(HttpStatus.NOT_FOUND.value()).setMessage("Data Not Found");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(AlreadyExistException.class)
    public Response alreadyExistException() {
        return new Response().setCode(HttpStatus.ALREADY_REPORTED.value()).setMessage("Data Already Exist");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingParameterException.class)
    public Response missingParameterException(MissingParameterException e) {
        return new Response().setCode(HttpStatus.BAD_REQUEST.value()).setMessage("Missing Parameter");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(AuthErrorException.class)
    public Response passwordWrongException() {
        return new Response().setCode(HttpStatus.UNAUTHORIZED.value()).setMessage("Username or Password Wrong");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(AccessDeniedException.class)
    public Response accessDeniedException(AccessDeniedException e) {
        return new Response().setCode(HttpStatus.UNAUTHORIZED.value()).setMessage("Access Denied");
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Response dataIntegrityViolationException(DataIntegrityViolationException e) {
        return new Response().setCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).setMessage(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    public Response invalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException e) {
        return new Response().setCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).setMessage(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(JpaSystemException.class)
    public Response jpaSystemException(JpaSystemException e) {
        return new Response().setCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).setMessage(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(InternalServerErrorException.class)
    public Response internalErrorException(InternalServerErrorException e) {
        return new Response().setCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).setMessage(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response httpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new Response().setCode(HttpStatus.BAD_REQUEST.value()).setMessage(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new Response().setCode(HttpStatus.BAD_REQUEST.value()).setMessage(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Response missingServletRequestParameterException(MissingServletRequestParameterException e) {
        return new Response().setCode(HttpStatus.BAD_REQUEST.value()).setMessage(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Response methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return new Response().setCode(HttpStatus.BAD_REQUEST.value()).setMessage(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return new Response().setCode(HttpStatus.METHOD_NOT_ALLOWED.value()).setMessage(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Response HttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return new Response().setCode(HttpStatus.METHOD_NOT_ALLOWED.value()).setMessage(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(AuthenticationException.class)
    public Map<String, Object> authenticationException() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.UNAUTHORIZED.value());
        map.put("message", "Sorry, your token expired or not exists.");
        return map;
    }
}

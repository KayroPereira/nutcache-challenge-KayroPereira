package com.nutcache.challenge_people_management.exception;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
//    @Override
//    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
////        return new ResponseEntity<ErrorResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
//        return super.handleNoHandlerFoundException(ex, headers, status, request);
//    }

    @ExceptionHandler(PersonNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleNotFoundException(PersonNotFoundException ex, WebRequest request) {
        ErrorResponse exceptionResponse = new ErrorResponse(new Date(), HttpStatus.NOT_FOUND.toString(),  ex.getMessage(), request.getDescription(false),
                Arrays.asList(new ErrorObject(ex.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase(), String.format("%d", HttpStatus.NOT_FOUND.value()))));
        
        return new ResponseEntity<ErrorResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
//
//    @Override
//    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleMissingServletRequestParameter(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleNoHandlerFoundException(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
////        return super.handleTypeMismatch(ex, headers, status, request);
//        List<ErrorObject> errorObjects = new ArrayList<>();
//
//        try {
//            TypeMismatchException errorResponses = ((TypeMismatchException) ex);
//
//            errorObjects.add(new ErrorObject(errorResponses.getMessage(), ((MethodArgumentTypeMismatchException) errorResponses).getName(), errorResponses.getValue().toString()));
//        }finally {
//            ErrorResponse exceptionResponse = new ErrorResponse(new Date(), status.toString(),
//                    ex.getErrorCode() + " - " + ((MethodArgumentTypeMismatchException) ex).getParameter().getExecutable().getName(),
//                    request.getDescription(false), errorObjects);
//            return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleConversionNotSupported(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleBindException(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
//        return super.handleAsyncRequestTimeoutException(ex, headers, status, webRequest);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleServletRequestBindingException(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleMissingServletRequestPart(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleMissingPathVariable(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleHttpMessageNotWritable(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//        List<ErrorObject> errorObjects = new ArrayList<>();
//
//        try {
//            errorObjects.add(new ErrorObject(ex.getMessage(), "", ""));
//        }finally {
//            ErrorResponse exceptionResponse = new ErrorResponse(new Date(), status.toString(),  ex.getRootCause().getLocalizedMessage().split("\n")[0], request.getDescription(false), errorObjects);
//            return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleMethodArgumentNotValid(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//        List<ErrorObject> errorObjects = new ArrayList<>();
//
//        try {
//            List<FieldError> errorResponses = ((MethodArgumentNotValidException) ex).getFieldErrors().stream().collect(Collectors.toList());
//
//            errorObjects = errorResponses.stream().map(e -> new ErrorObject(e.getDefaultMessage(), e.getField(), e.getCode())).collect(Collectors.toList());
//        }finally {
//            ErrorResponse exceptionResponse = new ErrorResponse(new Date(), status.toString(),  ex.getMessage(), request.getDescription(false), errorObjects);
//            return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//        List<ErrorObject> errorObjects = new ArrayList<>();
//
//        try {
//            List<Field> errorResponses = Arrays.stream(ex.getClass().getDeclaredFields()).collect(Collectors.toList());
//
//
//            errorObjects = errorResponses.stream().map(e -> new ErrorObject(e.getName(), e.toString(), "")).collect(Collectors.toList());
//        }finally {
//            ErrorResponse exceptionResponse = new ErrorResponse(new Date(), status.toString(),  ex.getMessage(), request.getDescription(false), errorObjects);
//            return new ResponseEntity<Object>(exceptionResponse, HttpStatus.METHOD_NOT_ALLOWED);
//        }
//    }
//
////    @ExceptionHandler({ Exception.class })
//    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
//        ErrorObject apiError = new ErrorObject(HttpStatus.INTERNAL_SERVER_ERROR+"", ex.getLocalizedMessage(), "error occurred");
//        return new ResponseEntity<Object>(apiError, new HttpHeaders(), Integer.parseInt(apiError.getMessage()));
//    }
//
//    @ExceptionHandler
//    @ResponseBody
////    public String handleExampleException(Exception e) {
////    public String handleException(Exception e) {
////        return "dfljads";
////    }
////    protected ResponseEntity<Object> handleException(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//    protected ResponseEntity<Object> handleException(Exception ex) {
//
//        List<ErrorObject> errorObjects = new ArrayList<>();
//
//        try {
//            List<Field> errorResponses = Arrays.stream(ex.getClass().getDeclaredFields()).collect(Collectors.toList());
//
//
//            errorObjects = errorResponses.stream().map(e -> new ErrorObject(e.getName(), e.toString(), ex.getMessage())).collect(Collectors.toList());
//        }finally {
//            ErrorResponse exceptionResponse = new ErrorResponse(new Date(), ex.getCause().getMessage(),  ex.getMessage().split(";")[0], "", errorObjects);
//            return new ResponseEntity<Object>(exceptionResponse, HttpStatus.METHOD_NOT_ALLOWED);
//        }
//    }
}
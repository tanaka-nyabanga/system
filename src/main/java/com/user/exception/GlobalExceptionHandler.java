package com.user.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // missing fields
    @ExceptionHandler(value = {MissingFieldException.class})
    public ResponseEntity<Object> missingFieldExceptionHandler(MissingFieldException e){
        HttpStatus missing = HttpStatus.BAD_REQUEST;

        GlobalException globalException = new GlobalException(e.getMessage(), missing, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(globalException, missing);
    }

    //Dup (during signup)
    @ExceptionHandler(value = {DuplicateExpception.class})
    public  ResponseEntity<Object> duplicateExceptionHandler(DuplicateExpception e){
        HttpStatus duplicate = HttpStatus.CONFLICT;

        GlobalException globalException = new GlobalException( e.getMessage(), duplicate, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(globalException, duplicate);
    }

    //unauthorised (Loggingin)
    @ExceptionHandler(value = {UnauthorizedException.class})
    public  ResponseEntity<Object> unauthorizedExceptionHandler(UnauthorizedException e){
        HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;

        GlobalException globalException = new GlobalException(e.getMessage(), unauthorized, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(globalException, unauthorized);
    }

    //missingNumbersError (Password)
    @ExceptionHandler(value = {PasswordHasnoNumbersException.class})
    public  ResponseEntity<Object> passwordHasnoNumbersExceptionHandler(PasswordHasnoNumbersException e){
        HttpStatus noNumbers = HttpStatus.BAD_REQUEST;

        GlobalException globalException = new GlobalException(e.getMessage(), noNumbers, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(globalException, noNumbers);
    }

    //No Special Xharacters (Password)
    @ExceptionHandler(value = {PasswordHasNoSpecCharException.class})
    public  ResponseEntity<Object> passwordHasNoSpecCharExceptionHandler(PasswordHasNoSpecCharException e){
        HttpStatus noSpecChar = HttpStatus.BAD_REQUEST;

        GlobalException globalException = new GlobalException(e.getMessage(), noSpecChar, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(globalException, noSpecChar);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        final String message =  ex.getBindingResult().getAllErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(". "));
        HttpStatus noSpecChar = HttpStatus.BAD_REQUEST;
        GlobalException globalException = new GlobalException(message, noSpecChar, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(globalException, noSpecChar);
    }

//Not enough characters (Password)
@ExceptionHandler(value = {PasswordHasFewCharException.class})
public ResponseEntity<Object> passwordHasFewCharExceptionHandler(PasswordHasFewCharException e){
    HttpStatus fewChar = HttpStatus.BAD_REQUEST;

    GlobalException globalException = new GlobalException(e.getMessage(), fewChar, ZonedDateTime.now(ZoneId.of("Z")));
    return new ResponseEntity<>(globalException, fewChar);
}

//NotFoundUser (Updating)
@ExceptionHandler(value = {NotFoundUserException.class})
public ResponseEntity<Object> notFoundUserExceptionHandler(NotFoundUserException e){
    HttpStatus notFound = HttpStatus.NOT_FOUND;

    GlobalException globalException = new GlobalException(e.getMessage(), notFound, ZonedDateTime.now(ZoneId.of("Z")));
    return new ResponseEntity<>(globalException, notFound);
}

//PasswordsDontMatch
@ExceptionHandler(value = {PasswordMismatchException.class})
public ResponseEntity<Object> passwordsDontMatchExceptionHandler(PasswordMismatchException e){
    HttpStatus notMatching = HttpStatus.NOT_FOUND;

    GlobalException globalException = new GlobalException(e.getMessage(), notMatching, ZonedDateTime.now(ZoneId.of("Z")));
    return new ResponseEntity<>(globalException,notMatching);
}
}
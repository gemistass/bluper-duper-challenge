package com.blue1.challenge.homehandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HomeNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(HomeNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String homeNotFoundHandler(HomeNotFoundException ex) {
    return ex.getMessage();
  }
}
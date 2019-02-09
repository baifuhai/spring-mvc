package com.test.helloworld.exceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = {ArithmeticException.class})
    public ModelAndView exceptionHandler(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        mv.addObject("exception", e);
        return mv;
    }

    @ExceptionHandler(value = {Exception.class})
    public ModelAndView exceptionHandler2(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        mv.addObject("exception", e);
        return mv;
    }

}

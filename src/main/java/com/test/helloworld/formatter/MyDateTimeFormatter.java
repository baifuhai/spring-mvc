package com.test.helloworld.formatter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
public class MyDateTimeFormatter implements Formatter<Date> {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        System.out.println("MyDateTimeFormatter parse...");
        return sdf.parse(text);
    }

    @Override
    public String print(Date object, Locale locale) {
        System.out.println("MyDateTimeFormatter print...");
        return sdf.format(object);
    }

}

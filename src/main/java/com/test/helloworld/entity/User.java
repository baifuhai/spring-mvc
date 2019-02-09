package com.test.helloworld.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {
    @DateTimeFormat(pattern = "yyyy-MM-HH")
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "date=" + date +
                '}';
    }

}

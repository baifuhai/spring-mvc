package com.test.helloworld.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class Person {
    private int id;

    @NotEmpty
    @Pattern(regexp = "\\w{5,10}")
    private String name;

    @NotNull
    @Email
    private String email;

    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.NONE, style = "SS")
    private Date birth;

    // 有两个互斥的属性 style 和 pattern
    @NumberFormat(pattern = "#,###.0")
    private Float salary;

    private Car car;

    public Person() {
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", salary=" + salary +
                ", car=" + car +
                '}';
    }

}

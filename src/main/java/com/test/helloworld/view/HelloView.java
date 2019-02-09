package com.test.helloworld.view;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class HelloView implements View {

    @Override
    public String getContentType() {
        return "text/html; charset=utf-8";
    }

    @Override
    public void render(Map<String, ?> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().print("hello " + map.get("name"));
    }

}

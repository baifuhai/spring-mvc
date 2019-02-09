package com.test.restcrud.controller;

import com.test.restcrud.entity.Emp;
import com.test.restcrud.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@RequestMapping("emp")
@Controller
public class EmpController {

    @Autowired
    EmpService empService;

    @RequestMapping(value = "emps", method = RequestMethod.GET)
    public String emps(Map<String, Object> map) {
        List<Emp> empList = empService.getList();

        map.put("empList", empList);
        return "emp/emps";
    }
    
    @RequestMapping(value = "emp", method = RequestMethod.GET)
    public String emp(Map<String, Object> map) {
        //Emp emp = new Emp();

        //map.put("emp", emp);
        return "emp/emp";
    }

    @RequestMapping(value = "emp/{id}", method = RequestMethod.GET)
    public String emp(@PathVariable("id") Integer id, Map<String, Object> map) {
        Emp emp = empService.getById(id);

        map.put("emp", emp);
        return "emp/emp";
    }

    @RequestMapping(value = "emp", method = RequestMethod.POST)
    public String empInsert(Emp emp) {
        empService.insert(emp);
        return "redirect:/emp/emps";
    }

    @RequestMapping(value = "emp/{id}", method = RequestMethod.PUT)
    public String empUpdate(@PathVariable("id") Integer id, Emp emp) {
        empService.update(emp);
        return "redirect:/emp/emps";
    }

    @RequestMapping(value = "emp/{id}", method = RequestMethod.DELETE)
    public String empDelete(@PathVariable("id") Integer id) {
        empService.delete(id);
        return "redirect:/emp/emps";
    }

    @ModelAttribute
    public void getEmp(Integer id, Map<String, Object> map) {
        if (id != null) {
            Emp emp = empService.getById(id);
            map.put("emp", emp);
        }
    }

}

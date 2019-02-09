package com.test.restcrud.service;

import com.test.restcrud.entity.Emp;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpService {

    private int maxId;
    private Map<Integer, Emp> empMap;

    public EmpService() {
        maxId = 1;

        List<Emp> empList = new ArrayList<>();
        empList.add(new Emp(maxId++, "a", 10));
        empList.add(new Emp(maxId++, "b", 20));

        empMap = new LinkedHashMap<>();
        for (Emp emp : empList) {
            empMap.put(emp.getId(), emp);
        }
    }

    public void insert(Emp emp) {
        emp.setId(maxId++);
        empMap.put(emp.getId(), emp);
    }

    public void update(Emp emp) {
        empMap.put(emp.getId(), emp);
    }

    public void delete(Integer id) {
        empMap.remove(id);
    }

    public Emp getById(Integer id) {
        return empMap.get(id);
    }

    public List<Emp> getList() {
        return new ArrayList<>(empMap.values());
    }

}

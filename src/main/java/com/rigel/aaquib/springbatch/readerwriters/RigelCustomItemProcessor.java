package com.rigel.aaquib.springbatch.readerwriters;

import com.rigel.aaquib.springbatch.entity.Employee;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RigelCustomItemProcessor implements ItemProcessor<Employee, Employee> {

    private static final Map<String, String> DEPT_NAMES =
            new HashMap<>();

    public RigelCustomItemProcessor() {
        DEPT_NAMES.put("001", "Product");
        DEPT_NAMES.put("002", "HR");
        DEPT_NAMES.put("003", "Services");
    }

    @Override
    public Employee process(Employee user) throws Exception {
        String deptCode = user.getDept();
        String dept = DEPT_NAMES.get(deptCode);
        user.setDept(dept);
        user.setTime(new Date());
        log.info(String.format("Converted from [%s] to [%s]", deptCode, dept));
        return user;
    }
}

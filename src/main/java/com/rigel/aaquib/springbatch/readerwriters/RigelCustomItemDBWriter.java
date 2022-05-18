package com.rigel.aaquib.springbatch.readerwriters;

import com.rigel.aaquib.springbatch.entity.Employee;
import com.rigel.aaquib.springbatch.repository.EmployeeRepository;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RigelCustomItemDBWriter implements ItemWriter<Employee> {

    private EmployeeRepository employeeRepository;

    @Autowired
    public RigelCustomItemDBWriter (EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void write(List<? extends Employee> employee) throws Exception{
       log.info("Employee Data Saved for Users: " + employee);
        employeeRepository.saveAll(employee);
    }
}

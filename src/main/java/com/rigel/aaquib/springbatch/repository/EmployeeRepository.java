package com.rigel.aaquib.springbatch.repository;

import com.rigel.aaquib.springbatch.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}

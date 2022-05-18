package com.rigel.aaquib.springbatch.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rigel.aaquib.springbatch.entity.Employee;
import com.rigel.aaquib.springbatch.repository.EmployeeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RigelAaquibSpringBatchController {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	Job job;

	@GetMapping("/triggerJob")
	public BatchStatus load() throws JobParametersInvalidException,
			JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException {
		JobParameters parameters = generateJobParameters();
		JobExecution jobExecution = jobLauncher.run(job, parameters);
		log.info("JobExecution: " + jobExecution.getStatus());
		while (jobExecution.isRunning()) {
			log.info("job is Running");
		}
		return jobExecution.getStatus();
	}

	private JobParameters generateJobParameters() {
		Map<String, JobParameter> jobParameters = new HashMap<>();
		jobParameters.put("time", new JobParameter(System.currentTimeMillis()));
		JobParameters parameters = new JobParameters(jobParameters);
		return parameters;
	}

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
}

package com.rigel.aaquib.springbatch.configurations;

import com.rigel.aaquib.springbatch.constant.RigelSpringBatchConstant;
import com.rigel.aaquib.springbatch.entity.Employee;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class RigelAaquibBatchConfig {

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory,
			StepBuilderFactory stepBuilderFactory,
			ItemReader<Employee> itemReader,
			ItemProcessor<Employee, Employee> itemProcessor,
			ItemWriter<Employee> itemWriter) {

		Step step = stepBuilderFactory.get(RigelSpringBatchConstant.STEP_NAME)
				.<Employee, Employee> chunk(100).reader(itemReader)
				.processor(itemProcessor).writer(itemWriter).build();

		return jobBuilderFactory.get(RigelSpringBatchConstant.JOB_NAME)
				.incrementer(new RunIdIncrementer()).start(step).build();
	}

	@Bean
	public FlatFileItemReader<Employee> itemReader() {

		FlatFileItemReader<Employee> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(new FileSystemResource(
				RigelSpringBatchConstant.FILE_NAME_WITH_PATH));
		flatFileItemReader.setName(RigelSpringBatchConstant.FILE_READER_NAME);
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
	}

	@Bean
	public LineMapper<Employee> lineMapper() {

		DefaultLineMapper<Employee> defaultLineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

		lineTokenizer.setDelimiter(RigelSpringBatchConstant.DELIMITER_FOR_ITEM_READER);
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(RigelSpringBatchConstant.ID_COLUMN,
				RigelSpringBatchConstant.NAME_COLUMN,
				RigelSpringBatchConstant.DEPT_COLUMN, RigelSpringBatchConstant.CTC_COLUMN);

		BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Employee.class);

		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);

		return defaultLineMapper;
	}

}

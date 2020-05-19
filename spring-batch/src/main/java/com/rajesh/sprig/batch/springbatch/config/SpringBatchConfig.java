package com.rajesh.sprig.batch.springbatch.config;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rajesh.sprig.batch.springbatch.model.Employee;
import com.rajesh.sprig.batch.springbatch.skip.FileVerificationSkipper;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			ItemReader<Employee> itemReader, ItemProcessor<Employee, Employee> itemProcessor,
			ItemWriter<Employee> itemWriter,JobExecutionListener jobExecutionListener,
			ItemReadListener<Employee> itemReadListener,ItemProcessListener<Employee, Employee> itemProcessListener,
			ItemWriteListener<Employee> itemWriteListener) {
		// Step
		Step step = stepBuilderFactory.get("ETL-File-load")
				.<Employee, Employee>chunk(4)
				.reader(itemReader)
				.faultTolerant()
				.skipPolicy(fileVerificationSkipper())
				.processor(itemProcessor)
				.writer(itemWriter)
				.listener(itemReadListener)
				.listener(itemProcessListener)
				.listener(itemWriteListener)
				.taskExecutor(taskExecutor())
				.build();

		// JOb 	
		return jobBuilderFactory.get("ETL-Load")
				.incrementer(new RunIdIncrementer())
				.listener(jobExecutionListener)
				.start(step)
				.build();

	}
//	
//	@Bean
//	public FlatFileItemReader<Employee> fileItemReader(@Value("${input}") Resource resource) {
//		FlatFileItemReader<Employee> flatFileItemReader = new FlatFileItemReader<Employee>();
//		flatFileItemReader.setResource(resource);
//		flatFileItemReader.setName("CSV-Reader");
//		flatFileItemReader.setLinesToSkip(1);
//		flatFileItemReader.setLineMapper(LineMapper());
//
//		return flatFileItemReader;
//
//	}
//
//	private LineMapper<Employee> LineMapper() {
//		DefaultLineMapper<Employee> defaultLineMapper = new DefaultLineMapper<Employee>();
//		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
//		lineTokenizer.setDelimiter(",");
//		lineTokenizer.setStrict(false);
//		//[1,Rajesh ,Spice Garden,Bangalore,560037,India]country
////		private Integer id;
////		private String name;
////		private String streetaddress;
////		private String city;
////		private Integer zip;
////		private String country;
////		
//		lineTokenizer.setNames("id","name","streetaddress","city","zip","country");
//
//		BeanWrapperFieldSetMapper<Employee> filSetMapper = new BeanWrapperFieldSetMapper<Employee>();
//
//		defaultLineMapper.setLineTokenizer(lineTokenizer);
//		defaultLineMapper.setFieldSetMapper(filSetMapper);
//		return defaultLineMapper;
//	}
	@Bean
	public FlatFileItemReader<Employee> fileItemReader(@Value("${input}") Resource resource) {
	    return new FlatFileItemReaderBuilder<Employee>()
	            .name("itemReader")
	            .resource(resource)
	            //.resource(new FileSystemResource("D:\\SpringBoot\\spring-batch\\src\\main\\resources\\Employee.csv"))
	            .targetType(Employee.class)
	            .linesToSkip(1)
	            .delimited()
	            .delimiter(",")
	            .names("id","name","streetaddress","city","zip","country")
	            .build();
	}
	
	@Bean
	public TaskExecutor taskExecutor() {
		SimpleAsyncTaskExecutor asyncTaskExecutor=new SimpleAsyncTaskExecutor();
		asyncTaskExecutor.setConcurrencyLimit(5);
		return asyncTaskExecutor;
	}
	
	@Bean
	public SkipPolicy fileVerificationSkipper() {
		return new FileVerificationSkipper();
	}

}

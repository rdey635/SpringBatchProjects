package com.rajesh.sprig.batch.springbatch.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rajesh.sprig.batch.springbatch.model.Employee;
import com.rajesh.sprig.batch.springbatch.resource.EmployeeRepositry;

@Component
public class DbWriter implements ItemWriter<Employee> {
	
	@Autowired
	private EmployeeRepositry employeeRepo ;

	@Override
	public void write(List<? extends Employee> items) throws Exception {
		
		employeeRepo.saveAll(items);
	}
	
	

}

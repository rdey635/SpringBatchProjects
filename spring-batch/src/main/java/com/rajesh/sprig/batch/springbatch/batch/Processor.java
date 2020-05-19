package com.rajesh.sprig.batch.springbatch.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.rajesh.sprig.batch.springbatch.model.Employee;

@Component
public class Processor implements ItemProcessor<Employee, Employee> {

	@Override
	public Employee process(Employee employee) throws Exception {
		
		int zipCode=employee.getZip();
		employee.setZip(zipCode+100);
		return employee;

	}

}

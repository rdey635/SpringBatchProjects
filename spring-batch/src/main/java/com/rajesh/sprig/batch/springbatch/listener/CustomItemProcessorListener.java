package com.rajesh.sprig.batch.springbatch.listener;

import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

import com.rajesh.sprig.batch.springbatch.model.Employee;
@Component
public class CustomItemProcessorListener  implements ItemProcessListener<Employee, Employee> {

	@Override
	public void beforeProcess(Employee item) {
		System.out.println("Before Process");
		
	}

	@Override
	public void afterProcess(Employee item, Employee result) {
		System.out.println("After Process");
	}

	@Override
	public void onProcessError(Employee item, Exception e) {
		System.out.println("On error procesor" + e.getMessage());
	}

}

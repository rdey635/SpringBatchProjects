package com.rajesh.sprig.batch.springbatch.listener;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

import com.rajesh.sprig.batch.springbatch.model.Employee;
@Component
public class CustomItemReaderListener implements ItemReadListener<Employee> {

	@Override
	public void beforeRead() {
		System.out.println("ItemReadListener - beforeRead");
		
	}

	@Override
	public void afterRead(Employee item) {
		System.out.println("ItemReadListener - afterRead");
		
	}

	@Override
	public void onReadError(Exception ex) {
		System.out.println("ItemReadListener - onReadError");
		
	}

}

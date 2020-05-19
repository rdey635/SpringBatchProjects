package com.rajesh.sprig.batch.springbatch.listener;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import com.rajesh.sprig.batch.springbatch.model.Employee;
@Component
public class CustomItemWriterListener implements ItemWriteListener<Employee> {

	@Override
	public void beforeWrite(List<? extends Employee> items) {
		System.out.println("ItemWriteListener - beforeWrite");
		
	}

	@Override
	public void afterWrite(List<? extends Employee> items) {
		System.out.println("ItemWriteListener - afterWrite");
		
	}

	@Override
	public void onWriteError(Exception exception, List<? extends Employee> items) {
		System.out.println("ItemWriteListener - onWriteError");
		
	}

}

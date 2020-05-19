package com.rajesh.sprig.batch.springbatch.resource;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajesh.sprig.batch.springbatch.model.Employee;

public interface EmployeeRepositry extends JpaRepository<Employee, Integer> {

}

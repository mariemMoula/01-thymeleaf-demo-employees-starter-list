package com.luv2code.springboot.thymeleafdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// that's it ... no need to write any code LOL!
	// add a method to sort by last name
    // spring data jpa will parse the method name and patterns , then creates the appropriate query
    public List<Employee> findAllByOrderByLastNameAsc();
    // findallbyorderby***asc
}

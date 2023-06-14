package com.reactapi.EmpRestApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reactapi.EmpRestApi.Model.Employee;


public interface EmpRepository extends JpaRepository<Employee, Long> 
{
	//all crude database opreation
	

}

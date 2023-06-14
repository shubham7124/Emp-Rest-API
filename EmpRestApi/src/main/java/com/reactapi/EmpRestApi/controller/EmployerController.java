package com.reactapi.EmpRestApi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResponseExtractor;

import com.reactapi.EmpRestApi.Model.Employee;
import com.reactapi.EmpRestApi.exception.ResourceNotfound;
import com.reactapi.EmpRestApi.repository.EmpRepository;

@CrossOrigin("*")
@RestController()
public class EmployerController {

	@Autowired
	private EmpRepository emp;
	
	@GetMapping("/")
	public List<Employee> getEmployee()
	{
		return emp.findAll();
	}
	
	@PostMapping("/create")
	public Employee createEmp(@RequestBody Employee employee) {
	
		return emp.save(employee);
	}
	
//	get emp by id
	@GetMapping("/find/{id}")
	public ResponseEntity<Employee> getEmpById(@PathVariable long id){
		Employee employee= emp.findById(id).orElseThrow(() ->new ResourceNotfound("Employee not found "+id) );
		return ResponseEntity.ok(employee);
		
	}
	
//	Updata emp
	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> updatEmp(@PathVariable long id,@RequestBody Employee empDatails)
	{
		Employee updatemp=emp.findById(id).orElseThrow(() ->new ResourceNotfound("Employee not found "+id));
		
		updatemp.setFirstname(empDatails.getFirstname());
		updatemp.setLastname(empDatails.getLastname());
		updatemp.setEmailId(empDatails.getEmailId());
	
		emp.save(updatemp);
		return ResponseEntity.ok(updatemp);
		
	}
	
//Delete
	@DeleteMapping("/Delete/{id}")
	public ResponseEntity<Employee> deleteEmp(@PathVariable long id)
	{
		Employee deleteEmp=emp.findById(id).orElseThrow(() -> new ResourceNotfound("Employee not found"));
		
		emp.delete(deleteEmp);
		return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

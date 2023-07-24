package com.example.EmployeeDetails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.EmployeeDetails.constants.RequestPath;
import com.example.EmployeeDetails.dto.ApiResponse;
import com.example.EmployeeDetails.dto.EmployeeDto;
import com.example.EmployeeDetails.enums.BloodGroup;
import com.example.EmployeeDetails.service.IEmployeeService;

@RestController
@RequestMapping(RequestPath.VERSION + RequestPath.EMPLOYEE_DETAILS)
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@PostMapping
	public ApiResponse saveEmployee(@RequestBody EmployeeDto employeeDto) {
		return employeeService.createEmployee(employeeDto);
	}

	@PutMapping("{id}")
	public ApiResponse updateEmployee(@PathVariable int id, @RequestBody EmployeeDto employeeDto) {
		return employeeService.updateEmployee(id, employeeDto);
	}

	@GetMapping("{id}")
	public ApiResponse getEmployeeById(@PathVariable int id) {
		return employeeService.getEmployeeById(id);
	}

	@GetMapping
	public ApiResponse getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@DeleteMapping("{id}")
	public ApiResponse deleteEmployee(@PathVariable int id) {
		return employeeService.deleteEmployee(id);

	}

	@GetMapping("bloodGroup")
	public ApiResponse findEmployeeByBloodGroup(@RequestParam("bloodGroup") BloodGroup bloodGroup) {
		return employeeService.getEmployeeByBloodgroup(bloodGroup);

	}

	@GetMapping("salary_status")
	public ApiResponse findUnderPaidAndOverPaidEmployeeCount() {
		return employeeService.identifyOverpaidAndUnderpaid();
	}

	@PatchMapping("update-phoneNumber/{id}/{phoneNumber}")
	public ApiResponse updatePhoneNumber(@PathVariable("id") int id,
										@PathVariable("phoneNumber") String phoneNumber)
	{
		return employeeService.updatePhoneNumber(id, phoneNumber);
	}
}

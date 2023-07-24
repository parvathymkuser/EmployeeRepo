package com.example.EmployeeDetails.service;

import com.example.EmployeeDetails.dto.ApiResponse;
import com.example.EmployeeDetails.dto.EmployeeDto;
import com.example.EmployeeDetails.enums.BloodGroup;

public interface IEmployeeService {

	ApiResponse createEmployee(EmployeeDto employeedto);

	ApiResponse updateEmployee(int id, EmployeeDto employeedto);

	ApiResponse deleteEmployee(int id);

	ApiResponse getAllEmployees();

	ApiResponse getEmployeeById(int id);

	ApiResponse getEmployeeByBloodgroup(BloodGroup bloodGroup);

	ApiResponse identifyOverpaidAndUnderpaid();
	
	ApiResponse updatePhoneNumber(int id, String phoneNumber);

}

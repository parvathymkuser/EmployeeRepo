package com.example.EmployeeDetails.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.EmployeeDetails.constants.ResponseMessages;
import com.example.EmployeeDetails.dto.ApiResponse;
import com.example.EmployeeDetails.dto.EmployeeDto;
import com.example.EmployeeDetails.enums.BloodGroup;
import com.example.EmployeeDetails.model.Employee;
import com.example.EmployeeDetails.repo.IEmployeeRepo;
import com.example.EmployeeDetails.service.IEmployeeService;
import com.example.EmployeeDetails.util.BeanConverter;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	
	
	@Autowired
	private IEmployeeRepo employeeRepo;

	@Override
	public ApiResponse createEmployee(EmployeeDto employeedto) {
		try {
			employeeRepo.save(BeanConverter.toEmployee(employeedto));
			
			return new ApiResponse(true, HttpStatus.CREATED.value(), 
					ResponseMessages.CREATE_SUCCESS, true);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResponse(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ResponseMessages.SOMETHING_WENT_WRONG, false);
		}
	}

	@Override
	public ApiResponse updateEmployee(int id, EmployeeDto employeedto) {
		try {
			Optional<Employee> employeeInfo = employeeRepo.findById(id);
			if (employeeInfo.isEmpty()) {
				return new ApiResponse(false, HttpStatus.NOT_FOUND.value(),
						ResponseMessages.ID_NOT_FOUND, false);
			}
			BeanConverter.copyToEntity(employeedto, employeeInfo.get());
			employeeRepo.save(employeeInfo.get());
			return new ApiResponse(true, HttpStatus.OK.value(),
					ResponseMessages.UPDATE_SUCCESS, true);
		} catch (BeansException e) {
			e.printStackTrace();
			return new ApiResponse(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ResponseMessages.SOMETHING_WENT_WRONG, false);
		}
	}

	public ApiResponse getEmployeeById(int id) {
		try {
			Optional<Employee> employeeInfo = employeeRepo.findById(id);
			if (employeeInfo.isPresent()) {
				return new ApiResponse(true, HttpStatus.FOUND.value(),
						ResponseMessages.DATA_LOAD_SUCCESS,
						BeanConverter.toEmployeeDto(employeeInfo.get()));
			} else {
				return new ApiResponse(false, HttpStatus.NOT_FOUND.value(),
						ResponseMessages.ID_NOT_FOUND, false);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResponse(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ResponseMessages.SOMETHING_WENT_WRONG, false);
		}
	}

	@Override
	public ApiResponse deleteEmployee(int id) {
		try {
			employeeRepo.deleteById(id);
			return new ApiResponse(true, HttpStatus.OK.value(), 
					ResponseMessages.DELETE_SUCCESS, true);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return new ApiResponse(false, HttpStatus.NO_CONTENT.value(),
					ResponseMessages.ID_NOT_FOUND, false);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResponse(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ResponseMessages.SOMETHING_WENT_WRONG, false);
		}
	}

	@Override
	public ApiResponse getAllEmployees() {
		try {
			List<Employee> employeeList = employeeRepo.findAll();
			return new ApiResponse(true, HttpStatus.FOUND.value(), 
					ResponseMessages.DATA_LOAD_SUCCESS, BeanConverter.toEmployeeDto(employeeList));
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResponse(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ResponseMessages.SOMETHING_WENT_WRONG, false);
		}
	}

	@Override
	public ApiResponse getEmployeeByBloodgroup(BloodGroup bloodGroup) {
		try {
			List<Employee> employeeInfo = employeeRepo.findEmployeeByBloodGroup(bloodGroup);
			if (!employeeInfo.isEmpty()) {
				return new ApiResponse(true, HttpStatus.FOUND.value(), 
						ResponseMessages.DATA_LOAD_SUCCESS,
						BeanConverter.toEmployeeDto(employeeInfo));
			} else {
				return new ApiResponse(false, HttpStatus.NOT_FOUND.value(), 
						ResponseMessages.NO_DATA_FOUND, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResponse(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ResponseMessages.SOMETHING_WENT_WRONG, false);
		}
	}

	@Override
	public ApiResponse identifyOverpaidAndUnderpaid() {

		try {
			
			List<Employee> employees = employeeRepo.findAll();
			int overpaidCount = 0;
			int underpaidCount = 0;

			for (Employee employee : employees) {

				float ctcPerYear = employee.getAnnualCTC() / (employee.getYearsOfExperience());

				if (ctcPerYear > 1) {
					overpaidCount++;
				} else {
					underpaidCount++;
				}
			}
			Map<String, Integer> dataMap = new HashMap<>();

			dataMap.put("OverpaidEmployees", overpaidCount);
			dataMap.put("underpaidEmployees", underpaidCount);
			return new ApiResponse(true, HttpStatus.OK.value(), 
					ResponseMessages.DATA_LOAD_SUCCESS, dataMap);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new ApiResponse(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ResponseMessages.SOMETHING_WENT_WRONG, false);
		}
	}

	@Override
	public ApiResponse updatePhoneNumber(int id, String phoneNumber) {
		try {

			int updateValue = employeeRepo.updatePhoneNumber(phoneNumber, id);
			if (updateValue == 1) {
				return new ApiResponse(true, HttpStatus.OK.value(), 
						ResponseMessages.UPDATE_SUCCESS, true);
			} else {
				return new ApiResponse(true, HttpStatus.NOT_MODIFIED.value(),
						ResponseMessages.UPDATE_FAILED,
						false);
			}

		} catch (Exception e) {
			return new ApiResponse(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ResponseMessages.SOMETHING_WENT_WRONG, false);
		}
	}

}

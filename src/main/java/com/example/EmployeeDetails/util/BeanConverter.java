package com.example.EmployeeDetails.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import com.example.EmployeeDetails.dto.EmployeeDto;
import com.example.EmployeeDetails.model.Employee;

public class BeanConverter {

	public static void copyToEntity(Object source, Object target) {
		if (source != null && target != null) {
			BeanUtils.copyProperties(source, target,"id");
		}
	}

	public static Employee toEmployee(EmployeeDto source) {

		if (ObjectUtils.isEmpty(source)) {
			return null;
		}
		Employee target = new Employee();
		BeanUtils.copyProperties(source, target);
		return target;
	}

	public static EmployeeDto toEmployeeDto(Employee source) {

		if (ObjectUtils.isEmpty(source)) {
			return null;
		}

		EmployeeDto target = new EmployeeDto();
		BeanUtils.copyProperties(source, target);

		return target;
	}

	public static List<EmployeeDto> toEmployeeDto(List<Employee> source) {

		if (source.isEmpty()) {
			return Collections.emptyList();
		}
		return source.stream().map(BeanConverter::toEmployeeDto).collect(Collectors.toList());

	}
}

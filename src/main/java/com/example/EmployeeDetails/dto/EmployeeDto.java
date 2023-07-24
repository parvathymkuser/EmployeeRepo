package com.example.EmployeeDetails.dto;

import com.example.EmployeeDetails.enums.BloodGroup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {

	private int id;

	private String name;

	private String address;

	private String designation;

	private String contactNumber;

	private String email;

	private float annualCTC;

	private float yearsOfExperience;

	private BloodGroup bloodGroup;

}

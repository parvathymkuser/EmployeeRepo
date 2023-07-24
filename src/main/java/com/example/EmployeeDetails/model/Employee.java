package com.example.EmployeeDetails.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.example.EmployeeDetails.enums.BloodGroup;

import lombok.Data;

@Entity
@Table(name = "employee_details")
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	@NotBlank(message = "name required")
	private String name;

	@Column(name = "address", length = 50)
	private String address;

	@Column(name = "designation", length = 50)
	private String designation;

	@Column(name = "contact_number", length = 15)
	private String contactNumber;

	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "annual_ctc", length = 10)
	private float annualCTC;

	@Column(name = "years_of_experience")
	private float yearsOfExperience;

	@Column(name = "blood_group", length = 15)
	@Enumerated(EnumType.STRING)
	private BloodGroup bloodGroup;

}

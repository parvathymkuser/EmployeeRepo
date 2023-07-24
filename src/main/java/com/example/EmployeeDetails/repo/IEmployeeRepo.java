package com.example.EmployeeDetails.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.EmployeeDetails.enums.BloodGroup;
import com.example.EmployeeDetails.model.Employee;

@Repository
public interface IEmployeeRepo extends JpaRepository<Employee, Integer> {

	public List<Employee> findEmployeeByBloodGroup(BloodGroup bloodGroup);
	
	@Modifying
	@Transactional
	@Query(value = "update Employee spp set spp.contactNumber=:contactNumber where spp.id=:id")
	int updatePhoneNumber(@Param("contactNumber") String contactNumber, @Param("id") int id);
}

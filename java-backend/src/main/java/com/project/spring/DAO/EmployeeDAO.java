package com.project.spring.DAO;

import com.project.spring.DomainObjects.Employee;
import com.project.spring.Enums.Sex;
import com.project.spring.Services.EmployeeLookupResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee,Long> {
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
}

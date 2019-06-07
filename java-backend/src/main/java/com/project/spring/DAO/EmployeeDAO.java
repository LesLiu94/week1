package com.project.spring.DAO;

import com.project.spring.DomainObjects.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee,Long> {
    Employee findByFirstNameAndLastNameAndBirthDate(String firstName, String lastName, Date birthDate);
}

package com.project.spring.DAO;

import com.project.spring.DomainObjects.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee,Long> {
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
    Employee findByEmpNo(Integer empNo);
}

package com.project.spring.DAO;

import com.project.spring.DTO.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryDAO extends JpaRepository<Salary,Long> {
}

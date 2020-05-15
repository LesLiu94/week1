package com.project.spring.DAO;

import com.project.spring.DomainObjects.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryDAO extends JpaRepository<Salary,Long> {

    Salary findByEmpNoAndActive(Integer empNo, Boolean active);

}

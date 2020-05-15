package com.project.spring.DAO;

import com.project.spring.DomainObjects.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDAO extends JpaRepository<Department,Long> {
    Department findByDeptName(String deptName);
}

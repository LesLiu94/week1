package com.project.spring.DAO;

import com.project.spring.DomainObjects.DepartmentEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentEmployeeDAO extends JpaRepository<DepartmentEmployee,Long> {
    DepartmentEmployee findByEmpNo(Integer empNo);
}

package com.project.spring.DAO;

import com.project.spring.DomainObjects.DepartmentManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentManagerDAO extends JpaRepository<DepartmentManager,Long> {
}

package com.project.spring.DAO;

import com.project.spring.DomainObjects.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleDAO extends JpaRepository<Title, Long> {
    Title findByEmpNo(Integer empNo);
}

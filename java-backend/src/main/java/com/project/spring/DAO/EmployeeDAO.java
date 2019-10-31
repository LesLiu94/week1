package com.project.spring.DAO;

import com.project.spring.DomainObjects.Employee;
import com.project.spring.Enums.Sex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee,Long> {
    Employee findByFirstNameAndLastNameAndBirthDate(String firstName, String lastName, Date birthDate);
    Employee findByFirstNameAndLastName(String firstName, String lastName);

    @Query(
            value = "insert into employeesschema.employees " +
                    "(birth_date, first_name, last_name, gender, hire_date) " +
                    "values(:birth_date, :first_name, :last_name, CAST(:gender as SEX), :hire_date)",
            nativeQuery = true
    )
    Employee saveEmployee(@Param(value="birth_date") Date birth_date,
                          @Param(value="first_name") String first_name,
                          @Param(value="last_name") String last_name,
                          @Param(value="gender") String gender,
                          @Param(value="hire_date") Date hire_date);
}

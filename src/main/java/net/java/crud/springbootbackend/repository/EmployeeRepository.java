package net.java.crud.springbootbackend.repository;

import net.java.crud.springbootbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // all CRUD matters to interact with database

}

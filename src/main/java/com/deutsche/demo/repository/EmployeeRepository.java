package com.deutsche.demo.repository;

import java.util.List;
import com.deutsche.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByNameIgnoreCase(String name);
}
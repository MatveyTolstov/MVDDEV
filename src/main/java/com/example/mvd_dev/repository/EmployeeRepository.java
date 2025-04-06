package com.example.mvd_dev.repository;

import com.example.mvd_dev.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с сущностью Employee
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    @Query(value = "SELECT * FROM employees WHERE user_id = :userId", nativeQuery = true)
    Optional<Employee> findByUserId(Long userId);


    @Query(value = "SELECT * FROM employees WHERE department_id = :departmentId", nativeQuery = true)
    List<Employee> findByDepartmentId(Long departmentId);


    @Query(value = "SELECT * FROM employees WHERE role_id = :roleId", nativeQuery = true)
    List<Employee> findByRoleId(Long roleId);
}
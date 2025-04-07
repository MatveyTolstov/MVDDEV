package com.example.mvd_dev.mapper;

import com.example.mvd_dev.model.Employee;
import com.example.mvd_dev.modeldto.EmployeeDto;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeDto employeeDto) {
        if (employeeDto == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setSurname(employeeDto.getSurname());
        return employee;
    }

    public EmployeeDto toDto(Employee employee) {
        if (employee == null) {
            return null;
        }
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(employee.getName());
        employeeDto.setSurname(employee.getSurname());
        return employeeDto;
    }
}
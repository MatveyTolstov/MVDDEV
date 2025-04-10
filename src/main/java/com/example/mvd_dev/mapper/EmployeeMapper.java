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
        employee.setIdEmployee(employeeDto.getIdEmployee());
        employee.setName(employeeDto.getName());
        employee.setSurname(employeeDto.getSurname());
        employee.setMidname(employeeDto.getMidname());

        return employee;
    }

    public EmployeeDto toDto(Employee employee) {
        if (employee == null) {
            return null;
        }
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setIdEmployee(employee.getIdEmployee());
        employeeDto.setDepartmentId(employee.getDepartment() != null ? employee.getDepartment().getIdDepartment() : null);
        employeeDto.setUserId(employee.getUser () != null ? employee.getUser ().getIdUser () : null);
        employeeDto.setName(employee.getName());
        employeeDto.setSurname(employee.getSurname());
        employeeDto.setMidname(employee.getMidname());
        return employeeDto;
    }
}
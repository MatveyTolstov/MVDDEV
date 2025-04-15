package com.example.mvd_dev.mapper;

import com.example.mvd_dev.model.Department;
import com.example.mvd_dev.modeldto.DepartmentDto;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public Department toEntity(DepartmentDto departmentDto) {
        if (departmentDto == null) {
            return null;
        }
        Department department = new Department();
        department.setIdDepartment(departmentDto.getIdDepartment());
        department.setName(departmentDto.getName());
        department.setAddress(departmentDto.getAddress());
        return department;
    }

    public DepartmentDto toDto(Department department) {
        if (department == null) {
            return null;
        }
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setIdDepartment(department.getIdDepartment());
        departmentDto.setName(department.getName());
        departmentDto.setAddress(department.getAddress());
        return departmentDto;
    }
}
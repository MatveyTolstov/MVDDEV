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
        department.setName(departmentDto.getName());
        return department;
    }

    public DepartmentDto toDto(Department department) {
        if (department == null) {
            return null;
        }
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName(department.getName());
        return departmentDto;
    }
}
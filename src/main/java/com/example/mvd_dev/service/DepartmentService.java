package com.example.mvd_dev.service;

import com.example.mvd_dev.mapper.DepartmentMapper;
import com.example.mvd_dev.model.Department;
import com.example.mvd_dev.modeldto.DepartmentDto;
import com.example.mvd_dev.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mvd_dev.service.ErrorMessages.DEPARTMENT_NOT_FOUND;

@Service
@AllArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentDto save(DepartmentDto departmentDto) {
        Department department = departmentMapper.toEntity(departmentDto);
        department = departmentRepository.save(department);
        return departmentMapper.toDto(department);
    }

    public DepartmentDto findById(Long id) {
        return departmentRepository.findById(id)
                .map(departmentMapper::toDto)
                .orElseThrow(() -> new RuntimeException(DEPARTMENT_NOT_FOUND));
    }

    public List<DepartmentDto> findAll() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toDto)
                .toList();
    }

    public DepartmentDto update(Long id, DepartmentDto departmentDto) {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(DEPARTMENT_NOT_FOUND));

        existingDepartment.setName(departmentDto.getName());
        existingDepartment.setAddress(departmentDto.getAddress());

        return departmentMapper.toDto(departmentRepository.save(existingDepartment));
    }

    public void delete(Long id) {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(DEPARTMENT_NOT_FOUND));
        departmentRepository.delete(existingDepartment);
    }
}
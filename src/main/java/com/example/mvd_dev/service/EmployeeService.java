package com.example.mvd_dev.service;

import com.example.mvd_dev.mapper.EmployeeMapper;
import com.example.mvd_dev.model.Employee;
import com.example.mvd_dev.modeldto.EmployeeDto;
import com.example.mvd_dev.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.mvd_dev.service.ErrorMessages.EMPLOYEE_NOT_FOUND;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeDto save(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        employee = employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    public EmployeeDto findById(Long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toDto)
                .orElseThrow(() -> new RuntimeException(EMPLOYEE_NOT_FOUND));
    }

    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDto)
                .toList();
    }

    public EmployeeDto update(Long id, EmployeeDto employeeDto) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(EMPLOYEE_NOT_FOUND));

        existingEmployee.setName(employeeDto.getName());
        existingEmployee.setSurname(employeeDto.getSurname());
        return employeeMapper.toDto(employeeRepository.save(existingEmployee));
    }

    public void delete(Long id) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(EMPLOYEE_NOT_FOUND));
        employeeRepository.delete(existingEmployee);
    }

    public List<EmployeeDto> findByDepartmentId(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId).stream()
                .map(employeeMapper::toDto)
                .toList();
    }

    public Optional<EmployeeDto> findByUserId(Long userId) {
        return employeeRepository.findByUserId(userId)
                .map(employeeMapper::toDto);
    }
}
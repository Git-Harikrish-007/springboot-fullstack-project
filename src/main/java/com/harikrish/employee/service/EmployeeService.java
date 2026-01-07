package com.harikrish.employee.service;

import com.harikrish.employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    public EmployeeDto createEmployee(EmployeeDto employeeDto);

    public EmployeeDto getEmployeeById(Long id);

    public List<EmployeeDto> getAllEmployees();

    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee);

    public void deleteEmployee(Long employeeId);
}

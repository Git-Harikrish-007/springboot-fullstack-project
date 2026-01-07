package com.harikrish.employee.service.impl;

import com.harikrish.employee.dto.EmployeeDto;
import com.harikrish.employee.entity.Employee;
import com.harikrish.employee.exception.ResourceNotFoundException;
import com.harikrish.employee.repository.EmployeeRepository;
import com.harikrish.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository repository;

    private ModelMapper mapper;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = mapper.map(employeeDto, Employee.class);
        Employee savedEmployee = repository.save(employee);
        return mapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Id is not exists for given id:" + id));

        return mapper.map(employee, EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = repository.findAll();
        return employees.stream()
                .map(employee -> mapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {

        Employee employee = repository
                .findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Id is not exists for given id:" + employeeId));

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObj = repository.save(employee);

        return mapper.map(updatedEmployeeObj, EmployeeDto.class);

    }

    @Override
    public void deleteEmployee(Long employeeId) {

        Employee employee = repository
                .findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Id is not exists for given id:" + employeeId));

        repository.deleteById(employeeId);

    }
}

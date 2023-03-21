package com.example.tien.Final.service;

import com.example.tien.Final.Dto.EmployeeDto;
import com.example.tien.Final.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> getEmployee();
    Employee saveEmployee(EmployeeDto userDto);
	Double getEmployeePayroll(Long id);
}

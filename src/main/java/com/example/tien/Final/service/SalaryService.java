package com.example.tien.Final.service;

import com.example.tien.Final.Dto.EmployeeDto;
import com.example.tien.Final.Dto.SalaryDto;
import com.example.tien.Final.entity.Employee;
import com.example.tien.Final.entity.Salary;

import java.util.List;

public interface SalaryService {
    List<SalaryDto> getSalary();
    Salary save(SalaryDto salaryDto);
}

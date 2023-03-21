package com.example.tien.Final.service;

import com.example.tien.Final.Dto.EmployeeDto;
import com.example.tien.Final.entity.Employee;

import java.io.IOException;
import java.util.List;

public interface FileService {
    List<Employee> getAllEngineers() throws IOException;

    void save(Employee employee);



    List<Employee> readEngineersFromFile();
}

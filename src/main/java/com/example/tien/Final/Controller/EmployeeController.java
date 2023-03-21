package com.example.tien.Final.Controller;

import com.example.tien.Final.Dto.EmployeeDto;
import com.example.tien.Final.entity.Employee;
import com.example.tien.Final.service.EmployeeService;
import com.example.tien.Final.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
	
    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("/Employees")
    private List<EmployeeDto> findAllEmployee(){
        return employeeService.getEmployee();
    }
    
    @PostMapping("/addEmployee")
    private Employee addEmployee(@RequestBody EmployeeDto employeeDto ){
        return employeeService.saveEmployee(employeeDto);
    }
    
    ///// Tính Lương
    @GetMapping("/Employees/Payroll/{id}")
    private Double payroll(@PathVariable Long id){
        return employeeService.getEmployeePayroll(id);
    }
}

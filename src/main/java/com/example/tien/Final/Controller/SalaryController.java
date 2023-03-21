package com.example.tien.Final.Controller;

import com.example.tien.Final.Dto.SalaryDto;
import com.example.tien.Final.entity.Salary;
import com.example.tien.Final.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SalaryController {
    @Autowired
    private SalaryService salaryService;
    @GetMapping("/Salaries")
    private List<SalaryDto> getAllSalary(){
        return salaryService.getSalary();
    }
    @PostMapping("/addSalaries")
    private Salary addEmployee(@RequestBody SalaryDto salaryDto){
        return salaryService.save(salaryDto);
    }


}

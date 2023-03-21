package com.example.tien.Final.service.impl;

import com.example.tien.Final.Dto.SalaryDto;
import com.example.tien.Final.entity.Position;
import com.example.tien.Final.entity.Salary;
import com.example.tien.Final.repos.EmployeeRepository;
import com.example.tien.Final.repos.PositionRepository;
import com.example.tien.Final.repos.SalaryRepository;
import com.example.tien.Final.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private PositionServiceImpl positionService;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<SalaryDto> getSalary(){
        List<Salary> salaries=salaryRepository.findAll();
        List<SalaryDto> salaryDtos=new ArrayList<>();
        for (Salary salary : salaries){
            SalaryDto salaryDto = SalaryDto.builder()
                    .id(salary.getId())
                    .baseSalary(salary.getBaseSalary())
                    .daysWorked(salary.getDaysWorked())
                    .positionId(salary.getPosition().getId())
                    .overtimeSalary(salary.getOvertimeSalary())
                    .build();
            salaryDtos.add(salaryDto);
        }
        return salaryDtos;
    }
    @Override
    public Salary save(SalaryDto salaryDto){
        Position position = positionService.getPositionById(salaryDto.getPositionId());

        Salary salary = Salary.builder()
                .baseSalary(salaryDto.getBaseSalary())
                .daysWorked(salaryDto.getDaysWorked())
                .overtimeSalary(salaryDto.getOvertimeSalary())
                .position(position)
                .build();
        return salaryRepository.save(salary);
    }
//    public Salary getSalaryById(Long id){
//        return salaryRepository.findById(id).orElseThrow(()-> new RuntimeException("Error"));
//    }
    public BigDecimal calculateSalaries(Long salaryId, int daysWorked){
        Salary salary = salaryRepository.findByEmployeeId(salaryId);
        Position position = positionRepository.findById(salary.getPosition().getId()).orElse(null);
        BigDecimal salaryPerDay = BigDecimal.ZERO;
        if(position != null){
            switch (position.getName()){
                case "Cleaner":
                    salaryPerDay = new BigDecimal("300000");
                    break;
                case "Manager":
                    salaryPerDay = new BigDecimal("1500000");
            }
        }
        BigDecimal baseSalary = salary.getBaseSalary().add(salaryPerDay.multiply(new BigDecimal(daysWorked)));
        BigDecimal overtimeSalary = salary.getOvertimeSalary().add(new BigDecimal("300000"));
        BigDecimal totalSalary =  baseSalary.add(overtimeSalary);
        return totalSalary;

    }
//    public List<SalaryDto> calculateSalaries(){
//        List<Employee> employees = employeeRepository.findAll();
//        List<SalaryDto> salaryDtos = new ArrayList<>();
//        for (Employee employee : employees){
//
//        }
//    }




























//    @Override
//    public double calculateSalary(EmployeeDto employee){
//        double baseSalary = employee.g  etSalaryDto().getBasicSalary();
//        double totalSalary = baseSalary * employee.getDaysWorked();
//        if (employee.getDaysWorked() > 22){
//            totalSalary += baseSalary * 0.3;
//        }
//        if(employee.getSalaryDto().getOvertimeSalary()){
//            totalSalary += 300000;
//        }
//        return totalSalary;
//    }
}

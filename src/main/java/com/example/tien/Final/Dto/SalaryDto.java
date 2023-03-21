package com.example.tien.Final.Dto;

import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDto {
    private Long id;
    private BigDecimal baseSalary;
    private int daysWorked;
    private BigDecimal overtimeSalary;
    private Long positionId;
}

package com.example.tien.Final.Dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private String username;
    private String password;
    private String name;

    private Long positionId;
    private Long salaryId;
}

package com.example.tien.Final.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "position_tbl")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    //    @Column(name = "pay")
//    private long pay;
    @OneToMany(mappedBy = "position")
    private List<Employee> employees;

}

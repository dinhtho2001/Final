package com.example.tien.Final.repos;

import com.example.tien.Final.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {


    Position findAllByName(String name);
}

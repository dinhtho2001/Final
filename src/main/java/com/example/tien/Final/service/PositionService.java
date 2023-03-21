package com.example.tien.Final.service;

import com.example.tien.Final.Dto.PositionDto;
import com.example.tien.Final.entity.Position;

import java.util.List;

public interface PositionService {

    List<PositionDto> getPosition();

    Position savePosition(PositionDto positionDto);
}

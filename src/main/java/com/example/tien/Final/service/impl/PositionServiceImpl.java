package com.example.tien.Final.service.impl;

import com.example.tien.Final.Dto.PositionDto;
import com.example.tien.Final.entity.Position;
import com.example.tien.Final.repos.PositionRepository;

import com.example.tien.Final.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionRepository positionRepository;
//    @Autowired
//    private UserRepository userRepository;
//    public List<Position> getAllPosition() {
//        return positionRepository.findAll();
//    }
    @Override
    public List<PositionDto> getPosition(){
        List<Position> positions=positionRepository.findAll();
        List<PositionDto> positionDtos=new ArrayList<>();
        for(Position position:positions){
            PositionDto positionDto=PositionDto.builder()
                    .id(position.getId())
                    .name(position.getName())
//                    .pay(position.getPay())
                    .build();
            positionDtos.add(positionDto);
        }
        return positionDtos;
    }
    public Position getPositionById(Long id){
        return positionRepository.findById(id).orElseThrow(()-> new RuntimeException("Error"));
    }
    public Position getPositionByName(String name){
        return positionRepository.findAllByName(name);
    }
    @Override
    public Position savePosition(PositionDto positionDto){
        Position pos = Position.builder()
                .name(positionDto.getName())
//                .pay(positionDto.getPay())
                .build();

        return positionRepository.save(pos);
    }


    public List<Position> savePositions(List<Position> positions){
        return positionRepository.saveAll(positions);
    }
    public String deletePosition(Long id){
        positionRepository.deleteById(id);
        return "Position removed !!" + id;
    }
//    public Position updatePosition(Position position){
//        Position existPosition = positionRepository.findById(position.getId()).orElse(null);
//        existPosition.setName(position.getName());
//        existPosition.setPay(position.getPay());
////        existPosition.setUser(position.getUser());
//        return positionRepository.save(existPosition);
//    }
    public PositionDto updatePosition(PositionDto positionDto){
        Position position = positionRepository.findById(positionDto.getId()).orElseThrow(() -> new RuntimeException("Position not found"));
        position.setName(positionDto.getName());
//        position.setPay(positionDto.getPay());
        positionRepository.save(position);
        return positionDto;

    }

//    public void deleteAllPosition(){
//        positionRepository.deleteAll();
//    }





}

package com.example.tien.Final.Controller;

import com.example.tien.Final.Dto.PositionDto;
import com.example.tien.Final.entity.Position;
import com.example.tien.Final.service.impl.PositionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PositionController {
    @Autowired
    private PositionServiceImpl service;
    @GetMapping("/Positions")
    private List<PositionDto> findAllPosition(){
        return service.getPosition();
    }
    @GetMapping("/Position/{id}")
    private Position findAllById(@PathVariable Long id){
        return service.getPositionById(id);
    }
    @GetMapping("/position/{name}")
    private Position findAllByName(@PathVariable String name){
        return service.getPositionByName(name);
    }
    @PostMapping("/addPosition")
    private Position addPosition(@RequestBody PositionDto position){
        return service.savePosition(position);
    }

    @PostMapping("/addPositions")
    private List<Position> addPositions(@RequestBody List<Position> positions){
        return service.savePositions(positions);
    }
    @PutMapping("/updatePosition/{id}")
    private PositionDto updatePosition(@PathVariable Long id ,@RequestBody PositionDto positionDto){
        positionDto.setId(id);
        return service.updatePosition(positionDto);
    }
    @DeleteMapping("/deletePosition/{id}")
    private String deletePosition(@PathVariable Long id){
        return service.deletePosition(id);
    }
//    @DeleteMapping("/positions")
//    private ResponseEntity<String> deleteAll(){
//        service.deleteAllPosition();
//        return ResponseEntity.ok("Delete all position");
//    }
}

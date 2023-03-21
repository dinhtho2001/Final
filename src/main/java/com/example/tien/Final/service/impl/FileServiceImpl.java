package com.example.tien.Final.service.impl;

import com.example.tien.Final.Dto.EmployeeDto;
import com.example.tien.Final.entity.Employee;
import com.example.tien.Final.entity.Position;
import com.example.tien.Final.repos.EmployeeRepository;
import com.example.tien.Final.repos.FileReponsitory;
import com.example.tien.Final.repos.PositionRepository;
import com.example.tien.Final.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileReponsitory fileReponsitory;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PositionRepository positionRepository;

    private final String FILENAME = "D:\\Downloads\\Managerment\\Final\\engineers.txt";
    @Override
    public List<Employee> getAllEngineers() throws IOException {
        List<Employee> employees = new ArrayList<>();

        File file =new File(FILENAME);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] tokens = line.split(",");
            Employee employee = new Employee();
            employee.setId(Long.parseLong(tokens[0]));
            employee.setUsername(tokens[1]);
            employee.setPassword(tokens[2]);
            employee.setName(tokens[3]);
            Position position = new Position();
            position.setId(Long.parseLong(tokens[4]));
            employee.setPosition(position);

            employees.add((employee));
        }
        scanner.close();
        return employees;
    }

    public void save(Employee employee) {
        employeeRepository.save(employee);

    }
//    @Override
//    public void save(List<Employee> employees){
//        for (Employee employee : employees){
//            String positionId = employee.getPosition().getId();
//            Position position = positionRepository.findById(positionId);
//            if(position == null){
//                position = new Position();
//                position.setId(positionId);
//                positionRepository.save(position);
//            }
//            employee.setPosition(position.getId());
//        }
//        employeeRepository.save(employee);
//    }


    @Override
    public List<Employee> readEngineersFromFile(){
        List<Employee> employeeList = new ArrayList<>();

        try {
            File file = new File(FILENAME);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();

                String[] tokens = line.split(",");
//                Long id = Long.parseLong(tokens[0]);
//                String username = tokens[1];
//                String password = (tokens[2]);
//                String name = tokens[3];

                Employee employee = new Employee();
                employee.setUsername(tokens[1]);
                employee.setPassword(tokens[2]);
                employee.setName(tokens[3]);


//                    Position position = new Position();
//                    position.setId(Long.parseLong(tokens[4]));
//                    position.setEmployee(employee);
//                    employee.setPosition(position);

                employeeList.add(employee);
            }
            scanner.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return employeeList;
    }

}

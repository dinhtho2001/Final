package com.example.tien.Final.service.impl;

import com.example.tien.Final.Dto.EmployeeDto;
import com.example.tien.Final.Dto.PositionDto;
import com.example.tien.Final.entity.Employee;
import com.example.tien.Final.entity.Position;
import com.example.tien.Final.entity.Salary;
import com.example.tien.Final.repos.EmployeeRepository;
import com.example.tien.Final.repos.PositionRepository;
import com.example.tien.Final.repos.SalaryRepository;
import com.example.tien.Final.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PositionRepository positionRepository;

	@Autowired
	private SalaryRepository salaryRepository;
	
	@Autowired
	private PositionServiceImpl positionService;

	// public List<User> getAllUser() {
//        return userRepository.findAll();
//    }
	@Override
	public List<EmployeeDto> getEmployee() {
		List<Employee> employees = employeeRepository.findAll();
		List<EmployeeDto> employeeDtos = new ArrayList<>();
		for (Employee employee : employees) {

			EmployeeDto employeeDto = EmployeeDto.builder().id(employee.getId()).username(employee.getUsername())
					.password(employee.getPassword()).name(employee.getName())
					.positionId(employee.getPosition().getId())
//                    .salaryId(employee.getSalaries())
					.build();
			Position position = positionRepository.findById(employee.getPosition().getId())
					.orElseThrow(() -> new RuntimeException("Position not found"));
			PositionDto positionDto = PositionDto.builder().id(position.getId()).name(position.getName())
//                    .pay(position.getPay())
					.build();

			employeeDtos.add(employeeDto);
		}
		return employeeDtos;
	}

	// public List<PositionDto> getPosition(){
//        List<Position> positions=positionRepository.findAll();
//        List<PositionDto> positionDtos=new ArrayList<>();
//        for(Position position:positions){
//            PositionDto positionDto=PositionDto.builder()
////                    .id(position.getId())
//                    .name(position.getName())
//                    .pay(position.getPay())
//                    .build();
//            positionDtos.add(positionDto);
//        }
//        return positionDtos;
//    }
//    public User getUserByName(String name){
//        return userRepository.findAllByName(name);
//    }
//
	@Override
	public Employee saveEmployee(EmployeeDto userDto) {
		Position position = positionService.getPositionById(userDto.getPositionId());
//        Attendance attendance = attendanceService.getAttendanceById(userDto.getAttendanceId());
		Employee user = Employee.builder().username(userDto.getUsername()).password(userDto.getPassword())
				.name(userDto.getName()).position(position)
//                .attendance(attendance)
				.build();

		return employeeRepository.save(user);
	}
//    public List<User> saveUsers(List<User> Users){
//        return userRepository.saveAll(Users);
//    }
//    public String deleteUser(int id){
//        userRepository.deleteById(id);
//        return "User removed !!" + id;
//    }
//    //    public User updateUser(User User){
////
////        User existUser = userRepository.findById(User.getId()).orElse(null);
////        existUser.setName(User.getName());
////        existUser.setUsername(User.getUsername());
////        existUser.setPassword(User.getPassword());
////        existUser.setPosition(User.getPosition());
////
//////        existUser.setUser(User.getUser());
////        return userRepository.save(existUser);
////    }
//    public UserDto updateUser(UserDto userDto){
//        Position position = positionService.getPositionById(userDto.getPositionId());
//        User user = userRepository.findById(userDto.getId()).orElseThrow(()-> new RuntimeException("User Not Found"));
//        user.setUsername(userDto.getUsername());
//        user.setPassword(userDto.getPassword());
//        user.setName(userDto.getName());
//        user.setPosition(position);
//        userRepository.save(user);
//        return userDto;
//    }
//
////    public void deleteAllUser(){
////        userRepository.deleteAll();
////    }

	@Override
	public Double getEmployeePayroll(Long id) {
		Salary salaryDb = salaryRepository.findByEmployeeId(id);
		Double salary = 0.00;
		Double amountOfSalaryIn1Day = (getAmountOfSalaryIn1Day(salaryDb.getBaseSalary(), salaryDb.getOvertimeSalary())).doubleValue();
		salary = (double) (amountOfSalaryIn1Day * salaryDb.getDaysWorked());
// Tiền lương  = số tiền lương trong 1 ngày     x số ngày làm việc
		return salary;
	}
	
	private BigDecimal getAmountOfSalaryIn1Day(BigDecimal baseSalary, BigDecimal overtimeSalary) {
		BigDecimal bd1 = baseSalary;
		BigDecimal bd2 = overtimeSalary;
		BigDecimal r = bd1.add(bd2);
		return r;
	}
}

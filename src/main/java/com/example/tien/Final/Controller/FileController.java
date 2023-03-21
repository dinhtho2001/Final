package com.example.tien.Final.Controller;

import com.example.tien.Final.entity.Employee;
import com.example.tien.Final.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping("/engineers")
    public List<Employee> getAllEngineers() throws IOException {
        return fileService.getAllEngineers();
    }

    @PostMapping("/engineers")
    public void saveUsersFromFile() {
        // Đọc dữ liệu từ file .txt và chuyển đổi sang định dạng của model
        List<Employee> employeeList = fileService.readEngineersFromFile();

        // Lưu dữ liệu vào database
        for (Employee employee : employeeList) {
            fileService.save(employee);
        }
    }
}

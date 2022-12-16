package com.register.learning.service.impl;

import com.register.learning.entity.MonthlyExpanse;
import com.register.learning.repositories.MonthlyExpanseRepository;
import com.register.learning.service.ExcelDataReader;
import com.register.learning.service.iface.MonthlyExpanseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MonthlyExpanseServiceImpl implements MonthlyExpanseService {


    @Autowired
    private MonthlyExpanseRepository monthlyExpanseRepository;

    @Override
    public ResponseEntity<Object> save(MultipartFile file) {
        if(ExcelDataReader.checkExcelFormat(file)) {
            try {
                List<MonthlyExpanse> monthlyExpanses = ExcelDataReader.convertExcelToList(file.getInputStream());
                monthlyExpanseRepository.saveAll(monthlyExpanses);
                return new ResponseEntity<>("your data saved successfully", HttpStatus.ACCEPTED);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return new ResponseEntity<>("Please upload excel file", HttpStatus.BAD_REQUEST);
    }
    @Override
    public ResponseEntity<Object> getAllExpanses() {
        return new ResponseEntity<>(monthlyExpanseRepository.findAll(), HttpStatus.ACCEPTED);
    }
}

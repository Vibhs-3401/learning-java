package com.register.learning.controller;

import com.register.learning.service.iface.MonthlyExpanseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/monthly_expanse")
public class MonthlyExpansesController {

    @Autowired
    private MonthlyExpanseService monthlyExpanseService;

    @PostMapping("/excel/upload")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file) {
        return monthlyExpanseService.save(file);
    }

    @GetMapping("/excel/findAll")
    public ResponseEntity<?> getAll() {
        return monthlyExpanseService.getAllExpanses();
    }
}

package com.register.learning.service.iface;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface MonthlyExpanseService {

    ResponseEntity<Object> save(MultipartFile file);

    ResponseEntity<Object> getAllExpanses();
}

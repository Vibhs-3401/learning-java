package com.register.learning.service.iface;

import com.register.learning.entity.MonthlyExpanse;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface MonthlyExpanseService {

    boolean checkExcelFormat(MultipartFile file);

    List<MonthlyExpanse> convertExcelToList(InputStream inputStream);
}

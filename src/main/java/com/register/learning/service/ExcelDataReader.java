package com.register.learning.service;

import com.register.learning.entity.MonthlyExpanse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelDataReader {

    //    check whether given file is excel or not
    public static boolean checkExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    //    to convert excel data to objects and give a list
    public static List<MonthlyExpanse> convertExcelToList(InputStream inputStream) {
        List<MonthlyExpanse> monthlyExpanseList = new ArrayList<>();
        try {
            Workbook xssfWorkbook = new XSSFWorkbook(inputStream);
            Sheet sheet = xssfWorkbook.getSheetAt(0);
            int rowNumber = 0;
            for (Row row : sheet) {
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells = row.iterator();
                int cid = 0;
                MonthlyExpanse monthlyExpanse = new MonthlyExpanse();
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    switch (cid) {
                        case 0:
                            monthlyExpanse.setId((long) cell.getNumericCellValue());
                            break;
                        case 1:
                            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            String date = cell.getStringCellValue();
                            monthlyExpanse.setDate(LocalDate.parse(date, dateFormat));
                            break;
                        case 2:   // not working if the cell is empty, skipping this cell n going to the next one
                            monthlyExpanse.setCredit(cell.getNumericCellValue());
                            break;
                        case 3:
                            if(cell==null){
                                monthlyExpanse.setDebit((double)0);
                                break;
                            }
                            monthlyExpanse.setDebit(cell.getNumericCellValue());
                            break;
                        case 4:
                            monthlyExpanse.setDescription(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                monthlyExpanseList.add(monthlyExpanse);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return monthlyExpanseList;
    }

}

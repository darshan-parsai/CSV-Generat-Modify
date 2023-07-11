package com.excel.excelDemo.serviceImpl;

import com.excel.excelDemo.entity.ExcelEntity;
import com.excel.excelDemo.repository.ExcelRepo;
import com.excel.excelDemo.service.ExcelService;
import com.excel.excelDemo.util.ExcelGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    private ExcelRepo excelRepo;
    @Override
    public void generateExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/o  ctet-stream");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=records_"+currentDateTime+".xlsx";
        response.setHeader(headerKey,headerValue);
        List<ExcelEntity> excelEntityList = excelRepo.findAll();
        ExcelGenerator excelGenerator = new ExcelGenerator(excelEntityList);
        excelGenerator.generate(response);
    }
}

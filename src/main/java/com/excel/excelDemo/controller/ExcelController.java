package com.excel.excelDemo.controller;

import com.excel.excelDemo.service.ExcelService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class ExcelController {
    @Autowired
    private ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping("/generate-excel")
    public void generateExcel(HttpServletResponse response) throws IOException {
        excelService.generateExcel(response);
    }
}

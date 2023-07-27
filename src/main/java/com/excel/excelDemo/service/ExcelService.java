package com.excel.excelDemo.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ExcelService {
    void generateExcel(HttpServletResponse response) throws IOException;

    void save(MultipartFile file);
}

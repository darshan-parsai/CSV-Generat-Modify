package com.excel.excelDemo.controller;

import com.excel.excelDemo.helper.CsvHelper;
import com.excel.excelDemo.responseMessage.ResponseMessage;
import com.excel.excelDemo.service.ExcelService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @PostMapping("/upload-csv")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam MultipartFile file){
        String message = "";
        if(CsvHelper.hasCSVFormat(file)){
            try {
                excelService.save(file);
                message= "uploaded the file "+ file.getOriginalFilename() +" successfully";
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/csv/download/")
                        .path(file.getOriginalFilename()).toUriString();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message,fileDownloadUri));
            } catch (Exception e) {
                message="Could not upload File:"+file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message,""));
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message,""));
    }
}

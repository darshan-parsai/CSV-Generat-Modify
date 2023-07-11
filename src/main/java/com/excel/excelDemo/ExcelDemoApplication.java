package com.excel.excelDemo;

import com.excel.excelDemo.repository.ExcelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExcelDemoApplication {
	@Autowired
	private ExcelRepo excelRepo;

	public static void main(String[] args) {
		SpringApplication.run(ExcelDemoApplication.class, args);
	}
	}

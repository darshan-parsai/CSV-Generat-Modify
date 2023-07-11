package com.excel.excelDemo.repository;

import com.excel.excelDemo.entity.ExcelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelRepo extends JpaRepository<ExcelEntity,Long > {
}

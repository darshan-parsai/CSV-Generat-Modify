package com.excel.excelDemo.util;

import com.excel.excelDemo.entity.ExcelEntity;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

public class ExcelGenerator {
    private List<ExcelEntity> excelEntityList;
    private XSSFWorkbook xssfWorkbook;
    private XSSFSheet xssfSheet;
    public ExcelGenerator(List<ExcelEntity> excelEntityList) {
        this.excelEntityList = excelEntityList;
        xssfWorkbook = new XSSFWorkbook();
    }
    private  void writeHeader(){
        xssfSheet = xssfWorkbook.createSheet("Excel Records");
        Row row = xssfSheet.createRow(0);
        CellStyle cellStyle  = xssfWorkbook.createCellStyle();
        XSSFFont font = xssfWorkbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        cellStyle.setFont(font);

        createCell(row,0,"ID",cellStyle);
        createCell(row,1,"Name",cellStyle);
        createCell(row,2,"Number",cellStyle);
        createCell(row,3,"Date",cellStyle);

    }
    private void createCell(Row row, int columnCount, Object value, CellStyle cellStyle){
        xssfSheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if(value instanceof Integer){
            cell.setCellValue((Integer) value);
        }
        else if (value instanceof Long){
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }
        else if (value instanceof Date){
            cell.setCellValue((Date) value);
        }
        else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(cellStyle);
    }
    private void write(){
        int rowCount = 1;
        CellStyle cellStyle = xssfWorkbook.createCellStyle();
        XSSFFont font = xssfWorkbook.createFont();
        font.setFontHeight(14);
        cellStyle.setFont(font);
        for (ExcelEntity entity: excelEntityList) {
            Row row = xssfSheet.createRow(rowCount++);
            int columnCount =0 ;
            createCell(row, columnCount++, entity.getId(), cellStyle);
            createCell(row, columnCount++, entity.getName(), cellStyle);
            createCell(row, columnCount++, entity.getNumber(), cellStyle);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = dateFormat.format(entity.getDate());
            createCell(row, columnCount++, strDate, cellStyle);

        }
    }

    public void generate(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        xssfWorkbook.write(outputStream);
        xssfWorkbook.close();
        outputStream.close();
    }
}

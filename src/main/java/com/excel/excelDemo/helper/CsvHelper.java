package com.excel.excelDemo.helper;

import com.excel.excelDemo.entity.ExcelEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CsvHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERS = {"Id", "Date", "Name", "Number" };
    public static boolean hasCSVFormat(MultipartFile file){
        if(TYPE.equals(file.getContentType())|| file.getContentType().equals("application/vnd.ms-excel")){
            return  true;
        }
        return false;
    }
    public static List<ExcelEntity> uploadCsv (InputStream is){
          try (BufferedReader filReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
               CSVParser csvParser  = new CSVParser(filReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase()
                       .withTrim());){
                   List<ExcelEntity> excelEntityList = new ArrayList<>();
                   Iterable<CSVRecord> csvRecords = csvParser.getRecords();
              SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
              for (CSVRecord csvRecord : csvRecords){
                  Date date;
                  date = new Date(dateFormat.parse(csvRecord.get("Date").toString()).getTime());
                  ExcelEntity excelEntity = new ExcelEntity(
                  Long.parseLong(csvRecord.get("Id")),
                  csvRecord.get("Name"),
                  csvRecord.get("Number"),
                  date
                  );
                  excelEntityList.add(excelEntity);
              }
              return excelEntityList;
          } catch (UnsupportedEncodingException e) {
              throw new RuntimeException(e);
          } catch (IOException e) {
              throw new RuntimeException("fail to parse csv");
          } catch (ParseException e) {
              throw new RuntimeException(e);
          }
    }

}

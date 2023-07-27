package com.excel.excelDemo.responseMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseMessage {
       private String message;
       private String fileDownloadUri;
}

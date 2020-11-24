package com.explore.galaxy.basic.utils.tool.excel;

import com.explore.galaxy.basic.utils.tool.file.support.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.util.Date;

public class BasicExcelFunc implements IBasicExcelFunc {
    @Override
    public File exportExcel() {
        Workbook excel = new HSSFWorkbook();
        //work sheet create
        Sheet sheet = excel.createSheet();
        //title -> first row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < 5; i++) {
            //add title message
            Cell cell = headerRow.createCell(i++);
            cell.setCellValue("Test" + i);
        }
        for (int i = 1; i < 5; i++) {
            Row row = sheet.createRow(1);
            Cell cell = row.createCell(i++);
            cell.setCellValue("data");
        }
        String excelName = "Test";
        String filePath = FileUtils.getTmpDir()+excelName+".xlxs";
        File file= null;
        try {
            file = new File(filePath);
            file.deleteOnExit();
            file.createNewFile();
            OutputStream outputStream = new FileOutputStream(file);
            excel.write(outputStream);
            excel.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public void importExcel() {

    }

    private ResponseEntity<FileSystemResource> ExportFile(String adapterName, boolean isTemplate) throws IOException {

//        File file = iExcelExportService.ExportToFile(adapterName, isTemplate);
        File file = exportExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        headers.add("Content-Disposition", "attachment; filename=" + new String(file.getName().getBytes("UTF-8"), "iso-8859-1"));

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.valueOf(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .body(new FileSystemResource(file));
    }
}

package com.cutie.common;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;

/**
 * Created by Cutie on 2018/4/19.
 */
public class ExcelTest {

    public static void main(String[] args) throws Throwable {
        // keep 100 rows in memory, exceeding rows will be flushed to disk
        Workbook wb = new SXSSFWorkbook(100);
        Sheet sh = wb.createSheet();
        for(int rownum = 0; rownum < 200000; rownum++){
            Row row = sh.createRow(rownum);
            for(int cellnum = 0; cellnum < 10; cellnum++){
                Cell cell = row.createCell(cellnum);
                String address = new CellReference(cell).formatAsString();
                cell.setCellValue(address); }
        }
        FileOutputStream out = new FileOutputStream("C:/Users/Administrator/Desktop/sxssf.xlsx");
        wb.write(out);
        out.close();
    }
}

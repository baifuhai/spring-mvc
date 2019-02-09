package com.test.helloworld.view;

import com.test.helloworld.entity.Person;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Component
public class MyExcelView extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileName = map.get("fileName").toString() + ".xls";

        response.setHeader("content-disposition", "attachment; filename=" + URLEncoder.encode(fileName,"utf-8"));
        response.setContentType("application/ms-excel; charset=utf-8");
        response.setCharacterEncoding("utf-8");

        List<Person> personList = (List<Person>) map.get("personList");

        Sheet sheet = workbook.createSheet("Person List");
        sheet.setDefaultColumnWidth(10);

        Font font = workbook.createFont();
        font.setFontName("Arial");
        font.setBold(true);
        //font.setColor(HSSFColor.WHITE.index);

        CellStyle style = workbook.createCellStyle();
        //style.setFillForegroundColor(HSSFColor.BLUE.index);
        //style.setFillPattern((short) 1);
        style.setFont(font);

        int rowIndex = 0;
        int cellIndex = 0;

        Row row = null;
        Cell cell = null;

        row = sheet.createRow(rowIndex++);

        cell = row.createCell(cellIndex++);
        cell.setCellValue("编号");
        cell.setCellStyle(style);

        cell = row.createCell(cellIndex++);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);

        for (Person person : personList) {
            cellIndex = 0;
            row = sheet.createRow(rowIndex++);
            row.createCell(cellIndex++).setCellValue(person.getId());
            row.createCell(cellIndex++).setCellValue(person.getName());
        }
    }

}

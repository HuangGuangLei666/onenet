package com.hgl.myforum.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author HGL
 * @Date 2020/9/12
 */
public class ExportUtil {

    public static CellStyle getCellStyle(HSSFWorkbook wb) {
        HSSFCellStyle style = wb.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        return style;
    }

    public static void setAllHeader(HSSFWorkbook wb, HSSFSheet sheet, String[] cells, CellStyle style) {
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < cells.length; i++) {
            HSSFCell cell = row.createCell(i);
            sheet.setColumnWidth(i, cells[i].getBytes().length * 256);
            setCellValue(wb, cell, cells[i], style);
        }
    }

    public static void setCellValue(HSSFWorkbook wb, HSSFCell cell, Object obj, CellStyle style) {
        if (cell != null) {
            cell.setCellStyle(style);
            cell.setCellValue(obj == null ? "" : obj.toString());
        }
    }

    public static void setAllCell(HSSFWorkbook wb, HSSFSheet sheet, int n, String[] cells, CellStyle style) {
        HSSFRow row = sheet.createRow(n);
        if (row == null) {
            return;
        }
        for (int i = 0; i < cells.length; i++) {
            HSSFCell cell = row.createCell(i);
            setCellValue(wb, cell, cells[i], style);
        }
    }

    public static void exportXls(HSSFWorkbook wb, String downNamePre, HttpServletResponse response) {
        try {
            String fileNameDisplay = new String(downNamePre.getBytes("GB2312"), "ISO_8859_1") ;
            response.reset();
//
            response.setHeader("Cache-Control", "min-fresh");
            response.setHeader("Access-Control-Allow-Origin", "*");
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());


            int fileSize = wb.getBytes().length;
//            logger.info("fileSize===" + fileSize);
            if (fileSize > 2048000)
            {
//                logger.info("application/x-zip-compressed" );
                response.addHeader("Content-Disposition", "attachment;filename=" + fileNameDisplay+ ".zip");

                response.setContentType("application/x-zip-compressed");
                ZipOutputStream zip = new ZipOutputStream( toClient );

                ZipEntry entry = new ZipEntry(downNamePre+".xls");
                zip.putNextEntry(entry);

                wb.write(zip);
                zip.flush();
                zip.close();
            }
            else
            {
                response.addHeader("Content-Disposition", "attachment;filename=" + fileNameDisplay+ ".xls");
                response.setContentType("application/vnd.ms-excel");
                wb.write(toClient);
            }


            toClient.flush();
            toClient.close();

        } catch (Exception e) {
//            logger.error("exportXls error.", logger, e);
        }
    }
}

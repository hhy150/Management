package com.example.management.util;

import com.example.management.entity.ExcelData;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Component
public class ExcelUtil {

    private final static  Logger log = LoggerFactory.getLogger(ExcelUtil.class);

    public static boolean exportExcel(HttpServletResponse response, ExcelData data,String name) throws IOException {
            log.info("导出解析开始，fileName:{}",data.getFileName());
            try {
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet(name+"成员表");
                setTitle(workbook, sheet, data.getHead());
                setData(sheet, data.getData());
                //在研究
                setBrowser(response, workbook, data.getFileName());
                log.info("导出解析成功!");
            } catch (Exception e) {
                log.info("导出解析失败!");
                e.printStackTrace();
                throw new IOException();
            }
            return true;
        }

    private static void setTitle(HSSFWorkbook workbook, HSSFSheet sheet, String[] str){
        try {
            HSSFRow row = sheet.createRow(0);
            //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
            for (int i = 0; i <= str.length; i++) {
                sheet.setColumnWidth(i, 15 * 256);
            }
            //设置为居中加粗,格式化时间格式
            HSSFCellStyle style = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
            //创建表头名称
            HSSFCell cell;
            for (int j = 0; j < str.length; j++) {
                cell = row.createCell(j);
                cell.setCellValue(str[j]);
                cell.setCellStyle(style);
            }
        } catch (Exception e) {
            log.info("导出时设置表头失败！");
            e.printStackTrace();
        }
    }

    private static void setData(HSSFSheet sheet, List<String[]> data) {
        try{
            int rowNum = 1;
            for (int i = 0; i < data.size(); i++) {
                HSSFRow row = sheet.createRow(rowNum);
                for (int j = 0; j < data.get(i).length; j++) {
                    row.createCell(j).setCellValue(data.get(i)[j]);
                }
                rowNum++;
            }
            log.info("表格赋值成功！");
        }catch (Exception e) {
            log.info("表格赋值失败！");
            e.printStackTrace();
        }
    }

    private static void setBrowser(HttpServletResponse response, HSSFWorkbook workbook, String fileName) throws IOException {
     try{
            //清空response
            response.reset();
            //设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

            ServletOutputStream stream = response.getOutputStream();
            System.out.println(stream);

            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            //将excel写入到输出流中
            workbook.write(os);
            os.flush();
            os.close();
            log.info("设置浏览器下载成功！");
        } catch (Exception e) {
            log.info("设置浏览器下载失败！");
            e.printStackTrace();
            throw new IOException();
        }

    }


    public static List<Object[]> importExcel(MultipartFile file) throws IOException {
        log.info("导入解析开始，fileName:{}",file.getOriginalFilename());
        try {
            List<Object[]> list = new ArrayList<>();
            InputStream inputStream = file.getInputStream();
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            //获取sheet的行数
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 0; i <= lastRowNum; i++) {
                //过滤表头行
                if (i == 0)
                    continue;
                //获取当前行的数据
                HSSFRow row = sheet.getRow(i);
                Object[] objects = new Object[row.getPhysicalNumberOfCells()];
                int index = 0;
                Iterator<Cell> iterator = row.iterator();
                while (iterator.hasNext()){
                    Cell cell = iterator.next();
                    if (cell.getCellType()==0) {
                        objects[index] = cell.getNumericCellValue();
                    }
                    if (cell.getCellType()==1) {
                        objects[index] = cell.getStringCellValue();
                    }
                    if (cell.getCellType()==4) {
                        objects[index] = cell.getBooleanCellValue();
                    }
                    if (cell.getCellType()==5) {
                        objects[index] = cell.getErrorCellValue();
                    }
                    index++;
                }
                list.add(objects);
            }
            log.info("导入文件解析成功！");
            return list;
        }catch (Exception e){
            log.info("导入文件解析失败！");
            e.printStackTrace();
            throw  new IOException();
        }
    }


}

package com.genscript.gsscm.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: 解析Excel文件工具类
 * @author: Golf   
 * @createDate: 2010/7/25 4:42 PM
 */
@Service
@Transactional
public class ExcelUtil {

    private int totalRows = 0;       
    private int totalCells = 0;

    
    public ExcelUtil() {
    }
    
    public List<ArrayList<String>> read(File file, String fileName) {
        List<ArrayList<String>> dataLst = new ArrayList<ArrayList<String>>();

        if (fileName == null || !fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
            return dataLst;
        }

        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }

        if (file == null || !file.exists()) {
            return dataLst;
        }

        try {
            dataLst = read(new FileInputStream(file), isExcel2003);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dataLst;
    }
    
    /**
     * 重写read方法，对于第三方工具生成的Excel读取异常时以文件流的形式读取文件内容
     * @author Zhang Yong
     * add date 2011-11-25
     */
    public List<ArrayList<String>> readTool(File file, String fileName) {
        List<ArrayList<String>> dataLst = new ArrayList<ArrayList<String>>();
        if (fileName == null || !fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
            return dataLst;
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        if (file == null || !file.exists()) {
            return dataLst;
        }
        try {
            dataLst = readTool(new FileInputStream(file), isExcel2003);
        } catch (Exception ex) {
            ex.printStackTrace();
            //对于第三方工具生成的Excel读取异常时以文件流的形式读取文件内容
            BufferedReader br;
    		try {
    			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    	    	String data = null;
    	    	ArrayList<String> list = null;
    	    	while((data = br.readLine())!=null) {
    	    		String[] datas = data.split("\t");
    	    		list = new ArrayList<String>();
    	    		for (String dataStr : datas) {
    	    			list.add(dataStr);
    	    		} 
    	    		dataLst.add(list);
    	    	}
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }
        return dataLst;
    }

    public List<ArrayList<String>> read(String fileName) {
        List<ArrayList<String>> dataLst = new ArrayList<ArrayList<String>>();

        if (fileName == null || !fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
            return dataLst;
        }

        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }

        File file = new File(fileName);
        if (file == null || !file.exists()) {
            return dataLst;
        }

        try {
            dataLst = read(new FileInputStream(file), isExcel2003);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dataLst;
    }

    public List<ArrayList<String>> read(InputStream inputStream,
                                        boolean isExcel2003) {
        List<ArrayList<String>> dataLst = null;
        try {

            Workbook wb = isExcel2003 ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
//            Workbook wb =   new HSSFWorkbook(inputStream) ;
            dataLst = read(wb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataLst;
    }
    
    /**
     * 重写read方法，遇异常时抛出异常
     * @author Zhang Yong
     * add date 2011-11-25
     * @param inputStream
     * @param isExcel2003
     * @return
     * @throws IOException 
     */
    public List<ArrayList<String>> readTool(InputStream inputStream,  boolean isExcel2003) throws IOException {
		List<ArrayList<String>> dataLst = null;
		Workbook wb = isExcel2003 ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
		dataLst = read(wb);
		return dataLst;
	}

    public List<ArrayList<String>> read_new(InputStream inputStream) {
        List<ArrayList<String>> dataLst = null;
        try {
            Workbook wb =   new HSSFWorkbook(inputStream) ;
            dataLst = read(wb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataLst;
    }


    private List<ArrayList<String>> read(Workbook wb) {
        List<ArrayList<String>> dataLst = new ArrayList<ArrayList<String>>();
        Sheet sheet = wb.getSheetAt(0);
        this.totalRows = sheet.getPhysicalNumberOfRows();
        if (this.totalRows >= 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
 
        for (int r = 0; r < this.totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }

            ArrayList<String> rowLst = new ArrayList<String>();
            int cells = row.getPhysicalNumberOfCells();
            for (short c = 0; c < cells; c++) {
                Cell cell = row.getCell(c);
                String cellValue = "";
                if (cell == null) {
                    rowLst.add(cellValue);
                    continue;
                }

                if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date d = cell.getDateCellValue();
                        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                        cellValue = formater.format(d);
//						cellValue = getRightStr(cell.getDateCellValue() + "");
                    } else {
                        cellValue = getRightStr(cell.getNumericCellValue() + "");
                    }
                } else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
                    cellValue = cell.getStringCellValue();
                } else if (Cell.CELL_TYPE_FORMULA == cell.getCellType()) {
                    cellValue = cell.getCellFormula();
                } else if (Cell.CELL_TYPE_BOOLEAN == cell.getCellType()) {
                    cellValue = cell.getBooleanCellValue() + "";
                } else { 
                } 
                rowLst.add(cellValue);
            }
            dataLst.add(rowLst);
  
        }
        return dataLst;
    }

    private String getRightStr(String sNum) {
        DecimalFormat decimalFormat = new DecimalFormat("#.000000");
        String resultStr = decimalFormat.format(new Double(sNum));
        if (resultStr.matches("^[-+]?\\d+\\.[0]+$")) {
            resultStr = resultStr.substring(0, resultStr.indexOf("."));
        }
        return resultStr;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalCells() {
        return totalCells;
    }

    public static void main(String[] args) {
//        ExcelUtil ex = new ExcelUtil();
//        try {
//            List lst = ex.read(new FileInputStream("F:\\ww.xlsx"), false);
//            lst.remove(0);
//            for (int i = 0; i < lst.size(); i++) {
//                System.out.println(lst.get(i).toString());
//            } 
//        } catch (FileNotFoundException e) {
//         
//            e.printStackTrace();
//        }
    	BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Documents and Settings/user/Desktop/上传引物143820-1.xls")));
	    	String data = null;
	    	while((data = br.readLine())!=null) {
	    		String[] datas = data.split("\t");
	    		for (String dataStr : datas) {
		    		System.out.println(dataStr);
	    		} 
	    	}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}

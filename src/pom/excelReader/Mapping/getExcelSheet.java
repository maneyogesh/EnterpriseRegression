package pom.excelReader.Mapping;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class getExcelSheet {
   public static HSSFWorkbook WBook = null;
   public static HSSFSheet WSheet= null;
   

    public static HSSFSheet DataSheet(String FilePath, String SheetName){
        File file = new File(FilePath);
        try {
            FileInputStream fis = new FileInputStream(file);
            WBook = new HSSFWorkbook(fis);
            WSheet = WBook.getSheet(SheetName);         
        } catch (Exception e) {         
            e.printStackTrace();
        }
        return WSheet;      
    }       

}
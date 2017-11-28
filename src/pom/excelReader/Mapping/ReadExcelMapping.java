package pom.excelReader.Mapping;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ReadExcelMapping {

	public  List<Map<String, String>> ReadExcel(String FilePath,String SheetName) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(FilePath);
		HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
		
		List<Map<String, String>> listOfRows = new ArrayList<Map<String, String>>();
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) 
		{
			String sheetName = workbook.getSheetName(i);
			if (sheetName.trim().equalsIgnoreCase(SheetName)) {
				Sheet sheet = workbook.getSheetAt(i);
				for (int rowIndex = 1; rowIndex <= sheet.getPhysicalNumberOfRows(); rowIndex++) 
				{
					Map<String, String> map = new HashMap<String, String>();
					mappingExcelValues(sheet, map, rowIndex);
					listOfRows.add(map);
					
				}
				
			}
		}
		return listOfRows;
		
		
	}

	public static void mappingExcelValues(Sheet sheet,
			Map<String, String> map, int rowIndex) {
		Row headerRow = sheet.getRow(0);
		Row dataRow = sheet.getRow(rowIndex);
		if (dataRow != null)
			for (int j = 0; j < headerRow.getLastCellNum(); j++) {
				Cell testDataCell = dataRow.getCell(j);
				if (testDataCell != null)
					switch (testDataCell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						testDataCell.setCellType(Cell.CELL_TYPE_STRING);
						map.put(headerRow.getCell(j).getStringCellValue(),
								dataRow.getCell(j).getStringCellValue());
						break;
					case Cell.CELL_TYPE_STRING:
						map.put(headerRow.getCell(j).getStringCellValue(),
								dataRow.getCell(j).getStringCellValue());
						break;
					case Cell.CELL_TYPE_BLANK:
						if (!(headerRow.getCell(j).getStringCellValue()
								.equalsIgnoreCase("") || headerRow.getCell(j) == null))
							map.put(headerRow.getCell(j).getStringCellValue(),
									dataRow.getCell(j).getStringCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						testDataCell.setCellType(Cell.CELL_TYPE_STRING);
						map.put(headerRow.getCell(j).getStringCellValue(),
								dataRow.getCell(j).getStringCellValue());
						break;

					}
			}
	}
}

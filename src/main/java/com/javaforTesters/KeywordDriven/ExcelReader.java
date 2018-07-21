package com.javaforTesters.KeywordDriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

	public static List<List<String>> readExcel(String path) throws EncryptedDocumentException, InvalidFormatException {
		int rowCnt;
		int colCnt;
		List<List<String>> data = null;
		List<String>[] cols;

		try (InputStream is = new FileInputStream(new File(path).getAbsolutePath())) {
			Workbook wb = WorkbookFactory.create(is);
			Sheet sh = wb.getSheetAt(0);
			rowCnt = sh.getLastRowNum();
			colCnt = sh.getRow(0).getLastCellNum();
			data = new ArrayList<List<String>>();
			cols = new ArrayList[colCnt];
			Cell cell;
			for (int i = 0; i < colCnt; i++) {
				cols[i] = new ArrayList<String>();
				for (int j = 0; j < rowCnt; j++) {
					cell = sh.getRow(j).getCell(i);
					cols[i].add(cell.getStringCellValue());
				}
				data.add(cols[i]);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;

	}
}

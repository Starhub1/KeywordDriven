package com.javaforTesters.KeywordDriven;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import utils.keywordfeeder;

public class TestCase extends TestSuite {

	public static ThreadLocal<List<List<String>>> data = new ThreadLocal<>();
	public static ThreadLocal<Integer> sRow = new ThreadLocal<>();

	/*
	 * private String file;
	 * 
	 * @Factory(dataProvider = "testcases", dataProviderClass = TestSuite.class)
	 * public TestCase(String file) { this.file = file; }
	 */
	@Test(dataProvider = "testcases", dataProviderClass = TestSuite.class)
	public void testcase1(String file) throws Exception {
		data.set(ExcelReader.readExcel(file));
		System.out.println(Class.forName("java.util.ArrayList"));
		sRow.set(1);
		int eRow = data.get().get(2).size();
		execute(sRow.get(), eRow, data.get(), driver.get());

	}

	public static void execute(int sRow, int eRow, List<List<String>> data, WebDriver driver) throws Exception {
		while (eRow > sRow) {
			List<Object> myParamList = new ArrayList<Object>();
			List<String> locatorType = data.get(0);
			List<String> locatorValue = data.get(1);
			List<String> action = data.get(2);
			List<String> params = data.get(3);
			myParamList.add(driver);
			myParamList.add(locatorType.get(sRow));
			myParamList.add(locatorValue.get(sRow));
			myParamList.add(params.get(sRow));
			if (action.get(sRow).equals("StartDataTable"))
				myParamList.add(data);
			myParamList.removeIf(o -> o.equals(""));
			Object[] paramListObject = new Object[myParamList.size()];
			paramListObject = myParamList.toArray(paramListObject);
			keywordfeeder keywordexec = new keywordfeeder();
			keywordexec.keywordinvoker("Keywords.Keywords", action.get(sRow), paramListObject);
			sRow++;
		}
	}

}

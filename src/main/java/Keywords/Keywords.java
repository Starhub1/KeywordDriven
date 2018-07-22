package Keywords;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.javaforTesters.KeywordDriven.ExcelReader;
import com.javaforTesters.KeywordDriven.TestCase;

public class Keywords {

	public void Open(WebDriver driver, String url) {
		driver.get(url);
	}

	public void Type(WebDriver driver, String locatorType, String locatorValue, String text) {
		By by = getlocator(locatorType, locatorValue);
		driver.findElement(by).sendKeys(text);
	}

	public void Click(WebDriver driver, String locatorType, String locatorValue) {
		By by = getlocator(locatorType, locatorValue);
		driver.findElement(by).click();
	}

	public void Check(WebDriver driver, String locatorType, String locatorValue) {
		By by = getlocator(locatorType, locatorValue);
		driver.findElement(by).click();
	}

	public void VerifyTitle(WebDriver driver, String title) {
		assertEquals(driver.getTitle(), title);
	}

	public void VerifyText(WebDriver driver, String locatorType, String locatorValue, String text) {
		By by = getlocator(locatorType, locatorValue);
		assertEquals(driver.findElement(by).getText(), text);
	}

	public void VerifySelected(WebDriver driver, String locatorType, String locatorValue) {
		By by = getlocator(locatorType, locatorValue);
		assertEquals(driver.findElement(by).isSelected(), true);
	}

	public void IfTrue(WebDriver driver, String condtion, List<List<String>> data) {
		String[] c = condtion.split("==");
		int sRow = TestCase.sRow.get();
		if (!c[0].equals(c[1])) {
			List<String> action = data.get(2);
			sRow = action.subList(sRow, action.size()).indexOf("EndIf") + sRow;
			TestCase.sRow.set(sRow);
		}
	}

	public void EndIf() {
		System.out.println("End of If Statment");
	}

	public void StartDataTable(WebDriver driver, String path, List<List<String>> data)
			throws Exception, InvalidFormatException {
		List<List<String>> testdata = ExcelReader.readExcel(path);
		int tdsize = testdata.get(0).size();
		int sRow = TestCase.sRow.get();

		for (int i = 1; i < tdsize; i++) {
			TestCase.sRow.set(sRow + 1);
			int eRow = data.get(2).subList(sRow, data.get(2).size()).indexOf("EndDataTable") + sRow;
			List<List<String>> temp = new ArrayList<List<String>>(data);
			List<String> params = new ArrayList<String>(temp.get(3));
			params = getParameter(params, testdata, i);
			temp.set(3, params);
			TestCase.execute(eRow, temp, driver);
		}
		// TestCase.sRow.set(sRow + eRow);
	}

	/*
	 * private List<List<String>> getSubList(List<List<String>> data, int sRow)
	 * { List<String>[] cols = new ArrayList[data.size()]; List<List<String>>
	 * temp = new ArrayList<List<String>>(); for (int i = 0; i < data.size();
	 * i++) { cols[i] = new ArrayList<String>(); for (int j = sRow; j <
	 * data.get(i).size(); j++) {
	 * 
	 * cols[i].add(data.get(i).get(j)); } temp.add(cols[i]); } return temp; }
	 */

	private List<String> getParameter(List<String> params, List<List<String>> testdata, int r) {
		for (int i = 0; i < params.size(); i++) {
			if (params.get(i).contains("DataTable")) {
				String p = params.get(i).replaceAll("(^\\$\\(\\w+.)|(\\)).*", "");
				// search the element in the testdata
				for (List<String> td : testdata) {
					if (td.contains(p)) {
						int c = testdata.indexOf(td);
						String temp = params.get(i).replaceAll("^[\\$].*\\)", testdata.get(c).get(r));
						params.set(i, temp);
					}
				}

			}
		}
		return params;
	}

	public void EndDataTable() {
		System.out.println("End of the Data Provider");
	}

	public void Close(WebDriver driver) {
		driver.quit();
	}

	public By getlocator(String locatorType, String locatorValue) {
		By by;
		switch (locatorType) {
		case "id":
			by = By.id(locatorValue);
			break;
		case "name":
			by = By.name(locatorValue);
			break;
		case "classname":
			by = By.className(locatorValue);
			break;
		case "css":
			by = By.cssSelector(locatorValue);
			break;
		case "linkText":
			by = By.linkText(locatorValue);
			break;
		case "partialLinkText":
			by = By.partialLinkText(locatorValue);
			break;
		case "xpath":
			by = By.xpath(locatorValue);
			break;
		default:
			by = null;
			break;
		}

		return by;

	}

}

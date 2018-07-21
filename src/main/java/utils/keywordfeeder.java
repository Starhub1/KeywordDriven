package utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

public class keywordfeeder {
	Class<?> className;

	public void keywordinvoker(String cls, String mtd, Object... inputArgs) throws Exception {

		Class<?> params[] = new Class[inputArgs.length];
		params[0] = WebDriver.class;
		for (int i = 1; i < inputArgs.length; i++) {
			if (inputArgs[i] instanceof ArrayList)
				params[i] = Class.forName("java.util.List");
			else
				params[i] = String.class;
		}
		className = Class.forName(cls);
		Object _instance = className.newInstance();
		Method myMethod = className.getDeclaredMethod(mtd, params);
		myMethod.invoke(_instance, inputArgs);

	}
}

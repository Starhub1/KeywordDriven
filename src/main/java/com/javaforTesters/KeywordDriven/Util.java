package com.javaforTesters.KeywordDriven;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Util {
	public static List<String> getTestcasePath() {
		Path path = Paths.get(System.getProperty("user.dir") + "//testcases");
		List<String> files = null;

		try {
			files = Files
					.find(path, Integer.MAX_VALUE,
							(p, attr) -> (String.valueOf(p.getFileName()).endsWith(".xlsx")
									|| String.valueOf(p.getFileName()).endsWith("xls") && attr.isRegularFile()))
					.map(p -> p.toString()).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return files;
	}
}

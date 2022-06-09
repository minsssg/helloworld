package com.helloworld.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentLocalDateTime {

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddkkmmss");
	
	public static String now() {
		return formatter.format(LocalDateTime.now());
	}
}

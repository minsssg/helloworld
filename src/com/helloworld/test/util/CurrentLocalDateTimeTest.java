package com.helloworld.test.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.helloworld.util.CurrentLocalDateTime;

public class CurrentLocalDateTimeTest {

	@Test
	@DisplayName("현재 로컬 시간을 문자열로 보여줌.")
	void nowTest() {
		System.out.println(CurrentLocalDateTime.now());
	}
}

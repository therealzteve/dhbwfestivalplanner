package com.helper;

import static org.junit.Assert.*;

import org.junit.Test;

public class RandomStringTest {

	@Test
	public void test() {
		int length=5;
		RandomString s1= new RandomString(length);
		RandomString s2= new RandomString(length);
		assertEquals(s1.resultStringLength(), length);
		assertEquals(s2.resultStringLength(), length);
		assertNotSame(s1.resultString(), s2.resultString());
	}

}

package com.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RandomStringTest {
	int length;
	RandomString s1,s2;
	
	@Before
	public void setup(){
		length=5;
		s1= new RandomString(length);
		s2= new RandomString(length);
	}

	@Test
	public void test() {
		assertEquals(s1.resultStringLength(), length);
		assertEquals(s2.resultStringLength(), length);
		assertNotSame(s1.resultString(), s2.resultString());
	}
	
	@After
	public void end(){
		System.out.println("Test Completed");
	}

}

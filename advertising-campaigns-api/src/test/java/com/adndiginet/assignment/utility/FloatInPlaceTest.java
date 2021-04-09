package com.adndiginet.assignment.utility;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FloatInPlaceTest {

	
	public static final float BAUDGET = 234.35f;
	
	@Test
	public void test() {
		float res = FloatInPlace.convertIn2DecimalPlace(BAUDGET);
		assertTrue(BAUDGET == res);
	}

}

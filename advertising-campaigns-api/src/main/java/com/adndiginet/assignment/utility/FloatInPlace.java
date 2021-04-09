package com.adndiginet.assignment.utility;

import org.springframework.stereotype.Component;

/**
 * @author    Md Mizanur Rahman<kmmizanurrahmanjp@gmail.com>
 * @version   0.0.1-SNAPSHOT
 * @since     0.0.1-SNAPSHOT
 */
@Component
public class FloatInPlace {
	
	public static float convertIn2DecimalPlace(float number) {
	    String stringValue = String.format("%.2f", number);
	    float floatingValue = Float.parseFloat(stringValue);
	    return floatingValue;
	}
	
	
	public static float convertIn2DecimalPlace(int number) {
		String stringValue = String.format("%.2f", number);
	    float floatingValue = Float.parseFloat(stringValue);
	    return floatingValue;
	}
}

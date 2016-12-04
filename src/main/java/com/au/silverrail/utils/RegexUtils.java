package com.au.silverrail.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
	
	public static Matcher CreateMatcher(String pattern, String input) {
		Pattern regex = Pattern.compile(pattern);
		return regex.matcher(input);
	}
}
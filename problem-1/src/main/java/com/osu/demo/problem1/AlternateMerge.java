package com.osu.demo.problem1;

/**
 * The Class AlternateMerge.
 * 
 */
public class AlternateMerge {
	
	/**
	 * Alternate merge strings.
	 * 
	 * Given 2 strings, merge them in an alternate way, i.e. the final string’s first character is the first character of the longer string, 
	 * the second character of the final string is the first character of the shorter string and so on. And if once you reach end of the shorter string 
	 * while if the longer string is still remaining then append the remaining of that longer string to final string. If the length of both strings are equal, 
	 * then the final string’s first character is the first character of the first string
	 *
	 * @param string1 the {@code String} to merge
	 * @param string2 the {@code String} to merge
	 * @return the string
	 */
	public static String alternateMergeStrings(String string1,String string2) {
		if(string1==null || string2==null) {
			return string1==null?string2:string1;
		}
		else if(string2.length()>string1.length()) {
			return mergeStrings(string2,string1);
		}else {
			return mergeStrings(string1,string2);
		}
	}
	
	/**
	 * Merge string.
	 *
	 * @param longer the {@code String}
	 * @param shorter the {@code String}
	 * @return the string
	 */
	private static String mergeStrings(String longer,String shorter) {
		StringBuilder result = new StringBuilder();
		for(int i=0;i<shorter.length();i++) {
			result.append(longer.charAt(i)).append(shorter.charAt(i));
		}
		if(shorter.length()!=longer.length()) {
			result.append(longer.substring(shorter.length()));
		}		
		return result.toString();
	}	
	
}

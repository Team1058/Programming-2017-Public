package org.usfirst.frc.team1058.robot;

public class ParseHelper {
	public static boolean isNull(String s)
	{
		return s.equalsIgnoreCase("null");
	}
	
	public static boolean isNumber(String s)
	{
		try {
			Double.parseDouble(s);
		}
		catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isBoolean(String s)
	{
		try {
			Boolean.parseBoolean(s);
		}
		catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isString(String s1)
	{
		char alph[] = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		s1 = s1.toLowerCase();
		char[] s = s1.toCharArray();
		if (isBoolean(s1)){
			return false;
		}
		for (int x = 0; x < s.length; x++){
			for (int y = 0; y < alph.length; y++){
				if (s[x] == alph[y]){
					return true;
				}
			}
		}
		return false;
		
	}
	
	public static String unescapeString(String s)
	{
		return s.substring(1, s.length() - 1).replaceAll("\\\"", "\"");
	}
}

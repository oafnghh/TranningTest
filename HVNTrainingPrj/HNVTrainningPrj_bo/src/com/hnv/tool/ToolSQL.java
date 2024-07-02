package com.hnv.tool;


public class ToolSQL {
	private static String COND_EQUAL_TYP_INT 		= " %s.%s = %d";
	private static String COND_EQUAL_TYP_DOUBLE 	= " %s.%s = %f";
	private static String COND_EQUAL_TYP_STR 		= " %s.%s = '%s'";
	
	private static String SQL_SELECT_FROM			= "select %s from ";
	
	private static String SQL_WHERE					= "where ";
	private static String SQL_OP_AND				= " and ";
	private static String SQL_OP_OR					= " or ";
	
	private static String SQL_JOIN_INNER			= "%s %s inner join  %s %s on ";
	private static String SQL_JOIN_LEFT				= "%s %s left join  %s %s on ";
	private static String SQL_JOIN_RIGHT			= "%s %s right join  %s %s on ";
	private static String SQL_JOIN_FULL				= "%s %s full join  %s %s on ";
	
	public static String reqCondEqualTypInt (String tabAlias, String tabCol, int value) {
		return String.format(COND_EQUAL_TYP_INT, tabAlias	, tabCol, value	);
	}
	
	public static String reqCondEqualTypStr (String tabAlias, String tabCol, String value) {
		return String.format(COND_EQUAL_TYP_STR, tabAlias	, tabCol, value	);
	}
	
	public static String reqSelectWhere (String...tabCols) {
		String part01 = ""; 
		for (String col: tabCols){
			part01 =  part01  + "," +  col;
		}
		
		//remove first ","
		part01 = part01.substring(1);
		
		
		return String.format(SQL_SELECT_FROM, part01);
	}
	
	public static String reqSelect (String select, String where, String cond, String order){
		String s = select + where + cond + order;
		return s;
	}
	
	public static void main(String []a){
		
	}
}

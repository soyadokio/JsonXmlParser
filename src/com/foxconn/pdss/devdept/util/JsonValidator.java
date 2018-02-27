package com.foxconn.pdss.devdept.util;

import net.sf.json.JSONObject;

public class JsonValidator {

	private static String msgErr= null;
	private static String indent= "    ";//4個空格
	private static String lineSe= System.lineSeparator();
	
	public static boolean isJson(String input) {
		JSONObject jsonObject= null;
		try {
			jsonObject= JSONObject.fromObject(input);
			return true;
		} catch (Exception e) {
			msgErr= e.toString();
			msgErr= msgErr.substring(msgErr.indexOf(":")+ 2);//將報錯信息中的類名部分截掉
//			System.out.println(msgErr);
			return false;
		}
	}
	
	//通過JSONObject將JSON字串轉為JSON對象再轉回來，以將格式補全。如：單引號變雙引號，無引號key補上雙引號，去掉多餘空格等
	public static String init(String input) {
		if(isJson(input)) {
			JSONObject jsonObject= JSONObject.fromObject(input);
			String strJson= jsonObject.toString();
			return strJson;
		} else {
			return null;
		}
	}
	
	//格式處理：加減縮進
	public static String format(String json) {
		StringBuffer buff= new StringBuffer();
		int indent= 0;
		boolean lastLineBreak= false;
		for(int i= 0; i< json.length(); i++) {
			char ch= json.charAt(i);
			boolean isEnd= ch== '}' || ch== ']';//(})(])之前應換行，且換行后縮進應比上一行減少一個縮進長度
			boolean isBegin= ch== '{' || ch== '[';//({)([)之後應換行，且換行后縮進應比上一行增加一個縮進長度
			boolean isComma= ch== ',';//(,)的後面應換行
			
			if(isEnd) {//遇到(})(])時，應在其前一位添加換行
				indent-= 2;
//				System.out.println();
				buff.append(System.lineSeparator());
				lastLineBreak= true;
			}
			if(lastLineBreak) {//若開始新行，打印縮進
				if(ch==' ') {
					continue;//跳過空格
				}
				for(int j= 0; j< indent; ++j) {
//					System.out.print(" ");
					buff.append(" ");
				}
			}
			if(isBegin) {//遇到({)([)(,)時，下一行的縮進應增加一次
				indent+= 2;
			}
			lastLineBreak= isBegin || isComma;//遇到({)([)(,)時，應緊跟其後添加換行
//			System.out.print(ch);
			buff.append(ch);
			if(lastLineBreak) {
//				System.out.println();
				buff.append(System.lineSeparator());
			}
		}
		return buff.toString();
	}
}

package com.foxconn.pdss.devdept.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class XmlValidator {
	
	public static boolean isXml(String input) {
		try {
			DocumentHelper.parseText(input);
		} catch (DocumentException e) {
			return false;
		}
		return true;
	}
	
	public static String format(String xml) {
		Document xmlDoc= null;
		try {
			xmlDoc= (Document) DocumentHelper.parseText(xml);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		OutputFormat format= OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer= null;
		StringWriter sw= null;
		try {
			sw= new StringWriter();
			writer= new XMLWriter(sw , format);
			writer.write(xmlDoc);
			writer.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(writer!= null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		xml= sw.toString().substring(40);
		return xml;
	}
	
}

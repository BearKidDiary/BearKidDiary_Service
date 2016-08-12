package com.bearkiddiary.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ParameterDecode {
	public static String decode(String value){
		String value_decode = null;
		try {
			value_decode = new String(value.getBytes("iso-8859-1"), "UTF-8");
			value_decode = URLDecoder.decode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value_decode;
	}
}

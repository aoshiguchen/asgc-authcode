package com.github.aoshiguchen.framework.authcode;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class Test2 {

	public static void main(String[] args) {
		String str = null;
		 
	       int hightPos, lowPos; // 定义高低位
	 
	       Random random = new Random();
	 
	       hightPos = (176 + Math.abs(random.nextInt(39)));//获取高位值
	 
	       lowPos = (161 + Math.abs(random.nextInt(93)));//获取低位值
	 
	       byte[] b = new byte[2];
	 
	       b[0] = (new Integer(hightPos).byteValue());
	 
	       b[1] = (new Integer(lowPos).byteValue());
	 
	       try {
			str = new String(b, "GBk");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//转成中文
	 
	       System.err.println(str);

	}
	
}

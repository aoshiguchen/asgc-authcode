package com.github.aoshiguchen.framework.authcode;

import java.io.File;
import java.io.IOException;

public class Test3 {

	public static void main(String[] args) {
		CharNumAuthCode charNumAuthCode=new CharNumAuthCode();
		ChineseAuthCode chineseAuthCode=new ChineseAuthCode();
		for(int i=1;i<=50;i++){
			try {
				charNumAuthCode.write("PNG",new File("files/a/"+i+".png"));
				chineseAuthCode.write("PNG",new File("files/b/"+i+".png"));
				charNumAuthCode.next();
				chineseAuthCode.next();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

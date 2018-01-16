package com.github.aoshiguchen.framework.authcode;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		CharNumAuthCode numAuthCode=new CharNumAuthCode(6);
		System.out.println(numAuthCode.getRandomString());
		Scanner in=new Scanner(System.in);
		String str=in.next();
		System.out.println(numAuthCode.contrast(str));
	}
}
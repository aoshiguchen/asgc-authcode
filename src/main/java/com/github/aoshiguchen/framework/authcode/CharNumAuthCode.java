package com.github.aoshiguchen.framework.authcode;

import java.util.Random;

/**
 * 字符数字验证码
 * @author 傲世孤尘
 *
 */
public final class CharNumAuthCode extends AbstractAuthCode{
	/**
	 * 构造方法1
	 */
	public CharNumAuthCode(){
		this.init(defaultWidth,defaultHeight,defaultStringLength);
	}
	/**
	 * 构造方法2
	 * @param stringLength 验证码内容长度
	 */
	public CharNumAuthCode(int stringLength){
		this.init(defaultWidth,defaultHeight,stringLength);
	}
	/**
	 * 构造方法3
	 * @param width 验证码宽度
	 * @param height 验证码高度
	 */
	public CharNumAuthCode(int width,int height){
		this.init(width, height,defaultStringLength);
	}
	/**
	 * 构造方法4
	 * @param width 验证码宽度
	 * @param height 验证码高度
	 * @param stringLength 验证码内容长度
	 */
	public CharNumAuthCode(int width,int height,int stringLength){
		this.init(width, height, stringLength);
	}
	@Override
	public String getRandomString() {
		// TODO Auto-generated method stub
		int range=62;
		String str="";
		Random random=new Random();
		for(int i=1;i<=this.stringLength;i++){
			int t=random.nextInt(range);
			if(t<=9)str+=t;
			else if(t<=35)str+=(char)(t+55);
			else str+=(char)(t+61);
		}
		this.content=str;
		return str;
	}
}


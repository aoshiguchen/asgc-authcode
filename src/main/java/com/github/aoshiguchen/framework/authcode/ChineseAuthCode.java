package com.github.aoshiguchen.framework.authcode;

import java.awt.Font;
import java.io.UnsupportedEncodingException;
import java.util.Random;


public final class ChineseAuthCode extends AbstractAuthCode{
	/**
	 * 构造方法1
	 */
	public ChineseAuthCode(){
		this.init(defaultWidth,defaultHeight,defaultStringLength);
	}
	/**
	 * 构造方法2
	 * @param stringLength 验证码内容长度
	 */
	public ChineseAuthCode(int stringLength){
		this.init(defaultWidth,defaultHeight,stringLength);
	}
	/**
	 * 构造方法3
	 * @param width 验证码宽度
	 * @param height 验证码高度
	 */
	public ChineseAuthCode(int width,int height){
		this.init(width, height,defaultStringLength);
	}
	/**
	 * 构造方法4
	 * @param width 验证码宽度
	 * @param height 验证码高度
	 * @param stringLength 验证码内容长度
	 */
	public ChineseAuthCode(int width,int height,int stringLength){
		this.init(width, height, stringLength);
	}
	public void init(int width, int height,int stringLength) {
		// TODO Auto-generated method stub
		this.width=width;
		this.height=height;
		this.stringLength=stringLength;
		this.disturbLineWeight=defaultDisturbLineWeight;
		this.disturbLineFc=defaultDisturbLineFc;
		this.disturbLineBc=defaultDisturbLineBc;
		this.disturbLineCount=100;
		this.maxGradient=defaultMaxGradient;
		this.topPadding=defaultTopPadding;
		this.leftPadding=defaultLeftPadding;
		this.spaceBetween=defaultSpaceBetween;
		this.font=new Font("宋体",Font.BOLD,20);
		this.fontColorMinValue=defaultFontColorMinValue;
		this.fontColorMaxValue=defaultFontColorMaxValue;
		this.threadingMaxCount=0;
		this.threadingWeight=defaultThreadingWeight;
		this.threadingFc=defaultDisturbLineFc;
		this.threadingBc=defaultDisturbLineBc;
		
		this.draw(this.getRandomString());
	}
	@Override
	public String getRandomString() {
		// TODO Auto-generated method stub
		String str="";
		 int hightPos, lowPos; // 定义高低位
		 Random random = new Random();
		for(int i=1;i<=this.stringLength;i++){
			 hightPos = (176 + Math.abs(random.nextInt(39)));//获取高位值
		     lowPos = (161 + Math.abs(random.nextInt(93)));//获取低位值
		     byte[] b = new byte[2];
		     b[0] = (new Integer(hightPos).byteValue());
		     b[1] = (new Integer(lowPos).byteValue());
		     try {
				str+=new String(b, "GBk");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.content=str;
		return str;
	}
}

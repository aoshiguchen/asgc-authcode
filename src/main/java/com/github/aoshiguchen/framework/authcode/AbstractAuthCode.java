package com.github.aoshiguchen.framework.authcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

/**
 * 字符数字验证码
 * @author 傲世孤尘
 *
 */
public abstract class AbstractAuthCode implements IAuthCode{
	/**
	 * 验证码内容
	 */
	String content;
	/**
	 * 验证码图片
	 */
	BufferedImage image;
	/**
	 * 验证码内容长度
	 */
	int stringLength;
	/**
	 * 验证码图片宽度
	 */
	int width;
	/**
	 * 验证码图片高度
	 */
	int height;
	/**
	 * 干扰线权重，权重越大，则干扰线越长
	 */
	int disturbLineWeight;
	/**
	 * 第一个影响干扰线颜色的因子，范围在0到255之间
	 */
	int disturbLineFc;
	/**
	 * 第二个影响干扰线颜色的因子，范围在0到255之间
	 */
	int disturbLineBc;
	/**
	 * 干扰线数目
	 */
	int disturbLineCount;
	/**
	  * 字符最大倾斜度
	  */
	int maxGradient;
	/**
	 * 字符上边距
	 */
	int topPadding;
	/**
	 * 字符左边距
	 */
	int leftPadding;
	/**
	 * 字符间距
	 */
	int spaceBetween;
	/**
	 * 验证码内容使用的字体
	 */
	Font font;
	/**
	 * 第一个影响字体颜色的因子
	 */
	int fontColorMinValue;
	/**
	 * 第二个影响字体颜色的因子
	 */
	int fontColorMaxValue;
	/**
	 * 中穿线最大条数
	 */
	int threadingMaxCount;
	/**
	 * 中穿线权重
	 */
	int threadingWeight;
	/**
	 * 第一个影响中穿线颜色的因子
	 */
	int threadingFc;
	/**
	 * 第二个影响中穿线颜色的因子
	 */
	int threadingBc;
	
	@Override
	public void init(int width, int height,int stringLength) {
		// TODO Auto-generated method stub
		this.width=width;
		this.height=height;
		this.stringLength=stringLength;
		this.disturbLineWeight=defaultDisturbLineWeight;
		this.disturbLineFc=defaultDisturbLineFc;
		this.disturbLineBc=defaultDisturbLineBc;
		this.disturbLineCount=defaultDisturbLineCount;
		this.maxGradient=defaultMaxGradient;
		this.topPadding=defaultTopPadding;
		this.leftPadding=defaultLeftPadding;
		this.spaceBetween=defaultSpaceBetween;
		this.font=defaultFont;
		this.fontColorMinValue=defaultFontColorMinValue;
		this.fontColorMaxValue=defaultFontColorMaxValue;
		this.threadingMaxCount=defaultThreadingMaxCount;
		this.threadingWeight=defaultThreadingWeight;
		this.threadingFc=defaultDisturbLineFc;
		this.threadingBc=defaultDisturbLineBc;
		
		this.draw(this.getRandomString());
	}
	
	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return this.image;
	}

	@Override
	public BufferedImage next() {
		// TODO Auto-generated method stub
		BufferedImage image=this.image;
		this.draw(this.getRandomString());
		return image;
	}

	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		return this.content;
	}

	@Override
	public boolean contrast(String content) {
		// TODO Auto-generated method stub
		return this.content.equalsIgnoreCase(content);
	}

	@Override
	public abstract String getRandomString();

	@Override
	public BufferedImage draw(String content) {
		// TODO Auto-generated method stub
		BufferedImage image=new BufferedImage(this.width,this.height, BufferedImage.TYPE_INT_RGB);
		Graphics g=image.getGraphics();
		drawBackground(g);
		drawString((Graphics2D)g);
		drawThreading(g);
		this.image=image;
		return image;
	}
	/**
	 * 画出验证码的背景
	 * @param g
	 */
	public void drawBackground(Graphics g) {
		Random random=new Random();
		g.setColor(Color.white);
		g.fillRect(0, 0,this.width,this.height);
		drawLine(g);
	}
	/**
	 * 画出中穿线
	 * @param g
	 */
	public void drawThreading(Graphics g){
		Random random=new Random();
		int count=random.nextInt(this.threadingMaxCount+1);
		for(int i=0;i<count;i++){
			g.setColor(getRandColor(this.threadingFc,this.disturbLineBc));
			int x1=random.nextInt(this.width/3);
			int y1=random.nextInt(this.height);
			int x2=x1+random.nextInt(this.threadingWeight+10);
			int y2;
			if(y1<=this.height/2){
				y2=y1+random.nextInt(this.height/2);
			}else{
				y2=y1-random.nextInt(this.height/2);
			}
			g.drawLine(x1, y1, x2, y2);
		}
	}
	/**
	 * 画出验证码内容
	 * @param g
	 */
	public void drawString(Graphics2D g){
		Random random=new Random();
		int x=this.leftPadding;
		int y=this.topPadding;
		g.setFont(this.font);
		g.setColor(new Color(this.fontColorMinValue+random.nextInt(this.fontColorMaxValue),this.fontColorMinValue+random.nextInt(this.fontColorMaxValue),this.fontColorMinValue+random.nextInt(this.fontColorMaxValue)));
        for(int i=0;i<this.stringLength;i++){ 
        	int dregee=new Random().nextInt()%this.maxGradient;
        	 g.rotate(dregee*Math.PI/180, x, y);
        	 g.drawString(this.content.charAt(i)+"",x,y);
        	 g.rotate(-dregee*Math.PI/180,x,y);
        	 x+=this.spaceBetween;
        }
	}
	/**
	 * 画干扰线
	 * @param g
	 */
	public void drawLine(Graphics g){
		Random random = new Random();
		for (int i=0;i<this.disturbLineCount;i++){
			int x = random.nextInt(this.width);
			int y = random.nextInt(this.height);
		        int xl = random.nextInt(this.disturbLineWeight);
		        int yl = random.nextInt(this.defaultDisturbLineWeight);
		    g.setColor(getRandColor(this.disturbLineFc,this.disturbLineBc));
			g.drawLine(x,y,x+xl,y+yl);
		}
	}
	/**
	 * 产生给定范围内的随机颜色
	 * @param fc
	 * @param bc
	 * @return
	 */
	public Color getRandColor(int fc,int bc){//给定范围获得随机颜色
        Random random = new Random();
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        int r=fc+random.nextInt(bc-fc);
        int g=fc+random.nextInt(bc-fc);
        int b=fc+random.nextInt(bc-fc);
        return new Color(r,g,b);
     }

	@Override
	public boolean write(String formatName, OutputStream output) throws IOException{
		// TODO Auto-generated method stub
		return ImageIO.write(this.image, formatName, output);
	}

	@Override
	public boolean write(String formatName, File output) throws IOException {
		// TODO Auto-generated method stub
		return ImageIO.write(this.image, formatName, output);
	}

	@Override
	public boolean write(String formatName, ImageOutputStream output) throws IOException {
		// TODO Auto-generated method stub
		return ImageIO.write(this.image, formatName, output);
	}
}

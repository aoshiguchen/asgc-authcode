package com.github.aoshiguchen.framework.authcode;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.stream.ImageOutputStream;


/**
 * 验证码接口
 * @author 傲世孤尘
 *
 */
public interface IAuthCode {
	/**
	 * 默认的字符串长度
	 */
	public static final int defaultStringLength = 4;
	/**
	 * 默认的验证码宽度
	 */
	public static final int defaultWidth = 120;
	/**
	 * 默认的验证码高度
	 */
	public static final int defaultHeight = 40;
	/**
	 * 默认的干扰线权重，权重越大，则干扰线越长
	 */
	public static final int defaultDisturbLineWeight = 3;
	/**
	 * 第一个影响干扰线颜色的因子，范围在0到255之间
	 */
	public static final int defaultDisturbLineFc = 50;
	/**
	 * 第二个影响干扰线颜色的因子，范围在0到255之间
	 */
	public static final int defaultDisturbLineBc = 230;
	/**
	 * 默认的干扰线数目
	 */
	public static final int defaultDisturbLineCount = 150;
	 /**
	  * 默认的字符最大倾斜度
	  */
	public static final int defaultMaxGradient = 35;
	/**
	 * 默认的字符上边距
	 */
	public static final int defaultTopPadding = 30;
	/**
	 * 默认的字符左边距
	 */
	public static final int defaultLeftPadding = 20;
	/**
	 * 默认的字符间距
	 */
	public static final int defaultSpaceBetween = 25;
	/**
	 * 默认的字体
	 */
	public static final Font defaultFont =new Font("宋体",Font.BOLD,28);
	/**
	 * 第一个影响字体颜色的因子
	 */
	public static final int defaultFontColorMinValue = 50;
	/**
	 * 第二个影响字体颜色的因子
	 */
	public static final int defaultFontColorMaxValue = 150;
	/**
	 * 默认的中穿线最大条数
	 */
	public static final int defaultThreadingMaxCount=3;
	/**
	 * 默认的中穿线权重
	 */
	public static final int defaultThreadingWeight=80;
	/**
	 * 第一个影响中穿线颜色的因子
	 */
	public static final int defaultThreadingFc=20;
	/**
	 * 第二个影响中穿线颜色的因子
	 */
	public static final int defaultThreadingBc=230;
	/**
	 * 验证码的初始化方法,并产生随机禅定第一张验证码
	 * @param width 验证码宽度
	 * @param height 验证码高度
	 * @param stringLength 验证码字符串长度
	 */
	public void init(int width,int height,int stringLength);
	/**
	 * 获取一张验证码图片
	 * @return 验证码图片
	 */
	public BufferedImage getImage();
	/**
	 * 获取当前验证码图片，并且自动产生下一张验证码，更新成员中的验证码图片
	 * @return 当前验证码图片
	 */
	public BufferedImage next();
	/**
	 * 获取验证码的字符串内容
	 * @return 当前验证码内容
	 */
	public String getContent();
	/**
	 * 比较指定字符串是否与验证码内容一致，忽略大小写差异
	 * @param content 要判断的字符串
	 * @return 是否一致
	 */
	public boolean contrast(String content);
	/**
	 * 或去一个随机字符串，字符个数由参数指定，若没有指定，则使用默认字符串长度，所有字符为数字和字母的组合
	 * @return 产生的字符串
	 */
	public String getRandomString();
	/**
	 * 根据字符串内容，画出验证码图片，并更新成员中的验证码图片
	 * @param content 要画的字符串内容
	 * @return 当前画出的验证码图片
	 */
	public BufferedImage draw(String content);
	/**
	 * 将当前的验证码图片以指定格式写入到指定文件
	 * @param formatName 图片格式
	 * @param output 要写入的文件
	 * @return 操作成功与否
	 */
	public boolean write(String formatName,File output)throws IOException;
	/**
	 * 使用支持给定格式的任意 ImageWriter 将验证码图片写入 ImageOutputStream。
	 * 从当前流指针开始将图像写入 ImageOutputStream，并重写该点之后的现有流数据，如果有的话。
	 * @param formatName 图片格式
	 * @param output 指定的输出流
	 * @return 操作成功与否
	 */
	public boolean write(String formatName,ImageOutputStream output)throws IOException;
	/**
	 * 使用支持给定格式的任意 ImageWriter 将验证码图片写入 OutputStream。 
	 * @param formatName 图片格式
	 * @param output 指定的输出流
	 * @return 操作成功与否
	 */
	public boolean write(String formatName,OutputStream output) throws IOException;
}

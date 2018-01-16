package com.github.aoshiguchen.framework.authcode;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MyFrame extends JFrame{
	//字母数字验证码
	public static final int CHAR_NUM_AUTHCODE = 1;
	//汉子验证码
	public static final int CHINESE_AUTHCODE = 2;
	
	//当前测试的验证码类型
	public static final int AUTHCODE_TYPE = CHAR_NUM_AUTHCODE;
	
	public static void main(String[] args) {
		MyFrame myFrame=new MyFrame();
		myFrame.setVisible(true);
	}
	
	public MyFrame(){
		super("MyFrame");
		this.setSize(300,200);
		Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
		int x=(dim.width-this.getWidth())/2;
		int y=(dim.height-this.getHeight())/2;
		this.setLocation(x,y);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		JLabel label=new JLabel("aaa",JLabel.CENTER);
		label.setSize(120,40);

		this.add(label,BorderLayout.NORTH);
		
		IAuthCode auth = getAuthCode(label.getWidth(),label.getHeight());
		System.out.println(auth.getContent());
		try {
			auth.write("PNG",new File("files/" + auth.getContent() + ".PNG"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		label.setIcon(new ImageIcon(auth.getImage()));
		
		label.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				/*CharNumAuthCode numAuthCode=new CharNumAuthCode(((JLabel)e.getSource()).getWidth(),((JLabel)e.getSource()).getHeight());
				System.out.println(numAuthCode.getContent());
				try {
					numAuthCode.write("PNG",new File("C:\\Users\\Administrator\\Desktop\\123.png"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				((JLabel)e.getSource()).setIcon(new ImageIcon(numAuthCode.getImage()));*/
				
				
				IAuthCode auth = getAuthCode(((JLabel)e.getSource()).getWidth(),((JLabel)e.getSource()).getHeight());
				System.out.println(auth.getContent());
				try {
					auth.write("PNG",new File("files/" + auth.getContent() + ".PNG"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				((JLabel)e.getSource()).setIcon(new ImageIcon(auth.getImage()));
			}
		});
	}
	
	public static IAuthCode getAuthCode(int width,int height){
		if(AUTHCODE_TYPE == CHAR_NUM_AUTHCODE){
			return new CharNumAuthCode(width,height);
		}else if(AUTHCODE_TYPE == CHINESE_AUTHCODE){
			return new ChineseAuthCode(width,height);
		}
		
		return null;
	}
}

package com.deskapp;
import javax.swing.*;
import java.awt.*;
    
public class TimCap extends JFrame
{    
	/**
	 * 
	 */
	private static final long serialVersionUID = -5826574149311471875L;
	JProgressBar jb;
	JLabel lbl;
	JLabel bgimg;
	int i=0,num=0;     
																		//Constructor
	TimCap()
	{    
		bgimg = new JLabel(new ImageIcon("img/load1.jpg"));
		add(bgimg);
		bgimg.setLayout(null);
		jb=new JProgressBar(0,500);    
		jb.setBounds(240,250,300,40);         
		jb.setValue(0);    
		jb.setStringPainted(true);
		lbl=new JLabel("Loading your capsule...Please Wait!");
		lbl.setForeground(new Color(0,0,0));
		lbl.setFont(new Font("Lucida Handwriting",Font.BOLD,22));
		lbl.setBounds(160,200,600,50);
		bgimg.add(lbl);
		bgimg.add(jb);    
		setBounds(480,250,800,500);
		setTitle("Time Capsule v2.0");
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(0);
	}
																		//Progressing Progress Bar
	public void iterate()
	{    
		while(i<=600)
		{    
			jb.setValue(i);    
			i=i+30;
			try
			{
				Thread.sleep(150);
				if(i==600)
					break;
			}catch(Exception e){}  
		}
		
		dispose();
		new Login();													//Calling Login Screen
		
	}    
	public static void main(String args[]) 
	{    
		TimCap m=new TimCap();    										//Main
		m.setVisible(true);    
		m.iterate();
	}    
}  
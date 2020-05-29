package com.deskapp;
import javax.swing.*;
import java.awt.*;

class Info extends JFrame
{
  /**
	 * 
	 */
	private static final long serialVersionUID = -2564296586240560356L;
Container container = getContentPane();
  public Info()																					//INFO CLASS
  {
	  	setTitle("Credits");
		setIconImage(new ImageIcon("img/signup.png").getImage());
		setVisible(true);
		setBounds(360,260,870,450);
		setResizable(false);
		
	  JLabel bgimg = new JLabel(new ImageIcon("img/info.jpg"));
	  JLabel develop = new JLabel("Developers");
	  JLabel admin1 = new JLabel("PremKumar A");
	  JLabel admin2 = new JLabel("Asif Ahamed A");
	  JLabel admin3 = new JLabel("Abishek Adapa");
	  
	  develop.setForeground(new Color(255,255,255));			 admin1.setForeground(new Color(255,255,255));		//COLOR FIXATION
	  admin2.setForeground(new Color(255,255,255));				 admin3.setForeground(new Color(255,255,255));
	  
	  develop.setFont(new Font("Countryside",Font.BOLD,40));	 admin1.setFont(new Font("Times New Roman",Font.BOLD,25)); 
	  admin2.setFont(new Font("Times New Roman",Font.BOLD,25));	 admin3.setFont(new Font("Times New Roman",Font.BOLD,25));
	  
	  develop.setBounds(290,25,400,200);						 admin1.setBounds(320,100,400,200);
	  admin2.setBounds(320,150,400,200);						 admin3.setBounds(320,210,400,200);
	  
	  container.add(bgimg);
	  bgimg.setLayout(null);
	  bgimg.add(develop);	bgimg.add(admin1);
	  bgimg.add(admin2);	bgimg.add(admin3);  
  }
}
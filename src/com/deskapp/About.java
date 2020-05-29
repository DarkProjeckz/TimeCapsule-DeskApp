package com.deskapp;
import javax.swing.*;
import java.awt.*;
class About extends JFrame															//ABOUT CLASSS
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 4166625930643184050L;
Container container = getContentPane();
  static String uname="",dob="",gender="",passwd="",email="",phno="";
	public static void giveid()
	{uname=Mainscreen.getid();}
	public static void gived()
	{	phno=Mainscreen.getd();}
	public static void givez()
	{passwd=Mainscreen.getz();}
	public static void givex()
	{dob=Mainscreen.getx();}
	public static void giveb()
	{gender=Mainscreen.getb();}
	public static void givec()
	{email=Mainscreen.getc();}
	public About()
	{																			//CONSTRUCTOR
		setTitle("Details");
		setIconImage(new ImageIcon("img/signup.png").getImage());
		setVisible(true);
		setBounds(360,150,880,580);
		setResizable(false);
		Container cont = getContentPane();
		JLabel bgimg = new JLabel(new ImageIcon("img/info.jpg"));
		JLabel develop = new JLabel("About");
		JLabel admin11 = new JLabel("Name     		    : "+uname);
		//JLabel admin22 = new JLabel("Password  : "+passwd);
		JLabel admin33 = new JLabel("D.O.B    		    : "+dob);	
		JLabel admin44 = new JLabel("Email id    : "+email);
		JLabel admin55 = new JLabel("Gender      : "+gender);
		JLabel admin66 = new JLabel("Phone no   : "+phno);
		
		develop.setForeground(new Color(255,255,255));		admin11.setForeground(new Color(255,255,255));		//COLOR FIXATION
		/*admin22.setForeground(new Color(255,255,255));	*/	admin33.setForeground(new Color(255,255,255));
		admin44.setForeground(new Color(255,255,255));		admin55.setForeground(new Color(255,255,255));
		admin66.setForeground(new Color(255,255,255));
					
		develop.setFont(new Font("Countryside",Font.BOLD,40));		admin11.setFont(new Font("Times New Roman",Font.BOLD,25));		//CHANGING FONT STYLE
		/*admin22.setFont(new Font("Times New Roman",Font.BOLD,25));*/	admin33.setFont(new Font("Times New Roman",Font.BOLD,25));
		admin44.setFont(new Font("Times New Roman",Font.BOLD,25));	admin55.setFont(new Font("Times New Roman",Font.BOLD,25));
		admin66.setFont(new Font("Times New Roman",Font.BOLD,25));
		
		develop.setBounds(350,25,400,200);		/*admin11.setBounds(300,100,400,200); */
		admin11.setBounds(300,150,400,200);  	admin33.setBounds(300,200,400,200);
		admin44.setBounds(300,250,600,200);		admin55.setBounds(300,300,400,200);
		admin66.setBounds(300,350,400,200);	
	
		cont.add(bgimg);
		bgimg.setLayout(null);
		bgimg.add(develop);
		bgimg.add(admin11);		/*bgimg.add(admin22);*/
		bgimg.add(admin33); 	bgimg.add(admin44);
		bgimg.add(admin55);		bgimg.add(admin66);
	}
}
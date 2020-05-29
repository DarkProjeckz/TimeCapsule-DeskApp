package com.deskapp;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;																			//Login Class
import java.util.Properties;
class Login extends JFrame implements ActionListener
{
	static final long serialVersionUID = -7462331679744098306L;
																			//CREATING OBJECTS
	Container container=getContentPane();
	JLabel username = new JLabel("USERNAME  :");
	JLabel password = new JLabel("PASSWORD  :");
	JTextField user = new JTextField();
	JPasswordField pass = new JPasswordField();
	JButton login = new JButton("LOGIN");
	JButton reset = new JButton("RESET");
	JCheckBox box = new JCheckBox();
	JLabel show = new JLabel(" Show");
	JButton signup = new JButton("SIGNUP");
	JButton forgetp = new JButton("Forgot Password ?");
	JLabel bgimg = new JLabel(new ImageIcon("img/img.jpg"));
	
	static String userid;													//Sending variable to another class
	static int id;
	public static String getid(){return userid;}
	public static int get_uid(){return id;}
		
																			//CONSTRUCTOR
	public Login()
	{
		setitems();	
	}
	
	public void setitems()													//SETTING THE COMPONENTS IN THE CONTAINER
	{
		setIconImage(new ImageIcon("img/user.png").getImage());
		setTitle("Login");
		setVisible(true);
		setBounds(700,250,375,350);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		username.setBounds(40,40,130,50);	password.setBounds(40,100,130,50);	user.setBounds(160,50,150,30);
		pass.setBounds(160,110,150,30);		login.setBounds(50,200,100,30);		signup.setBounds(180,200,100,30);
		box.setBounds(165,140,20,40);		show.setBounds(180,120,150,80);		reset.setBounds(120,250,100,30);
		forgetp.setBounds(155,170,150,25);
		
		login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));  	//HAND_CURSOR SETTINGS
		reset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		box.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
																			//BACKGROUND COLOR
		username.setForeground(new Color(255,255,255));		password.setForeground(new Color(255,255,255));
		show.setForeground(new Color(255,255,255));			login.setBackground(new Color(31, 58, 147));
		login.setForeground(new Color(255,255,255));		reset.setBackground(new Color(31, 58, 147));
		reset.setForeground(new Color(255,255,255));		signup.setBackground(new Color(31, 58, 147));
		signup.setForeground(new Color(255,255,255));		forgetp.setForeground(new Color(255,255,255));	
		
		box.setBorder(null);
		box.setContentAreaFilled(false);
		
		username.setFont(new Font("Lucida Handwriting",Font.BOLD,14));		//DEFINING FONT
		password.setFont(new Font("Lucida Handwriting",Font.BOLD,14));
		login.setFont(new Font("Segoe Print",Font.BOLD,14));
		signup.setFont(new Font("Segoe Print",Font.BOLD,14));
		reset.setFont(new Font("Segoe Print",Font.BOLD,14));
		forgetp.setFont(new Font("TimesNewRoman",Font.BOLD,14));
		
		forgetp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));	
		forgetp.setBorder(null);
		forgetp.setContentAreaFilled(false);
		forgetp.setFocusPainted(false);
		
		container.add(bgimg);												//ADDING COMPONENTS					
		bgimg.setLayout(null);
		bgimg.add(username);	bgimg.add(password);	bgimg.add(user);
		bgimg.add(pass);		bgimg.add(login);		bgimg.add(reset);
		bgimg.add(box);			bgimg.add(show);		bgimg.add(signup);
		bgimg.add(forgetp);
		
		
		login.addActionListener(this);		reset.addActionListener(this);	//ADDING ACTIONLISTENER
		box.addActionListener(this);		signup.addActionListener(this);
		forgetp.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e)								//ADDING ACTION LISTENER
	{
		char[] pwdcheck;
		if(e.getSource()==login)											//LOGIN BUTTON
		{
			userid = user.getText();
			Mainscreen.giveid();
			pass.setEchoChar('*');
			pwdcheck = pass.getPassword();
			String pwd = String.valueOf(pwdcheck);
			String cname,cpass;
			int cc1=0,cc2=0;
			
			try {															//JDBC CONNECTION
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Login connection successful");
				try {
						
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/timecapsule","root","");
						Statement state=con.createStatement();
						ResultSet rs1 = state.executeQuery("SELECT ID FROM USERS WHERE UNAME='"+userid+"'");
						if(rs1.next())
						{
							id=rs1.getInt("ID");
							System.out.println(id);
							Mainscreen.give_uid();
						}
						ResultSet rs = state.executeQuery("SELECT uname,passwd FROM USERS");
						while(rs.next())
						{
							cname=rs.getString(1);
							cpass=rs.getString(2);
							if((userid.equals(cname)))
							{
								cc1=1;
								if(pwd.equals(cpass))
								{
									cc2=1;
									dispose();
									new Mainscreen();
									break;
								}		
							}						
						}
						if((cc1==1) && (cc2==0))
							JOptionPane.showMessageDialog(this,"Invalid Password !");
						
						else if((userid.equals(""))&&(pwd.equals("")))
							JOptionPane.showMessageDialog(this,"Enter Username and Password !");
						else if((cc1==0) && (cc2==0))
							JOptionPane.showMessageDialog(this,"Invalid Username and Password !");	
						con.close();
					}
					catch(Exception e1) {System.out.println("In login "+e1);}
				}
				catch(Exception e2) {System.out.println("In login "+e2);}
			
		}
		if(e.getSource()==reset)											//RESET BUTTON
		{
			user.setText("");	pass.setText("");
		}
		if(e.getSource()==box)												//SHOW PASSWORD CHECKBOX
		{
			if(box.isSelected())
				pass.setEchoChar((char) 0);
			else
				pass.setEchoChar('*');
		}
		if(e.getSource()==signup)											//SIGNUP BUTTON
		{
			dispose();
			new Signup();													//CALLING SIGNUP CLASS
		}
		if(e.getSource()==forgetp)
		{
			JOptionPane.showMessageDialog(this,"Forgot password is temporarily disabled. You can enable by modifying the source code");
		}
		//Forgot password is temporarily disabled. If you wish to add this feature, uncomment below lines
		//and provide your mail details (mail id, password) in the sections whereever needed below
		// Also do the same in signup file if you wish to send mail at the time of registration
		
		/*if(e.getSource()==forgetp)											//SIGNUP BUTTON
		{
			String funame,cfuname,toMail = null,topass=null;
			int nflag=0;
			
			funame=JOptionPane.showInputDialog("Enter your username. We'll send password to your mail");												//CALLING SIGNUP CLASS
			System.out.println(funame);
			
			try {															//JDBC CONNECTION
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Login connection successful");
				try {
						
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/timecapsule","root","");
						Statement state=con.createStatement();
						ResultSet rs = state.executeQuery("SELECT uname FROM USERS");
						while(rs.next())
						{
							cfuname=rs.getString("uname");
							
							if((funame.equals(cfuname)))
							{
									System.out.println("Found");
									int input = JOptionPane.showConfirmDialog(null, "Confirm sending password ?"); // 0=yes, 1=no, 2=cancel
									if(input==0)
									{
										ResultSet rs1 = state.executeQuery("SELECT email,passwd FROM USERS where UNAME='"+funame+"'");
										while(rs1.next())
										{
											toMail = rs1.getString("email");
										    topass = rs1.getString("passwd");;
										}
					/////////////////////////////////MAIL THROUGH SMTP///////////////////////////////////
											final String username = "<your mail id>"; // enter your mail id
									        final String password = "<your mail password>";// enter ur password

									        Properties props = new Properties();
									        props.put("mail.smtp.auth", "true");
									        props.put("mail.smtp.starttls.enable", "true");
									        props.put("mail.smtp.host", "smtp.gmail.com");
									        props.put("mail.smtp.port", "587");

									        Session session = Session.getInstance(props,
									                new javax.mail.Authenticator() {
									                    protected PasswordAuthentication getPasswordAuthentication() {
									                        return new PasswordAuthentication(username, password);
									                    }
									                });

									        try {

									            Message message = new MimeMessage(session);
									            message.setFrom(new InternetAddress("<your mail id>")); // same email id
									            message.setRecipients(Message.RecipientType.TO,
									                    InternetAddress.parse(toMail));// whome u have to send mails that person id
									            message.setSubject("Accepting password request");
									            message.setText("Dear Capsule user,"
									                    + "\n\n You have requested to get your password."
									                    + "\n\n Your capsule password is -> '"+topass+"'"
									                    + "\n\n Stay connected with us !!"
									                    + "\n\n\n\n With regards,\n Time Capsule Team.");

									            Transport.send(message);

									            System.out.println("Done");

									        } catch (MessagingException e1) {
									            throw new RuntimeException(e1);
									        }
					 /////////////////////////////////MAIL THROUGH SMTP//////////////////////////////////	
								
										
										JOptionPane.showMessageDialog(this,"Check your mail");
									}
									nflag++;
									break;
							}	
						}
						if(nflag==0)
							JOptionPane.showMessageDialog(this,"Username not registered");
						con.close();
					}
					catch(Exception e1) {System.out.println("In login (forget pass 1)"+e1); 
										 JOptionPane.showMessageDialog(this,"Connection Error");}
				}
				catch(Exception e2) {System.out.println("In login (forget pass 2)"+e2);}
		}*/
	}
}
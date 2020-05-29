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
import java.sql.*;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;

import java.text.ParseException;

class Signup extends JFrame implements ActionListener						//SIGNUP CLASS
{
	/**
	 * 
	 */
	static String dbuser,dbpass,dbsid;
	private static final long serialVersionUID = 2971170401672114212L;
	Container container = getContentPane();									//DECLARING THE VARIABLES
	JLabel username2 = new JLabel("USERNAME                  :");
	JLabel validuser = new JLabel();
	JLabel password2 = new JLabel("PASSWORD                  :");
	JLabel validpass = new JLabel();
	JTextField user = new JTextField();
	JPasswordField pass = new JPasswordField();
	JLabel confirmpass = new JLabel("CONFIRM PASSWORD :");
	JLabel validconfirmpass = new JLabel();
	JPasswordField confmpass = new JPasswordField();
	JLabel email = new JLabel("E-MAIL ID                  :");
	JLabel validemail = new JLabel();
	JTextField emailbox = new JTextField();
	JLabel dob = new JLabel("DATE OF BIRTH         :");
	JLabel validdob = new JLabel();
	JTextField dobbox = new JTextField();
	JRadioButton male = new JRadioButton("   MALE");
	JRadioButton female = new JRadioButton("   FEMALE");
	JRadioButton others = new JRadioButton("   OTHERS");
	ButtonGroup group = new ButtonGroup();
	JLabel mobi = new JLabel("MOBILE NUMBER       :");
	JLabel validmobi = new JLabel();
	JTextField mobibox = new JTextField();
	JButton register = new JButton("SIGNUP");
	JButton login = new JButton("LOGIN");
	JLabel bgimg = new JLabel(new ImageIcon("img/img1.jpg"));
	
	
	public Signup()
	{
		createitems();														
	}
	public void createitems()
	{
		setTitle("Signup");
		setIconImage(new ImageIcon("img/signup.png").getImage());
		setVisible(true);
		setBounds(700,100,500,600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		container.add(bgimg);													//SETTING UP THE COMPONENTS
		username2.setBounds(50,40,250,50);		password2.setBounds(50,100,250,50);		user.setBounds(250,50,180,30);	
		pass.setBounds(250,110,180,30);			confirmpass.setBounds(50,165,250,50);	confmpass.setBounds(250,175,180,30);
		email.setBounds(50,250,250,30);			emailbox.setBounds(250,250,180,30);		dob.setBounds(50,325,250,30);
		validemail.setBounds(250,285,200,20);	validdob.setBounds(250,360,200,20);		validmobi.setBounds(250,430,200,20);
		dobbox.setBounds(250,325,180,30);		male.setBounds(50,450,100,30);			female.setBounds(190,450,100,30);
		others.setBounds(340,450,100,30);		mobi.setBounds(50,400,250,30);			mobibox.setBounds(250,400,180,30);
		register.setBounds(70,515,140,30);		validconfirmpass.setBounds(250,210,200,20);		validuser.setBounds(250,80,200,20);
		validpass.setBounds(250,150,200,20);	login.setBounds(260,515,140,30);	
		
		mobibox.setToolTipText("Only numbers");
		dobbox.setToolTipText("dd-mm-yyyy");
		confmpass.setToolTipText("Same as given in Password field");
		emailbox.setToolTipText("eg : user@gmail.com");
		
		username2.setFont(new Font("Lucida Handwriting",Font.PLAIN,15));		//CHANGING FONT STYLE
		password2.setFont(new Font("Lucida Handwriting",Font.PLAIN,15));
		confirmpass.setFont(new Font("Lucida Handwriting",Font.PLAIN,15));
		email.setFont(new Font("Lucida Handwriting",Font.PLAIN,15));
		dob.setFont(new Font("Lucida Handwriting",Font.PLAIN,15));
		male.setFont(new Font("Lucida Handwriting",Font.PLAIN,15));
		female.setFont(new Font("Lucida Handwriting",Font.PLAIN,15));
		others.setFont(new Font("Lucida Handwriting",Font.PLAIN,15));
		mobi.setFont(new Font("Lucida Handwriting",Font.PLAIN,15));
		register.setFont(new Font("Segoe Print",Font.BOLD,15));
		login.setFont(new Font("Segoe Print",Font.BOLD,15));
																				//COLOR FIXATION
		username2.setForeground(new Color(255,255,255));	password2.setForeground(new Color(255,255,255));
		confirmpass.setForeground(new Color(255,255,255));email.setForeground(new Color(255,255,255));		dob.setForeground(new Color(255,255,255));
		male.setBackground(new Color(36,37,42));		  male.setForeground(new Color(255,255,255));		female.setBackground(new Color(36,37,42));
		female.setForeground(new Color(255,255,255));	  others.setBackground(new Color(36,37,42));		others.setForeground(new Color(255,255,255));
		mobi.setForeground(new Color(255,255,255));		  register.setBackground(new Color(31, 58, 147));		register.setForeground(new Color(255,255,255));
		validemail.setForeground(new Color(255,255,255));	validdob.setForeground(new Color(255,255,255));	validmobi.setForeground(new Color(255,255,255));
		validconfirmpass.setForeground(new Color(255,255,255));		validuser.setForeground(new Color(255,255,255));	validpass.setForeground(new Color(255,255,255));
		login.setBackground(new Color(31, 58, 147));		login.setForeground(new Color(255,255,255));			
		
		
		group.add(male);		group.add(female);		group.add(others);		//GROUPING THEM TO RADIO BUTTONS
		
		male.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));			//SETTING UP TO HAND CURSOR
		female.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		others.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		bgimg.add(username2);	bgimg.add(password2);							//ADDING AND LIMITING THE TEXTFIELDS 
		male.setBorder(null);
		male.setContentAreaFilled(false);female.setBorder(null);
		female.setContentAreaFilled(false);others.setBorder(null);
		others.setContentAreaFilled(false);
		user.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e) { 
            if (user.getText().length() >= 10)									 // limit textfield to 10 characters
            e.consume(); 
			}
		});
		bgimg.add(user);		bgimg.add(pass);		bgimg.add(confirmpass);
		bgimg.add(confmpass);	bgimg.add(email);		bgimg.add(emailbox);	bgimg.add(dob);		bgimg.add(login);
		dobbox.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e) { 
            if (dobbox.getText().length() >= 10)
            e.consume(); 
			}
		});
		bgimg.add(validemail);	bgimg.add(validdob);	bgimg.add(validmobi);
		bgimg.add(dobbox);		bgimg.add(male);		bgimg.add(female);	bgimg.add(validpass);
		bgimg.add(others);		bgimg.add(mobi);		bgimg.add(validconfirmpass);	bgimg.add(validuser);
		mobibox.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e) { 
            if (mobibox.getText().length() >= 10) 
            e.consume(); 
			}
		});
		bgimg.add(mobibox);		bgimg.add(register);
		
		register.addActionListener(this);	male.addActionListener(this);	female.addActionListener(this);	others.addActionListener(this);
		login.addActionListener(this);
		
	}
	
	private Pattern regexPattern;
    private Matcher regMatcher;
    private Pattern regexPattern1;
    private Matcher regMatcher1;
    public boolean validateEmail(String emailAddress) {

        regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        regexPattern1 = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]+\\.[(a-zA-z)]{2,3}$");
        regMatcher   = regexPattern.matcher(emailAddress);
        regMatcher1   = regexPattern1.matcher(emailAddress);
        if(regMatcher.matches() || regMatcher1.matches()) {
        	return true;
        } else {
            return false;
        }
    }

    public boolean validateMobile(String mobileNumber) {
        regexPattern = Pattern.compile("^[0-9]{10}$");
        regMatcher   = regexPattern.matcher(mobileNumber);
        if(regMatcher.matches()) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean validateDate(String strDate)
    {
 	if (strDate.trim().equals(""))
 	{
 	    return true;
 	}
 	else
 	{
 	    SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MM-yyyy");
 	    sdfrmt.setLenient(false);
 	   
 	    try
 	    {
 	        sdfrmt.parse(strDate); 
 	        System.out.println(strDate+" is valid");
 	    }
 	   
 	    catch (ParseException e)
 	    {
 	        System.out.println(strDate+" is Invalid");
 	        return false;
 	    }
 	    return true;
 	}
    } 
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==register)
		{
			String user2,pass2,cpass2,em,dobb,mob,gen="",userval;
			int f=0;
			user2 = user.getText();
			char[] passcheck;
			passcheck = pass.getPassword();
			pass2 = String.valueOf(passcheck);
			char[] cpasscheck;
			cpasscheck = confmpass.getPassword();
			cpass2 = String.valueOf(cpasscheck);
			dobb =dobbox.getText();
			em =emailbox.getText();
			mob =mobibox.getText();
			if(male.isSelected())
				gen="Male";
			if(female.isSelected())
				gen="Female";
			if(others.isSelected())
				gen="Others";													//WRITING USER PROFILE TO FILE
				
			try {
				Class.forName("com.mysql.jdbc.Driver");
					System.out.println("Signup connection successful");
					
					try {
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/timecapsule","root","");
						Statement state=con.createStatement();
						ResultSet rs;
						
						rs = state.executeQuery("SELECT uname from USERS");
						while(rs.next())
						{
							userval = rs.getString("uname");
							if(userval.equals(user2))
							{
								f++;
								break;
							}
							
						}				
							if((!user2.equals(""))&&(!pass2.equals(""))&&(!dobb.equals(""))&&(!em.equals(""))&&(!mob.equals(""))&&((male.isSelected())|| (female.isSelected()) || others.isSelected()))
							{
								if(f==0)
								{
								if(pass2.equals(cpass2))
								{
									if(validateEmail(em))
									{
										if(validateDate(dobb))
										{
											if(validateMobile(mob))
											{
										
												state.executeUpdate("INSERT INTO USERS (uname,passwd,cpass,email,dob,phno,gender) values('"+user2+"','"+pass2+"','"+cpass2+"','"+em+"','"+dobb+"',"+mob+",'"+gen+"')");
												
												System.out.println(user2+" Data Inserted");
												JOptionPane.showMessageDialog(this,"Registered Successfully!");
												dispose();
												new Login();
		/*		/////////////////////////////////MAIL THROUGH SMTP///////////////////////////////////
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
										                    InternetAddress.parse(em));// whome u have to send mails that person id
										            message.setSubject("Welcome");
										            message.setText(" Hello "+user2+" !!"
										                    + "\n\n Welcome to Time Capsule."
										                    + "\n\n Your registration has successfully completed."
										                    + "\n\n Stay connected with us !!"
										                    + "\n\n\n\n With regards,\n Time Capsule Team.");

										            Transport.send(message);

										            System.out.println("Done");

										        } catch (MessagingException e1) {
										            throw new RuntimeException(e1);
										        }
						 /////////////////////////////////MAIL THROUGH SMTP//////////////////////////////////			

						*/						
												
											}
											else
											{
												validmobi.setText("* Enter Valid Mobile Number !");
												mobibox.setBorder(BorderFactory.createLineBorder(Color.RED,2));
												mobibox.addMouseListener(new MouseAdapter() {
													public void mouseClicked(MouseEvent e) {
														mobibox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
														validmobi.setText("");
													}
												});			
											}
										}
										else
										{
											validdob.setText("* Enter Valid DOB !");
											dobbox.setBorder(BorderFactory.createLineBorder(Color.RED,2));
											dobbox.addMouseListener(new MouseAdapter() {
												public void mouseClicked(MouseEvent e) {
													dobbox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
													validdob.setText("");
												}
											});
										}
									}
									else
									{
										validemail.setText("* Enter Valid Email Id !");
										emailbox.setBorder(BorderFactory.createLineBorder(Color.RED,2));
										emailbox.addMouseListener(new MouseAdapter() {
											public void mouseClicked(MouseEvent e) {
												emailbox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
												validemail.setText("");
											}
										});
									}
								}
								else
								{
									validconfirmpass.setText("* Password Mismatched !");
									confmpass.setBorder(BorderFactory.createLineBorder(Color.RED,2));
									confmpass.addMouseListener(new MouseAdapter() {
										public void mouseClicked(MouseEvent e) {
											confmpass.setBorder(BorderFactory.createLineBorder(Color.BLACK));
											validconfirmpass.setText("");
										}
									});
									}
								}
								else
								{	validuser.setText("* Username already Exists !");
									user.setBorder(BorderFactory.createLineBorder(Color.RED,2));
									user.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										user.setBorder(BorderFactory.createLineBorder(Color.BLACK));
										validuser.setText("");
									}
								});		
								}
							}
							else {
								if(user2.equals("")) {
									validuser.setText("* Enter a Username !");
									user.setBorder(BorderFactory.createLineBorder(Color.RED,2));}
								if(pass2.equals("")) {
									validpass.setText("* Enter a Password !");
									pass.setBorder(BorderFactory.createLineBorder(Color.RED,2));}
								if(cpass2.equals("")) {
									confmpass.setBorder(BorderFactory.createLineBorder(Color.RED,2));}
								if(em.equals("")) {
									validemail.setText("* Enter an Email id !");
									emailbox.setBorder(BorderFactory.createLineBorder(Color.RED,2));}
								if(mob.equals("")) {
									validmobi.setText("* Enter a Mobile Number ! ");
									mobibox.setBorder(BorderFactory.createLineBorder(Color.RED,2));}
								if(dobb.equals("")) {
									validdob.setText("* Enter your DOB !");
									dobbox.setBorder(BorderFactory.createLineBorder(Color.RED,2));}
								
								
								user.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										user.setBorder(BorderFactory.createLineBorder(Color.BLACK));
										validuser.setText("");
									}
								});
								confmpass.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										confmpass.setBorder(BorderFactory.createLineBorder(Color.BLACK));
										validconfirmpass.setText("");
									}
								});
								emailbox.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										emailbox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
										validemail.setText("");
									}
								});
								dobbox.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										dobbox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
										validdob.setText("");
									}
								});
								mobibox.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										mobibox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
										validmobi.setText("");
									}
								});
								pass.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										pass.setBorder(BorderFactory.createLineBorder(Color.BLACK));
										validpass.setText("");
									}
								});
					}
													
					con.close();
					}
					catch(Exception e1) {System.out.println("In signup "+e1);}
				}
				catch(Exception e2) {System.out.println("In signup "+e2);}							
			
		}
		if(e.getSource()==login)
		{
			dispose();
			new Login();
		}
	}
	
}
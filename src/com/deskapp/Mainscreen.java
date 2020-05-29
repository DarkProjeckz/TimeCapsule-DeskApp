package com.deskapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.border.*;

class Mainscreen extends JFrame implements ActionListener							//MAINSCREEN CLASS
{
	
	private static final long serialVersionUID = 1269041075088673511L;
	static String userid,dbuser,dbpass,dbsid;															
	static String s="",z="",x="",c="",b="",d="";
	static int id;
	Container container = getContentPane();
	JTextArea type = new JTextArea(10,20);
	JScrollPane scroll = new JScrollPane(type,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JButton sign_out = new JButton("Sign out");
	JButton add_event = new JButton("ADD EVENT");
	JButton modify_event = new JButton("MODIFY EVENT");
	JButton display_event = new JButton("DISPLAY EVENT");
	JButton about = new JButton("ABOUT");
	JButton info = new JButton("INFO");
	JLabel welcome = new JLabel("Welcome "+userid);
	JLabel title = new JLabel("Time Capsule");
	JLabel t1 = new JLabel("TITLE :");
	JTextField tt1 = new JTextField();
	JLabel date = new JLabel("DATE :");
	JTextField date1 = new JTextField();
	TitledBorder tborder = BorderFactory.createTitledBorder(new LineBorder(Color.black,1),"What's up?");
	
	public static String getid()													//CALLING VARIABLE FROM LOGIN CLASS
	{	return s;	}
	public static String getd()
	{	return d;	}
	public static String getz()
	{	return z;	}
	public static String getx()
	{	return x;	}
	public static String getb()
	{	return b;	}
	public static String getc()
	{	return c;	}
	public static void giveid()
	{	userid = Login.getid();	}
	public static void give_uid()
	{	id = Login.get_uid();	}

	
	public Mainscreen()
	{
		container.setLayout(new BorderLayout());
		create_main_items();
	}
	
	public void create_main_items()
	{
		setIconImage(new ImageIcon("img/title.png").getImage());
		setTitle("Time Capsule");
		setVisible(true);
		setBounds(50,20,1200,720);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JLabel bgimg = new JLabel(new ImageIcon("img/main.jpg"));					//ASSIGNING BACKGROUND IMAGE
		JLabel chef = new JLabel(new ImageIcon("img/chef.png"));
		container.add(bgimg);
		
		bgimg.setLayout(null);														//SETTING THE TEXTAREA AS TRANSPARENT
		type.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		scroll.setOpaque(false);
		type.setLineWrap(true);

		tborder.setTitleFont(new Font("Cooper",Font.BOLD,18));				//CHANGING THE FONT STYLE
		type.setFont(new Font("TimesNewRoman",Font.BOLD,20));
		scroll.setBorder(tborder);
		t1.setFont(new Font("Copperplate Gothic Bold",Font.BOLD,20));
		welcome.setFont(new Font("Copperplate Gothic Bold",Font.PLAIN,25));
		sign_out.setFont(new Font("Copperplate Gothic Bold",Font.PLAIN,25));
		date.setFont(new Font("Copperplate Gothic Bold",Font.BOLD,20));
		title.setFont(new Font("Copperplate Gothic Bold",Font.BOLD,40));
																					//SETTING THE COMPONENTS
																					
		chef.setBounds(4,90,120,180);			date1.setBounds(930,135,200,30);	date.setBounds(850,100,100,100);    
		tt1.setBounds(410,135,220,30);			t1.setBounds(330,100,100,100);		title.setBounds(530,30,500,100);
		scroll.setBounds(310,200,850,450);		add_event.setBounds(70,200,200,60);	modify_event.setBounds(70,300,200,60);
		display_event.setBounds(70,400,200,60);	about.setBounds(70,500,200,60);		info.setBounds(70,600,200,60);
		sign_out.setBounds(1030,40,120,25);		welcome.setBounds(20,-40,400,180);

		add_event.setFont(new Font("Segoe Print",Font.BOLD,13));
		modify_event.setFont(new Font("Segoe Print",Font.BOLD,13));
		display_event.setFont(new Font("Segoe Print",Font.BOLD,13));
		about.setFont(new Font("Segoe Print",Font.BOLD,13));
		info.setFont(new Font("Segoe Print",Font.BOLD,13));
		
		sign_out.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));	
		sign_out.setBorder(null);
		sign_out.setContentAreaFilled(false);
		sign_out.setFocusPainted(false);
		bgimg.add(welcome);			bgimg.add(sign_out);		bgimg.add(chef);
		date1.setToolTipText("dd-mm-yyyy");
		date1.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e) { 
            if (date1.getText().length() >= 10) 									// LIMITING TEXTFIELD TO 10 CHARACTERS
            e.consume(); 
			}
		});
		bgimg.add(date1);	bgimg.add(date);
		tt1.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e) { 
            if (tt1.getText().length() >= 20) 										
            e.consume(); 															// LIMITING TEXTFIELD TO 10 CHARACTERS
			}
		});
		bgimg.add(tt1);		bgimg.add(t1);		bgimg.add(title);
		type.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e) { 
            if (type.getText().length() >= 720) // limit textfield to 10 characters
            e.consume(); 
			}
		});
		bgimg.add(scroll);		bgimg.add(add_event);		bgimg.add(modify_event);
		bgimg.add(display_event);	bgimg.add(about);			bgimg.add(info);
		
		add_event.addActionListener(this);		display_event.addActionListener(this);
		modify_event.addActionListener(this);	info.addActionListener(this);
		about.addActionListener(this);			sign_out.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==about)													  //ACTION LISTENER FOR ABOUT
		{
				try {
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("About connection successful");
					
					try {
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/timecapsule","root","");
						Statement state=con.createStatement();
						ResultSet rs = state.executeQuery("SELECT UNAME,PASSWD,EMAIL,DOB,PHNO,GENDER FROM USERS WHERE UNAME='"+userid+"'");
						if(rs.next())
						{
							s = rs.getString("UNAME");
							if(s.equals(userid))
							{
								z = rs.getString("PASSWD");
								x = rs.getString("DOB");
								c = rs.getString("EMAIL");
								b = rs.getString("GENDER");
								d = rs.getString("PHNO");
								About.giveid();		About.givez();		About.givex();					//SETTING DATA TO DIFFERENT CLASSES
								About.giveb();		About.givec();		About.gived();
								new About();
								
							}
						}
						con.close();
					}
					catch(Exception e1) {System.out.println("In about "+e1);}
				}
				catch(Exception e1) {System.out.println("In about "+e1);}
				
		}
		if(e.getSource()==add_event)												//ACTION LISTENER FOR ADD_EVENT
		{
			String tit,dat,data,titc,datc;
			int f=0;
			tit=tt1.getText();
			dat=date1.getText();
			data=type.getText();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Add_event connection successful");
				
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/timecapsule","root","");
					Statement state=con.createStatement();
					if((!tit.equals(""))&&(!dat.equals(""))&&(!data.equals("")))
					{
						ResultSet rs = state.executeQuery("SELECT title,dat from USERS U, EVENTS E WHERE U.ID=E.USER_ID AND U.ID="+id+"");
						while(rs.next())
						{
							titc = rs.getString("title");
							datc = rs.getString("dat");
							System.out.println(titc+" "+datc);
							if(((titc.equals(tit)) && (datc.equals(dat))) ||( (titc==null) && (datc==null)))
							{
								f++;
								break;
							}
						}
						if(f==0)
						{
							if(Signup.validateDate(dat))
							{
								state.executeUpdate("INSERT INTO EVENTS (user_id,title,dat,cont) values("+id+",'"+tit+"','"+dat+"','"+data+"')");
								JOptionPane.showMessageDialog(this,"Event added successfully!");
								System.out.println(userid+" event added successfully");
								type.setText("");
							}
							else
							{
								JOptionPane.showMessageDialog(this,"Invalid date or format\n\tdd-mm-yyyy");
							}
							
						}
						else
							JOptionPane.showMessageDialog(this,"Title with same date already exists");	
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Give data to all fields");
					}
					con.close();
					}
					catch(Exception e1) {System.out.println("In add_event "+e1);}
				}
				catch(Exception e2) {System.out.println("In add_event "+e2);}
				
		}
		if(e.getSource()==modify_event)													//ACTION LISTENER FOR MODIFY_EVENT
		{
			String titt,dt,datta,titc,datc;
			int f=0;
			titt =tt1.getText();
			dt=date1.getText();
			datta=type.getText();
				
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Modify connection successful");
				
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/timecapsule","root","");
					Statement state=con.createStatement();
					ResultSet rs = state.executeQuery("SELECT title,dat from USERS U, EVENTS E WHERE U.ID=E.USER_ID AND U.ID="+id+"");
					while(rs.next())
					{
						titc = rs.getString("title");
						datc = rs.getString("dat");
						System.out.println(titc+" "+datc);
						if((titc.equals(titt)) && (datc.equals(dt)))
						{
							f++;
							break;
						}
					}
					
						if((!titt.equals(""))&&(!dt.equals("")))
						{
							if(f!=0)
							{
								state.executeUpdate("UPDATE EVENTS set cont='"+datta+"' where title='"+titt+"' and dat='"+dt+"' and USER_ID="+id+"");
								JOptionPane.showMessageDialog(this,"Event modified successfully!");
								System.out.println(userid+" event modified successfully");
								type.setText("");
							}
							else
								JOptionPane.showMessageDialog(this,"Data does not exists for modification");
						}
						else
						{
							JOptionPane.showMessageDialog(this,"Give data to all fields");
						}
					
					
					con.close();
					}
					catch(Exception e1) {System.out.println("In modify "+e1);}
				}
				catch(Exception e2) {System.out.println("In modify "+e2);}
		}
		if(e.getSource()==display_event)												//ACTIONLISTENER FOR DISPLAY_EVENT
		{
			String titl,datt,content1="";
			titl=tt1.getText();
			datt=date1.getText();
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Display connection successful");
				
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/timecapsule","root","");
					Statement state=con.createStatement();
					if((!titl.equals("")) && (!datt.equals("")) )
					{
						ResultSet rs = state.executeQuery("SELECT cont FROM EVENTS where title='"+titl+"' and dat='"+datt+"' and USER_ID="+id+"");
						if(rs.next()) {
						content1 = rs.getString("cont");
						}
						if(content1=="")
						{	
							type.setText("");
							JOptionPane.showMessageDialog(this,"Error! Data not found");	
						}
						else
						{
							type.setText(content1);
						}
						 																		
					}
					else
					{	JOptionPane.showMessageDialog(this,"Give data to all fields");   }
					con.close();
					}
					catch(Exception e1) {System.out.println("In display "+e1);}
				}
				catch(Exception e2) {System.out.println("In display "+e2);}
		}
		if(e.getSource()==info)																		//ACTIONLISTENER FOR INFO				
		{
			new Info();
		}			
		if(e.getSource()==sign_out)																	//ACTIONLISTENER FOR SIGNOUT
		{
			dispose();
			new Login();
		}
	}
}
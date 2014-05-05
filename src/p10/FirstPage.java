package p10;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.*;


public class FirstPage extends JFrame implements ActionListener
{
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JPanel p3=new JPanel();
	JPanel p4=new JPanel();
	JTextField t1=new JTextField(10);
	JPasswordField p=new JPasswordField(10);
	JLabel l1=new JLabel("LOGIN PAGE");
	JLabel l2=new JLabel("USER NAME");
	JLabel l3=new JLabel("PASSWORD");
	JButton b1=new JButton("LOGIN");
	JButton b2=new JButton("INSTAL");
	

  static Connection getConnection()
	{
		Connection conn = null;
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");  //loads driver into JVM m/c
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","ashwini");
			JOptionPane.showMessageDialog(null, "connection done");
		} 
		catch (Exception e) 
		{
			System.out.println("error in connection");
		}
		
		return conn;
		
	}
	
	FirstPage()
	{
		setVisible(true);
		this.setBounds(400,100,400,500);
		this.setLayout(new BorderLayout());
		setBackground(Color.green);
		p1.add(l1);
		p2.add(l2);p2.add(t1);p2.add(l3);p2.add(p);
		p2.setBackground(Color.pink);
		p1.setBackground(Color.getHSBColor(1000,5000,100));
		p3.setBackground(Color.pink);
		p2.setLayout(new FlowLayout(FlowLayout.LEFT,50,20));
		p3.add(b1);p3.add(b2);
		b1.setFont(new Font("Algerian",Font.TRUETYPE_FONT,20));
		b2.setFont(new Font("Algerian",Font.TRUETYPE_FONT,20));
		l2.setFont(new Font("Algerian",Font.TRUETYPE_FONT,20));
		l3.setFont(new Font("Algerian",Font.TRUETYPE_FONT,20));
		l1.setFont(new Font("Algerian",Font.BOLD,20));
		 add(p1,BorderLayout.NORTH);
		 add(p2,BorderLayout.CENTER);
		 add(p3,BorderLayout.SOUTH);
		 b1.addActionListener(this);
		 b2.addActionListener(this);
		 setResizable(false);
	}
	
	public static void main(String[] args) 
	{
    try 
    {
		UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		FirstPage f=new FirstPage();
	}
	catch (Exception e) 
	{
		
	}
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getActionCommand().equalsIgnoreCase("INSTAL"))
		{
			Connection conn;
			Statement st;
			conn=getConnection();
			try
			{
			st=conn.createStatement();
			//st.execute("create table worker(worker_id varchar(20),worker_name char(20),work_type char(20),salary integer,primary key(worker_id))");
			//st.execute("create table product(product_id varchar(20),product_name char(20),pro_quantity integer,price integer,primary key(product_id))");
			//st.execute("create table retail_shop(shop_id varchar(20),shop_name char(20),purchased_product varchar(20),quantity1 integer,payed_amt integer,tobe_paid integer,date1 date,primary key(shop_id))");
			//st.execute("create table factory(factory_id varchar(20),factory_name char(20),purchased_product varchar(20),quantity2 integer,payed_amt integer,tobe_paid integer,date1 date,primary key(factory_id))");
			//st.execute("create table summary()");
			JOptionPane.showMessageDialog(null,"creation of table is done");
			st.close();
			conn.close();
			}
			catch(Exception e)
			{
				System.out.println("table is not created");
			} 	
		}
		
		else if(arg0.getActionCommand().equalsIgnoreCase("LOGIN"))
		{
			String s1,s2;
			s1="shop";s2="wholesale";
 
			 if(s1.equalsIgnoreCase(t1.getText())&&s2.equalsIgnoreCase(p.getText()))
			{
				MainPage m=new MainPage();
				this.setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"WRONG PASSWORD!!!");
			}
		}
	}
}

package p10;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.*;
public class WorkerInsert extends JFrame implements ActionListener
{
	JLabel l1 = new JLabel("WORKER ID");
	JLabel l2 = new JLabel("WORKER NAME");
	JLabel l3 = new JLabel ("WORK TYPE");
	JLabel l4 = new JLabel ("SALARY");
	JTextField t1 = new JTextField();
	JTextField t2 = new JTextField();
	JTextField t3 = new JTextField();
	JTextField t4 = new JTextField();
	JButton b1 = new JButton("INSERT");
	JButton b2 = new JButton("BACK");
	private String query;

	
	
	static String userid="system", password = "ashwini";
	static String url = "jdbc:oracle:thin:@localhost:1521:xe";	
    static Statement stmt;
	static Connection con;
	
	public static Connection getConnection()
	{ 
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");	
			con = DriverManager.getConnection(url,userid, password);
			System.out.println("connected....");
		} 
		catch(Exception e) 
		{
			System.err.print("error in connection");
			
		}
		
		return con;
	}

	
	WorkerInsert()
	{
		
		setVisible(true);
		getContentPane().setBackground(Color.getHSBColor(320,580,640));
		setBounds(400,80,500,600);
		l1.setBounds(50,100,150,30);
		l2.setBounds(50,150,150,30);
		l3.setBounds(50,200,150,30);
		l4.setBounds(50,250,150,30);
		t1.setBounds(300,100,80,30);
		t2.setBounds(300,150,80,30);
		t3.setBounds(300,200,80,30);
		t4.setBounds(300,250,80,30);
		b1.setBounds(100,500,120,30);
		b2.setBounds(300,500,120,30);
		 l1.setFont(new Font("Algerian",Font.ITALIC,20));
		 l2.setFont(new Font("Algerian",Font.ITALIC,20));
		 l3.setFont(new Font("Algerian",Font.ITALIC,20));
		 l4.setFont(new Font("Algerian",Font.ITALIC,20));
		 b1.setFont(new Font("Algerian",Font.ITALIC,30));
		 b2.setFont(new Font("Algerian",Font.ITALIC,30));
		add(l1);add(t1);
		add(l2);add(t2);
		add(l3);add(t3);
		add(l4);add(t4);
		add(b1); add(b2);
        b1.addActionListener(this);
        b2.addActionListener(this);
        setResizable(false);
        setLayout(null);
		
	}
public static void main(String[] args) 
	{
	
	  try 
	  {
		  UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
			  WorkerInsert g1 = new WorkerInsert();
	  }
	   catch (Exception e) 
		{
			
		}
	}


public void actionPerformed(ActionEvent arg0) 
{
  
if(arg0.getActionCommand().equalsIgnoreCase("INSERT"))
  {
    	 getConnection();
    	 String s1,s2,s3;
    	 int a;
    	 s1=t1.getText();s2=t2.getText();s3=t3.getText();
    	 a= Integer.parseInt(t4.getText());
    	 Connection con = getConnection();
		 
	  try 
	  {
		  stmt = con.createStatement();
		  stmt.executeUpdate("insert into worker values('"+s1+"','"+s2+"','"+s3+"','"+a+"')");
		  stmt.close();
		  con.close();
		  System.out.println("successfull insert");
	  }
	  
	  catch (Exception e) 
	  {
		  System.out.println("error.....");
		e.printStackTrace();
	  }
  }

else if(arg0.getActionCommand().equalsIgnoreCase("BACK"))
{
	this.setVisible(false);
	worker w=new worker();
}

}
}






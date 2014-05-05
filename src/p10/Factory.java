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
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Factory extends JFrame implements ActionListener
{
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JPanel p3=new JPanel();
	JLabel l1=new JLabel("FACTORY DETAILS");
	JButton b1=new JButton("INSERT");
	JButton b2=new JButton("UPDATE");
	JButton b3=new JButton("BACK");
	 JTable table = new JTable(0,6);
	 DefaultTableModel mod=(DefaultTableModel)table.getModel();
	static Connection getConnection()
	{
		Connection conn = null;
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");  //loads driver into JVM m/c
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","ashwini");
		} 
		catch (Exception e) 
		{
			System.out.println("error in connection");
		}
		
		return conn;
		
	}
	
	Factory()
	{
		 Object[][] data=new Object[0][0];
		 String[] columnNames={"FACTORY ID","FACTORY NAME","PRODUCT NAME","QUANTITY","PAID","BALANCE AMOUNT","DATE"};
		 mod=new DefaultTableModel(data,columnNames);
		 table=new JTable(mod);
		setLayout(null);
		add(p1);
		add(p2);
		add(p3);
		p1.add(l1);
		p1.setBackground(Color.getHSBColor(456,67, 856));
		l1.setFont(new Font("Algerian",Font.ITALIC,30));
		p1.setBounds(0,0,500,50);
	    table.setBackground(Color.pink);
	   JScrollPane scrollPane = new JScrollPane(table);
	   p2.add(scrollPane);
       p2.setBackground(Color.gray);
       p2.setBounds(0,50,500,350);
	   setBounds(400,100,500,500);
	   p3.add(b1);
	   //p3.add(b2);
	   p3.add(b3);
	   p3.setLayout(new FlowLayout(FlowLayout.LEFT,30,5));
	   b1.setFont(new Font("Algerian",Font.TRUETYPE_FONT,30));
	   b2.setFont(new Font("Algerian",Font.TRUETYPE_FONT,30));
	   b3.setFont(new Font("Algerian",Font.TRUETYPE_FONT,30));
	   p3.setBounds(0,400,500,50);
	   setVisible(true);
	   setResizable(false);
	   b1.addActionListener(this);
	   b3.addActionListener(this);
	   display1();
	}

	void display1()
	{
		
		
		Connection con;
		Statement st;
		con=getConnection();
		String s1=null,s2 = null,s3=null,s7=null;
		int s4=0,s5=0,s6=0;
		 ResultSet rs;
		   try {
			st = con.createStatement();
	       rs = st.executeQuery("select * from factory");
	       int cols = rs.getMetaData().getColumnCount();
	       while(rs.next())
	       {
	    	   for(int i=1;i<=cols;i++)
	    	   {
	    		   s1=(String) rs.getObject(1);
	    		   s2=(String) rs.getObject(2);
	    		   s3=(String) rs.getObject(3);
	    		   s4=rs.getInt(4);
	    		   s5=rs.getInt(5);
	    		   s6=rs.getInt(6);
	    		   s7=rs.getString(7);
	      	   }
	    	   mod.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7});
	       }      
	      	   	 
	      System.out.println("display of table is done");
	        con.close(); st.close(); rs.close();
		       } 
		   catch (Exception e) 
		   {
			e.printStackTrace();
		   }		  
			
	}
	
    public static void main(String[] args) 
	{
    try 
    {
		UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		Factory r=new Factory();
	}
	catch (Exception e) 
	{
		
	}
	}

@Override
public void actionPerformed(ActionEvent arg0)
{
	if(arg0.getActionCommand().equalsIgnoreCase("INSERT"))
	{
		this.setVisible(false);
		FactoryInsert l=new FactoryInsert();
	}
	 else if(arg0.getActionCommand().equalsIgnoreCase("BACK"))
	   {
		   this.setVisible(false);
		   MainPage p=new MainPage();
	   }
}

}

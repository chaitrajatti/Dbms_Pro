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
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class product extends JFrame implements ActionListener
{
	 JTable table = new JTable(0,4);
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JPanel p3=new JPanel();
	JLabel l1=new JLabel("Product Details");
	JButton b1=new JButton("INSERT");
	JButton b2=new JButton("UPDATE");
	JButton b3=new JButton("BACK");
	
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
	
	product()
	{

		 Object[][] data=new Object[0][0];
		 String[] columnNames={"PRODUCT ID","PRODUCT NAME","PRODUCT QUANTITY","PRICE","TOTAL"};
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
	   b2.addActionListener(this);
	   b3.addActionListener(this);
	   display2();
	}
	
	void display2()
	{
		
		Connection con;
		Statement st;
		con=getConnection();
		String s1=null,s2 = null;
		int s3=0,s4=0,s5=0;
		 ResultSet rs;
		   try {
			st = con.createStatement();
	       rs = st.executeQuery("select * from product");
	       int cols = rs.getMetaData().getColumnCount();
	       while(rs.next())
	       {
	    	   for(int i=1;i<=cols;i++)
	    	   {
	    		   s1=(String) rs.getObject(1);
	    		   s2=(String) rs.getObject(2);
	    		   s3=rs.getInt(3);
	    		   s4=rs.getInt(4);
	    		   s5=rs.getInt(5);
	      	   }
	    	   mod.addRow(new Object[]{s1,s2,s3,s4,s5});
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
		product p=new product();
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
		ProductInsert l=new ProductInsert();
	}
	   else if(arg0.getActionCommand().equalsIgnoreCase("BACK"))
	   {
		   this.setVisible(false);
		   MainPage p=new MainPage();
	   }
	   else if(arg0.getActionCommand().equalsIgnoreCase("UPDATE"))
	   {
		   Connection conn;
			Statement st;
			conn=getConnection();
		   int rcount=table.getRowCount();
		   System.out.println(rcount);
		   String s1[]=new String[rcount];
		   String s2[]=new String[rcount];
		   int s3[]=new int[rcount];
		   int s4[]=new int [rcount];
		   int s5[]=new int [rcount];
		   String a1,a2,a3;
		   for(int i=0;i<rcount;i++)
		   {
			   s1[i]=(String) table.getValueAt(i,0);
			   s2[i]=(String) table.getValueAt(i,1);
			   a1=(String) table.getValueAt(i,2);
			   int a=Integer.parseInt(a1);
			   s3[i]=a;
			   a2=(String) table.getValueAt(i,3);
			   int b=Integer.parseInt(a2);
			   s4[i]=b;
			   a3=(String) table.getValueAt(i,4);
			   int c=Integer.parseInt(a3);
			   s5[i]=c;
			   try
			   {
				   System.out.println("hello..");
				st=conn.createStatement();
				st.executeQuery("update product set product_id='"+s1[i]+"',product_name='"+s2[i]+"',pro_quantity='"+s3[i]+"',price='"+s4[i]+"',total='"+s5[i]+"' where product_id='"+s1[i]+"'");
				JOptionPane.showMessageDialog(null,"UPDATION IS DONE");
			   } 
			   catch (SQLException e)
			   {
				e.printStackTrace();
		       }
		   }
	   }
}

}

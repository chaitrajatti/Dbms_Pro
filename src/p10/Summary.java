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

public class Summary extends JFrame implements ActionListener
{
	String mon;
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JPanel p3=new JPanel();
	JLabel l1=new JLabel("SUMMARY BOOK");
	JButton b1=new JButton("ANALYSIS");
	JTable table = new JTable();
	JTable table1 = new JTable();
	DefaultTableModel mod;//=(DefaultTableModel)table.getModel();
	DefaultTableModel mod1;
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
	
	Summary()
	{
		Object[][] data=new Object[0][0];
		String[] ColumnNames ={"RETAIL_SHOP","PRODUCT_SOLD","SOLD DATE"};
		String[] ColumnNames1={"FACTORY_NAME","PRODUCT_PURCHASED","PURCHASED DATE"};
		mod=new DefaultTableModel(data,ColumnNames);
		mod1=new DefaultTableModel(data,ColumnNames1);
		table=new JTable(mod);
		table1=new JTable(mod1);
		setLayout(null);
		add(p1);
		add(p2);
		add(p3);
		p1.add(l1);
		p1.setBackground(Color.getHSBColor(456,67, 856));
		l1.setFont(new Font("Algerian",Font.ITALIC,30));
		p1.setBounds(0,0,1000,50);
	    table.setBackground(Color.cyan);table1.setBackground(Color.cyan);
	    JScrollPane scrollPane = new JScrollPane(table);
	    JScrollPane scrollPane1 = new JScrollPane(table1);
	    scrollPane.setBounds(1000,100,0,0);
	   p2.add(scrollPane);p2.add(scrollPane1);
	   p2.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
       p2.setBackground(Color.gray);
       p2.setBounds(0,50,1000,350);
	   this.setBounds(300,50,1000,600);
	   p3.add(b1);
	    
	   p3.setLayout(new FlowLayout(FlowLayout.CENTER,30,5));
	   b1.setFont(new Font("Algerian",Font.TRUETYPE_FONT,30));
	  
	   p3.setBounds(0,400,1000,180);
	   p3.setBackground(Color.getHSBColor(873,346,934));
	   setVisible(true);
	   setResizable(false);
	   b1.addActionListener(this);
	   display1();
	}

	void display1()
	{
		
		Connection con;
		Statement st;
		con=getConnection();
		String r_name[]=new String[100];
		String r_pro[]=new String[100];
		String r_date[]=new String[100];
		String f_name[]=new String[100];
		String f_pro[]=new String[100];
		String f_date[]=new String[100];
		int k=0; int j=0;
		String s2 = null,s3=null,s4 = null,s5 = null,s7=null,s8=null;
		   try { 
			   ResultSet rs;                            //like %'"+mon+"'%
			st = con.createStatement();
			 System.out.println(mon);
			//rs = st.executeQuery("select * from retail_shop where date1  in between '1-'"+mon+"'-12' and '30-mon-12' ");
			rs = st.executeQuery("select * from retail_shop");
		       int cols = rs.getMetaData().getColumnCount();
		       System.out.println(cols);
		      
		       while(rs.next())
		       {
		    	  
		    	   for(int i=1;i<=cols;i++)
		    	   {
		    		   s2=(String) rs.getObject(2);
		    		   s3=(String) rs.getObject(3);
		    		   s7=(String) rs.getString(7);
		    		 
		      	   }
		    	   System.out.println(s2+s3+s7);
		    	   r_name[j]=s2;r_pro[j]=s3;r_date[j]=s7;
		    	   j++;
		       }      
		      
	    rs = st.executeQuery("select * from factory");
	       int cols1 = rs.getMetaData().getColumnCount();
	       while(rs.next())
	       {
	    	   for(int i=1;i<=cols1;i++)
	    	   {
	    		   s4=(String) rs.getObject(2);
	    		   s5=(String) rs.getObject(3);
	    		   s8=(String) rs.getString(7);
	    	 
	    	   }
	    	   System.out.println(s4+s5+s8);
	    	   f_name[k]=s4;f_pro[k]=s5;f_date[k]=s8;
	    	   k++;
	       }
	       for(int l=0;l<j;l++)
	       {
	        mod.addRow(new Object[]{r_name[l],r_pro[l],r_date[l]});
	        mod1.addRow(new Object[]{f_name[l],f_pro[l],f_date[l]});
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
		Summary r=new Summary();
	}
	catch (Exception e) 
	{
		
	}
    	    	
    	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if(arg0.getActionCommand().equalsIgnoreCase("ANALYSIS"))
		{
			pie p=new pie();
			this.setVisible(false);
		}
		
	}

//@Override
/*public void actionPerformed(ActionEvent arg0)
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
}*/

}

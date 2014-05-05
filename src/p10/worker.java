package p10;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class worker extends JFrame implements ActionListener
{
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JPanel p3=new JPanel();
	JLabel l1=new JLabel("Worker Details");
	JButton b1=new JButton("INSERT");
	JButton b2=new JButton("UPDATE");
	JButton b3=new JButton("BACK");
	JTable table = new JTable(0,4);
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
	
	worker()
	{
		Object[][] data=new Object[0][0];
		 String[] columnNames={"WORKER ID","WORKER NAME","WORK TYPE","SALARY"};
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
	   b1.addActionListener(this);
	   b2.addActionListener(this);
	   b3.addActionListener(this);
	   setVisible(true);
	   display();
	   setResizable(false);
	}	
	void display()
	{
		
		Connection con;
		Statement st;
		con=getConnection();
		String s1=null,s2 = null,s3=null;
		int s4 = 0;
		 ResultSet rs;
		   try {
			st = con.createStatement();
	       rs = st.executeQuery("select * from worker");
	       int cols = rs.getMetaData().getColumnCount();
	       while(rs.next())
	       {
	    	   for(int i=1;i<=cols;i++)
	    	   {
	    		   s1=(String) rs.getObject(1);
	    		   s2=(String) rs.getObject(2);
	    		   s3=(String) rs.getObject(3);
	    		   s4=rs.getInt(4);
	      	   }
	    	   mod.addRow(new Object[]{s1,s2,s3,s4});
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
		worker w=new worker();
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
	   WorkerInsert i1=new WorkerInsert();
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
	   String s3[]=new String[rcount];
	   String s4[]=new String [rcount];
	   int a[]=new int[rcount];
	   int i;
	   for(i=0;i<rcount;i++)
	   {
		   s1[i]=(String) table.getValueAt(i,0);
		   s2[i]=(String) table.getValueAt(i,1);
		   s3[i]=(String) table.getValueAt(i,2);
		   String s=(String) table.getValueAt(i,3);
		   int a1=Integer.parseInt(s);
		   a[i]=a1;
		   System.out.println(a[i]);
	   }
	   for(int k=0;k<i;k++)
	   {
		   try
		   {
			st=conn.createStatement();
			st.executeQuery("update worker set work_type='"+s3[k]+"',salary='"+a[k]+"' where worker_id='"+s1[k]+"'");
			JOptionPane.showMessageDialog(null,"UPDATION IS DONE");
		   } 
		   catch (SQLException e)
		   {
			e.printStackTrace();
	       }
	   }
   }
   else if(arg0.getActionCommand().equalsIgnoreCase("BACK"))
   {
	   this.setVisible(false);
	   MainPage p=new MainPage();
   }
  }

public void actionPerformed1(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
}
}


																																																																																																																													


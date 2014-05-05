package p10;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
public class RetailInsert extends JFrame implements ActionListener
{
	JLabel l1 = new JLabel("SHOP ID");
	JLabel l2 = new JLabel("SHOP NAME");
	JLabel l3 = new JLabel ("PURCHASED PRODUCT");
	JLabel l4 = new JLabel ("QUANTITY");
	JLabel l5 = new JLabel ("PAYED AMOUNT");
	JLabel l6 = new JLabel ("TO BE PAID");
	JLabel l7 = new JLabel ("DATE");
	JTextField t1 = new JTextField();
	JTextField t2 = new JTextField();
	JTextField t3 = new JTextField();
	JTextField t4 = new JTextField();
	JTextField t5 = new JTextField();
	JTextField t6 = new JTextField();
	JTextField t7 = new JTextField();
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

	
	RetailInsert()
	{
		
		setVisible(true);
		getContentPane().setBackground(Color.getHSBColor(320,580,640));
		setBounds(400,80,500,600);
		l1.setBounds(50,100,150,30);
		l2.setBounds(50,150,150,30);
		l3.setBounds(50,200,150,30);
		l4.setBounds(50,250,150,30);
		l5.setBounds(50,300,150,30);
		l6.setBounds(50,350,150,30);
		l7.setBounds(50,400,150,30);
		t1.setBounds(300,100,80,30);
		t2.setBounds(300,150,80,30);
		t3.setBounds(300,200,80,30);
		t4.setBounds(300,250,80,30);
		t5.setBounds(300,300,80,30);
		t6.setBounds(300,350,80,30);
		t7.setBounds(300,400,80,30);
		b1.setBounds(100,500,120,30);
		b2.setBounds(300,500,120,30);
		 l1.setFont(new Font("Algerian",Font.ITALIC,20));
		 l2.setFont(new Font("Algerian",Font.ITALIC,20));
		 l3.setFont(new Font("Algerian",Font.ITALIC,20));
		 l4.setFont(new Font("Algerian",Font.ITALIC,20));
		 l5.setFont(new Font("Algerian",Font.ITALIC,20));
		 l6.setFont(new Font("Algerian",Font.ITALIC,20));
		 l7.setFont(new Font("Algerian",Font.ITALIC,20));
		 b1.setFont(new Font("Algerian",Font.ITALIC,30));
		 b2.setFont(new Font("Algerian",Font.ITALIC,30));
		add(l1);add(t1);
		add(l2);add(t2);
		add(l3);add(t3);
		add(l4);add(t4);
		add(l5);add(t5);
		add(l6);add(t6);
		add(l7);add(t7);
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
			  RetailInsert g1 = new RetailInsert();
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
    	 ResultSet rs;
    	 String s1,s2,s3,s4,id = null;
    	 int a,b,c,price = 0;
    	 s1=t1.getText();s2=t2.getText();s3=t3.getText();s4=t7.getText();
    	 a= Integer.parseInt(t4.getText());
    	 b= Integer.parseInt(t5.getText());
    	 c= Integer.parseInt(t6.getText());
    	 Connection con = getConnection();
		 
	  try 
	  {
		  stmt = con.createStatement();
		  stmt.executeUpdate("insert into retail_shop values('"+s1+"','"+s2+"','"+s3+"','"+a+"','"+b+"','"+c+"','"+s4+"')");
		  rs=stmt.executeQuery("select product_id,price from product where product_name='"+s3+"'");
		  int cols = rs.getMetaData().getColumnCount();
	       while(rs.next())
	       {
	    		id=(String) rs.getObject(1);
	    	    price=rs.getInt(2);
	       }
	       int fin_price=price*a;
	       //stmt.executeQuery("update summary set shop_id='"+s1+"',retailshop_amount='"+fin_price+"'"); 
		  stmt.executeQuery("update product set pro_quantity=pro_quantity-'"+a+"',total=total-'"+fin_price+"' where product_id='"+id+"'");
		  System.out.println("successfull insert");
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
	retailshop w=new retailshop();
}

}
}






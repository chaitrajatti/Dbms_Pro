package p10;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.jfree.chart.*;
import org.jfree.data.general.DefaultPieDataset;

public class pie
{	
	String pro_name[]=new String[20];
	int total[]=new int[20];
	int j;
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
		} 
		catch(Exception e) 
		{
			System.err.print("error in connection");
			
		}
		
		return con;
    }
	
	
void graph()
{
	String query;
	Connection conn;
	Statement stmt;
    ResultSet rs;
    conn=getConnection();
    j=0;
    int a = 0;
    String s = null;
    try
   	 {
	 stmt = conn.createStatement();
	 query="select product_name,total from product";
	 rs=stmt.executeQuery(query);
	 int cols2=rs.getMetaData().getColumnCount();
	  while(rs.next())
	  { 
		  for(int i=1;i<=cols2;i++)
		  {
			  s=(String) rs.getObject(1);
	          a=(int) rs.getInt(2);
		  }
		  pro_name[j]=s;
		  total[j]=a;
		  j++;
	  }
   	 }
	  catch (Exception e) 
		 {
				System.out.println("error in extracting product data");
				e.printStackTrace();
		 }
}
	
	pie()
	{
	  graph();
	  DefaultPieDataset pieDataset = new DefaultPieDataset();
	  for(int l=0;l<j;l++)
	  {
	  pieDataset.setValue(pro_name[l], new Integer(total[l]));
	  }
	  JFreeChart chart = ChartFactory.createPieChart("Pie Chart using JFreeChart", pieDataset, true,true,true);

	  ChartFrame frame1=new ChartFrame("Pie Chart",chart);
	  frame1.setVisible(true);
	  frame1.setBounds(400,100,600,600);	
	 
	}
public static void main(String arg[])
{
pie p=new pie();	
}
}

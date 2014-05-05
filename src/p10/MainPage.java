package p10;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class MainPage extends JFrame implements ActionListener,Runnable
{
	static int x=0;
 JPanel p2=new JPanel();
 JLabel l1=new JLabel("WHOLESALE SHOP");
 JButton b1=new JButton("  WORKERS   ");
 JButton b2=new JButton("  PRODUCTS  ");
 JButton b3=new JButton("  FACTORY   ");
 JButton b4=new JButton("RETAIL SHOPS");
 JButton b5=new JButton("SUMMARY BOOK");
 ImageIcon i1=new ImageIcon("1.jpg");
 ImageIcon i2=new ImageIcon("2.jpg");
 JLabel l2=new JLabel(i1);
 MainPage()
 {
	 setLayout(null);
	 setVisible(true);
	 this.setResizable(false);
	 this.setBounds(350, 100, 500, 500);
	 getContentPane().setBackground(Color.pink);
	 add(p2);
	 add(l1);add(l2);
	l2.setBounds(0,0,500,500);
	 //p2.setBackground(Color.PINK);
	 //p2.add(l1);
	 b1.setBounds(150, 100, 250,50 );
	 b2.setBounds(150, 170, 250,50 );
	 b3.setBounds(150, 240, 250,50 );
	 b4.setBounds(150, 320, 250,50 );
	 b5.setBounds(150, 400, 250,50 );
	 b1.setBackground(Color.white);
	 b2.setBackground(Color.white);
	 b3.setBackground(Color.white);
	 b4.setBackground(Color.white);
	 b5.setBackground(Color.white);
	 l2.add(b1); l2.add(b2); l2.add(b3);l2.add(b4); l2.add(b5);
	 l1.setFont(new Font("Algerian",Font.ITALIC,30));
	 b1.setFont(new Font("Algerian",Font.ITALIC,30));
	 b2.setFont(new Font("Algerian",Font.ITALIC,30));
	 b3.setFont(new Font("Algerian",Font.ITALIC,30));
	 b4.setFont(new Font("Algerian",Font.ITALIC,30));
	 b5.setFont(new Font("Algerian",Font.ITALIC,30));
	 b1.addActionListener(this);
	 b2.addActionListener(this);
	 b3.addActionListener(this);
	 b4.addActionListener(this);
	 b5.addActionListener(this);
	 Thread t=new Thread(this);
	 t.start();
 }
public static void main(String args[])
 {
	 
	 try 
	 {
     	UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
     	 MainPage f1=new MainPage();
     } 
	 catch (Exception e) 
	 {
		e.printStackTrace();
	} 
	
 
}
@Override
public void actionPerformed(ActionEvent arg0)
{

 if(arg0.getActionCommand().equalsIgnoreCase("  PRODUCTS  "))
 {
	 System.out.println("hiiiii");
	 this.setVisible(false);
	 product l=new product();
	 l.setVisible(true); 
 } 
 else if(arg0.getActionCommand().equalsIgnoreCase("  WORKERS   "))
 {
	 this.setVisible(false); 
	 worker w=new worker();
 }
 else if(arg0.getActionCommand().equalsIgnoreCase("RETAIL SHOPS"))
 {
	 retailshop r=new retailshop();
	 r.setVisible(true);
	 this.setVisible(false); 
 }
 else if(arg0.getActionCommand().equalsIgnoreCase("SUMMARY BOOK"))
 {
	 Summary s=new Summary();
	 s.setVisible(true);
	 this.setVisible(false); 
 }
 else if(arg0.getActionCommand().equalsIgnoreCase("  FACTORY   "))
 {
	 this.setVisible(false); 
	 Factory l=new Factory();
	 l.setVisible(true);
 }
}
@Override
public void run() 
{
	try 
	{
		for(;;)
		{
		while(x<400)
		{
		x=x+10;
		l1.setBounds(x+0,10,300,20);
		Thread.sleep(200);
		}
		while(x>0)
		{
		x=x-10;
		l1.setBounds(x+0,10,300,20);
		Thread.sleep(200);
		}
		}
		
	} 
	catch (InterruptedException e) 
	{
		e.printStackTrace();
	}
}
}
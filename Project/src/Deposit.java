import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Deposit {

	
JFrame jfd = new JFrame("Depositing");
	
	JLabel jl = new JLabel("Please enter your account number");
	JLabel jl1 = new JLabel("enter amount to Deposit");
	JTextField jt = new JTextField();
	JTextField jt1 = new JTextField();
	
	JButton jb = new JButton("Deposit");
	JButton jbcl = new JButton("Back to home page");
	
	
	
	

	JLabel jld = new JLabel("You deposited ");
	JTextField jt3 = new JTextField();
	JLabel jlbal = new JLabel("Your balance now ");
	JTextField jt4 = new JTextField();
	
	
	GridLayout gl = new GridLayout(11,11);
	String enteredacn = "";
	
	String acn = "";
	String name ="";
	String address = "";
	String balance = "";
	int counter = 0;
	String depositamount = "";
	String date = "12-Feb-2018";
	
	String url = "jdbc:mysql://localhost:3306/database";
	String username = "root";
	String password = "";
	
	public void createDeposit() {
		jfd.setLayout(gl);
		
		jfd.add(jl);
		jfd.add(jt);
		jfd.add(jl1);
		jfd.add(jt1);
		
		jfd.add(jb);
		jfd.add(jld);
		jfd.add(jt3);
		jfd.add(jlbal);
		jfd.add(jt4);
		jfd.add(jbcl);
		
		jfd.setSize(500, 500);
		jfd.setVisible(true);
		
		jbcl.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jfd.dispose();
				
			}
		
		});
		
		
	jb.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			try (Connection c = (Connection) DriverManager.getConnection(url, username, password);){
				Statement stm = (Statement) c.createStatement();
				enteredacn = jt.getText();
				depositamount =jt1.getText();
				String query = "insert into money values( 'D' , " + "'" + enteredacn + "' ," + depositamount + ", CURDATE());";
				int rs1  = stm.executeUpdate(query);
				
				
				jt3.setText(depositamount);
				
				String balancequery = "select * from bank where ACN = '" + enteredacn + "';";
				
				ResultSet rs = stm.executeQuery(balancequery);
				
				while(rs.next()) {
					balance = rs.getString(4);
					System.out.println(balance);
				}
				
				int bal = Integer.parseInt(balance);
				int depadd = Integer.parseInt(depositamount);
				
				String updatequery = "update bank set balance = balance + " +depadd+" where ACN = '" + enteredacn+ "';";
				int rs2 = stm.executeUpdate(updatequery);
				
				
				
				int finalbalance = bal + depadd;
				
				String stringbalance = Integer.toString(finalbalance);
				jt4.setText(stringbalance);
				
				
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}});
	}}
		
	
	
	
	


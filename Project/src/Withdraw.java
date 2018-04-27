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

public class Withdraw {

	
	
	JFrame jfd = new JFrame("Withdrawing");
	
	JLabel jl = new JLabel("Please enter your account number");
	JLabel jl1 = new JLabel("Enter amount to withdraw");
	JTextField jt = new JTextField();
	JTextField jt1 = new JTextField();
	
	JButton jb = new JButton("Withdraw");
	JButton jbcl = new JButton("Back to home page");
	
	
	
	

	JLabel jl5 = new JLabel("The amount you withdrew ");
	JTextField jtw = new JTextField();
	JLabel jlba = new JLabel("Your total balance");
	JTextField jtbal = new JTextField();
	
	
	GridLayout gl = new GridLayout(11,11);
	String enteredacn = "";
	
	String acn = "";
	String name ="";
	String address = "";
	String balance = "";
	int counter = 0;
	String withdrawamount = "";
	
	
	String url = "jdbc:mysql://localhost:3306/database";
	String username = "root";
	String password = "";
	
	public void createWithdraw() {
		jfd.setLayout(gl);
		
		jfd.add(jl);
		jfd.add(jt);
		jfd.add(jl1);
		jfd.add(jt1);
		jfd.add(jb);
		
		jfd.add(jl5);
		jfd.add(jtw);
		jfd.add(jlba);
		jfd.add(jtbal);
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
				withdrawamount =jt1.getText();
				String query = "insert into money values( 'W' , " + "'" + enteredacn + "' ," + withdrawamount + ", CURDATE());";
				int rs1  = stm.executeUpdate(query);
				
				jtw.setText(withdrawamount);
				
				String balancequery = "select * from bank where ACN = '" + enteredacn + "';";
				
				ResultSet rs = stm.executeQuery(balancequery);
				
				while(rs.next()) {
					balance = rs.getString(4);
					System.out.println(balance);
				}
				
				int bal = Integer.parseInt(balance);
				int withadd = Integer.parseInt(withdrawamount);
				
				String updatequery = "update bank set balance = balance - " +withadd+" where ACN = '" + enteredacn+ "';";
				int rs2 = stm.executeUpdate(updatequery);
				
				if(withadd>bal) {
					JFrame jf = new JFrame();
					JButton jb = new JButton("Insufficient Funds");
					jf.setSize(500,500);
					jf.setVisible(true);
					jf.add(jb);
					jb.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							jf.dispose();
							jfd.dispose();
							
						}
						
					
					});
				}
				
				int finalbalance = bal - withadd;
				
				String stringbalance = Integer.toString(finalbalance);
				jtbal.setText(stringbalance);
				
				
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}});
	}}
		
		
		
		
		

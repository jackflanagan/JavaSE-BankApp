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

public class Balance {

	JFrame jfd = new JFrame("Balance");
	
	JLabel jl = new JLabel("Please enter your account number");
	
	JTextField jt = new JTextField();

	
	JButton jb = new JButton("See balance");
	JButton jbcl = new JButton("Back to home page");
	
	JLabel jl2 = new JLabel("Your account number will appear here");
	JLabel jl3 = new JLabel("Your name will appear here");
	JLabel jl4 = new JLabel("Your address will appear here");
	JLabel jl5 = new JLabel("Your balance will appear here");
	
	
	GridLayout gl = new GridLayout(11,11);
	String enteredacn = "";
	
	String acn = "";
	String name ="";
	String address = "";
	String balance = "";
	
	String date = "";
	
	String url = "jdbc:mysql://localhost:3306/database";
	String username = "root";
	String password = "";
	
	public void createBalance() {
		jfd.setLayout(gl);
		
		jfd.add(jl);
		jfd.add(jt);
		
		
		jfd.add(jl2);
		jfd.add(jl3);
		jfd.add(jl4);
		jfd.add(jl5);
		
		
		
		jfd.add(jl5);
		jfd.add(jb);
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
				
				String query = "select * from bank where ACN = '" + enteredacn +"';";
				ResultSet rs1  = stm.executeQuery(query);
				
				while(rs1.next()) {
					System.out.println("hello");
					
					acn = rs1.getString(1);
					name = rs1.getString(2);
					address = rs1.getString(3);
					balance = rs1.getString(4);
					System.out.println(acn);
					
					
					
				
				}
				
				jl2.setText(acn);
				jl3.setText(name);
				jl4.setText(address);
				jl5.setText("£" + balance);
				
				
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}});
	}}
		
	
	
	
	


	
	


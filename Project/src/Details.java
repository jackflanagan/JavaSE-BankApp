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

public class Details {
	
	JFrame jfd = new JFrame("Your Details");
	
	JLabel jl = new JLabel("Please enter your account number");
	JTextField jt = new JTextField();
	
	JButton jb = new JButton("Fetch Details");
	JButton jbcl = new JButton("Back to home page");
	
	JLabel jl2 = new JLabel("Your account number will appear here");
	JLabel jl3 = new JLabel("Your name will appear here");
	JLabel jl4 = new JLabel("Your address will appear here");
	//JLabel jl5 = new JLabel("Your balance will appear here ");
	
	GridLayout gl = new GridLayout(11,11);
	String enteredacn = "";
	
	String acn = "";
	String name ="";
	String address = "";
	String balance = "";
	int counter = 0;
	
	boolean test = false;
	
	public void createDetails() {
		
		jfd.setLayout(gl);
		
		jfd.add(jl);
		jfd.add(jt);
		jfd.add(jb);
		jfd.add(jl2);
		jfd.add(jl3);
		jfd.add(jl4);
		//jfd.add(jl5);
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
			public void actionPerformed(ActionEvent arg0) {
				String url = "jdbc:mysql://localhost:3306/database";
				String username = "root";
				String password = "";

		
				try (Connection c = (Connection) DriverManager.getConnection(url, username, password)) {
					System.out.println("connect");

					Statement stm = (Statement) c.createStatement();
					enteredacn = jt.getText();
					
					
					String testquery ="select * from bank where ACN = '" + enteredacn + "';";

					System.out.println(testquery);
					
					ResultSet rs1  = stm.executeQuery(testquery);
					
					while(rs1.next()) {
						System.out.println("hello");
						
						acn = rs1.getString(1);
						name = rs1.getString(2);
						address = rs1.getString(3);
						balance = rs1.getString(4);
						System.out.println(acn);
						test = rs1.first();
						System.out.println(test);
						
						counter++;
						
						
					
					}
					System.out.println("ts");
					
					System.out.println(counter);
					
					if(counter ==0) {                         // this is the error checking if you account number is incorrect
						JFrame jfe = new JFrame("Error");
						JLabel jl = new JLabel("Unrecognized account number, please try again. Letters will need to be capitals");
						JButton jb3 = new JButton("Okay");
						
						GridLayout gl2 = new GridLayout(4,4);
						jfe.setLayout(gl2);
						
						jfe.add(jl);
						jfe.add(jb3);
						
						jfe.setSize(500, 500);
						jfe.setVisible(true);
						
						jb3.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								jfe.dispose();
								
							}
							
						});
						
					}
		
					jl2.setText(acn);
					jl3.setText(name);
					jl4.setText(address);
					//jl5.setText("£" + balance);
					
					
					
					
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
				
			}
			
		});
		
	}
	
	
	

}

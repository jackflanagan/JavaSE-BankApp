import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class OpenPage {

	String g, a;
	int rs = 0;
	String latest ="";
	int latestacc = 0;
	String newacn = "";
	
	public void OpenPageCreate() {                            // this class is for the open account

		JFrame jfo = new JFrame("Opening an Account");

		JLabel jl = new JLabel("Name");
		JLabel jl1 = new JLabel("Address");

		JLabel jl2 = new JLabel("Gender");
		JLabel jl3 = new JLabel("Account Type");

		JTextField jf = new JTextField(20);
		JTextField jf1 = new JTextField(20);

		JButton jb = new JButton("Submit");

		JRadioButton rb1 = new JRadioButton("Male");
		JRadioButton rb2 = new JRadioButton("Female");

		JRadioButton rb3 = new JRadioButton("Current");
		JRadioButton rb4 = new JRadioButton("Standard");

		GridLayout fl = new GridLayout(12, 12);
		JButton jbcl = new JButton("Back to home page");

		ButtonGroup b1 = new ButtonGroup();
		ButtonGroup b2 = new ButtonGroup();

		b1.add(rb1);
		b1.add(rb2);

		b2.add(rb3);
		b2.add(rb4);

		jfo.setLayout(fl);

		jfo.add(jl);
		jfo.add(jf);
		jfo.add(jl1);
		jfo.add(jf1);
		jfo.add(jl2);
		jfo.add(rb1);
		jfo.add(rb2);
		jfo.add(jl3);
		jfo.add(rb3);
		jfo.add(rb4);
		jfo.add(jb);
		jfo.add(jbcl);

		jfo.setSize(500, 500);
		jfo.setVisible(true);
		

		jbcl.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jfo.dispose();
				
			}
		
		});

		

		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String url = "jdbc:mysql://localhost:3306/database";
				String username = "root";
				String password = "";

				String name = jf.getText();
				String address = jf1.getText();

				try (Connection c = (Connection) DriverManager.getConnection(url, username, password)) {
					System.out.println("connect");

					Statement stm = (Statement) c.createStatement();

					if (rb1.isSelected()) {
						g = "M";
					}
					if (rb2.isSelected()) {
						g = "F";
					}
					if (rb3.isSelected()) {
						a = "C";
					}
					if (rb4.isSelected()) {
						a = "S";
					}

					String acno = a + g;
					
					String testquery = "Select lpad(max(substr(ACN , 3 , 3) + 1) , 3 , '0') from bank where ACN like" + " '" + a +"%';";

			
					//System.out.println(testquery);
					
					ResultSet rs1  = stm.executeQuery(testquery);
					
					while(rs1.next()) {
						//System.out.println(rs1.getString(1));
						
						latest = rs1.getString(1);
						latestacc = Integer.parseInt(latest);
						System.out.println(latestacc);
						
						
						
					}
					String insertquery = "Insert into bank values( '" + a + g + latest +"' , '" + name + "' , '" + address + "' ," + "0);"; 
					
					String acnquery = "select max(ACN) from bank where ACN like '" + a + "%';" ;

					rs = stm.executeUpdate(insertquery);
					
					ResultSet rs2 = stm.executeQuery(acnquery);
					
					while(rs2.next()) {
					
						//System.out.println(rs2.getString(1));
					 newacn = rs2.getString(1);
						

					
					}
					
					JFrame jf = new JFrame("Completed!");
					JLabel jl = new JLabel("Account Created!");
					JLabel jl1 = new JLabel ("Your account number is:" + newacn);
					JButton jb = new JButton("Close");
					
					GridLayout gl1 = new GridLayout(4,4);
					
					jf.setLayout(gl1);
					
					jf.add(jl);
					jf.add(jl1);
					jf.add(jb);
					
					jf.setSize(200,200);
					jf.setVisible(true);
					
					jb.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							jf.dispose();
							jfo.dispose();
							
						}
						
					});
					
					
					
					

				} catch (Exception e) {
					System.out.println("no connection ");
					System.out.println(e);
				}

			}

		});

	}

}

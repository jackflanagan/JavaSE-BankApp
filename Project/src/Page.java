import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Page {                              // this class keeps the home page and the action listeners for the items in the menu bar

	public void create() {

		JFrame jf = new JFrame("Banking");
		JMenuBar jmu = new JMenuBar();
		JMenu jm = new JMenu("Account");
		JMenu jm1 = new JMenu("Banking");

		JMenuItem it1 = new JMenuItem("Open");
		JMenuItem it2 = new JMenuItem("Details");
		JMenuItem it3 = new JMenuItem("Close");
		JMenuItem it4 = new JMenuItem("Deposit");
		JMenuItem it5 = new JMenuItem("Withdraw");
		JMenuItem it6 = new JMenuItem("Balance");

		jm.add(it1);
		jm.add(it2);
		jm.add(it3);
		jm1.add(it4);
		jm1.add(it5);
		jm1.add(it6);

		it1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {        
				// TODO Auto-generated method stub
				OpenPage op = new OpenPage();
				op.OpenPageCreate();

			}
		});
		
		it2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Details d = new Details();
				d.createDetails();
				
			}});	
		
		it3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Close cl = new Close();
				cl.createClose();
				
			}
			
		});
		
		it5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Withdraw w = new Withdraw();
				w.createWithdraw();
				
			}
			
		});
		
		it4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Deposit d = new Deposit();
				d.createDeposit();
				
			}
			
		});
		
		it6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Balance b = new Balance();
				b.createBalance();
				
			}
			
		});
			
		
		

		jmu.add(jm);
		jmu.add(jm1);
		jf.setJMenuBar(jmu);

		jf.setSize(500, 500);
		jf.setVisible(true);

	}

}

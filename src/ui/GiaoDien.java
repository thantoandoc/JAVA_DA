package ui;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GiaoDien extends JFrame{
	public static int WIDTH = 720;
	public static int HEIGHT = 520;
	
	private Box box, searchBox, buttonBox;
	private JLabel searchName;
	private JTextField txtSearch;
	private JButton btnSearch;
	
	public GiaoDien(String title) {
		super(title);
		mapWidgets();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);
	}
	private void mapWidgets() {
		box = Box.createVerticalBox();
		searchBox = Box.createHorizontalBox();
		buttonBox = Box.createHorizontalBox();
		box.add(searchBox);
		box.add(Box.createVerticalStrut(16));
		box.add(buttonBox);
		box.add(Box.createVerticalStrut(8));
		
		this.add(box, BorderLayout.NORTH);
		
		searchName = new JLabel("Ten");
		txtSearch = new JTextField(20);
		btnSearch = new JButton("Tim Kiem");
		searchBox.add(searchName);
		searchBox.add(txtSearch);	
		searchBox.add(btnSearch);
	}
	
}

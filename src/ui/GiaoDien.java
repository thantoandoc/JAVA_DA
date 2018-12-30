package ui;

import java.awt.BorderLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class GiaoDien extends JFrame{
	public static int WIDTH = 720;
	public static int HEIGHT = 520;
	
	private Box box, searchBox;
	private JTextField txtSearch;
	private JButton btnSearch;
	private DefaultTableModel tableModel;
	private JTable table;
	private JComboBox<String> cbb;
	private String[] arrS = {
			"Tim Kiem Theo Ten",
			"Tim Kiem Theo Sach"
	};
	
	public GiaoDien(String title) {
		super(title);
		mapWidgets();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);
	}
	private void mapWidgets() {
		box = Box.createVerticalBox();
		box.setBorder(new EmptyBorder(new Insets(16, 16, 16, 16)));
		searchBox = Box.createHorizontalBox();
		
		cbb = new JComboBox<>(arrS);
		txtSearch = new JTextField(20);
		btnSearch = new JButton("Tim Kiem");
		
		searchBox.add(cbb);
		searchBox.add(txtSearch);	
		searchBox.add(btnSearch);
		
		box.add(searchBox);
		box.add(Box.createVerticalStrut(16));
		this.add(box, BorderLayout.NORTH);
		
		
		tableModel = new DefaultTableModel();
		table = new JTable(tableModel);
		table.setRowMargin(4);
		tableModel.addColumn("Ma so");
		tableModel.addColumn("Ten");
		tableModel.addColumn("Tac gia");

		tableModel.addColumn("Nha bien soan");

		tableModel.addColumn("Nam phat hanh");

		tableModel.addColumn("Trang thai");
		tableModel.addColumn("");

		JScrollPane sc = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.add(sc , BorderLayout.CENTER);
		
	}
	
}

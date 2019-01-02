package ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.Source;
import model.Book;
import model.IConstants;
import model.LinkedList;

@SuppressWarnings("serial")
public class GDDeleteBook extends JFrame implements ActionListener, ItemListener{
	public static int WIDTH = 720;
	public static int HEIGHT = 436;
	
	private Box box, searchBox;
	private JTextField txtSearch;
	private JButton  btnDel, btnBack;
	private DefaultTableModel tableModel;
	private JTable table;
	private JComboBox<String> cbbDel;
	private int DELETE_MODE = 0;
	
	public GDDeleteBook(String title) {
		super(title);
		mapWidgets();
		handleEvents();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	private void handleEvents() {
		cbbDel.addItemListener(this);
		btnDel.addActionListener(this);
		btnBack.addActionListener(this);
	}
	
	private void mapWidgets() {
		box = Box.createVerticalBox();
		box.setBorder(new EmptyBorder(new Insets(16, 16, 16, 16)));
		searchBox = Box.createHorizontalBox();
		
		btnBack = new JButton(IConstants.GO_BACK);
		
		cbbDel = new JComboBox<>(IConstants.DEL_MODE);
		txtSearch = new JTextField(20);
		btnDel = new JButton(IConstants.DEL);
		
		searchBox.add(btnBack);
		searchBox.add(Box.createHorizontalStrut(4));
		searchBox.add(cbbDel);
		searchBox.add(Box.createHorizontalStrut(4));
		searchBox.add(txtSearch);	
		searchBox.add(Box.createHorizontalStrut(4));
		searchBox.add(btnDel);
		
		box.add(searchBox);
		box.add(Box.createVerticalStrut(16));
		this.add(box, BorderLayout.NORTH);
		
		
		tableModel = new DefaultTableModel();
		table = new JTable(tableModel);
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		
		table.setRowMargin(4);
		tableModel.addColumn(IConstants.s_ID);
		tableModel.addColumn(IConstants.s_NAME);
		tableModel.addColumn(IConstants.s_AUTHOR);

		tableModel.addColumn(IConstants.s_COM);

		tableModel.addColumn(IConstants.s_YEAR);

		tableModel.addColumn(IConstants.s_STATUS);
		
		
		showALLBook(GiaoDien.list);
		JScrollPane sc = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
			}
		});
		this.add(sc , BorderLayout.CENTER);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBack) {
			new GiaoDien("Quản Lý Sách").setVisible(true);
			this.dispose();
		}
		if(e.getSource() == btnDel) {
			switch (DELETE_MODE) {
			case 0:
				deleteByID();
				break;
			case 1:
				deleteAfterID();
				break;
			case 2:
				deleteByName();
				break;
			case 3:
				deleteByAuthor();
				break;
			case 4:
				deleteFirst();
				break;
			case 5:
				deleteLast();
				break;
					
			default:
				break;
			}
		}
	}
	
	private void deleteByAuthor() {
		String s = txtSearch.getText().toString().trim();
		if(!s.equals("")) {
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn Có Chắc Chắn Muốn Xóa Tác Giả : " + s + " ?", "Xóa Theo Tên", dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION) {
				GiaoDien.list.deleteByAuthor(s);	
				showALLBook(GiaoDien.list);
			}
		}	
	}
	private void deleteByName() {
		String s = txtSearch.getText().toString().trim();
		if(!s.equals("")) {
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn Có Chắc Chắn Muốn Xóa Sách " + s + " ?", "Xóa Theo Tên", dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION) {
				GiaoDien.list.deleteByName(s);	
				showALLBook(GiaoDien.list);
			}
		}	
		
	}
	private void deleteAfterID() {
		String s = txtSearch.getText().toString().trim();
		if(!s.equals("")) {
			int id = Integer.parseInt(s);
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn Có Chắc Chắn Muốn Xóa Sách Sau ID = " + s + " ?", "Xóa Theo Tên", dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION) {
				GiaoDien.list.deleteAfterID(id);	
				showALLBook(GiaoDien.list);
			}
		}
	}
	private void deleteLast() {
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn Có Chắc Chắn Muốn Xóa Phần Tử Này?", "Xóa Phần Tử Cuối", dialogButton);
		if(dialogResult == JOptionPane.YES_OPTION) {
			GiaoDien.list.deleteLast();	
			showALLBook(GiaoDien.list);
		}
	}
	private void deleteFirst() {
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn Có Chắc Chắn Muốn Xóa Phần Tử Này?", "Xóa Phần Tử Đầu", dialogButton);
		if(dialogResult == JOptionPane.YES_OPTION) {
			GiaoDien.list.deleteFirst();	
			
			showALLBook(GiaoDien.list);
		}
		
	}
	private void deleteByID() {
		String s = txtSearch.getText().toString().trim();
		if(!s.equals("")) {
			int id = Integer.parseInt(s);
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn Có Chắc Chắn Muốn Xóa Sách ID= " + s + " ?", "Xóa Theo Tên", dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION) {
				GiaoDien.list.deleteByID(id);	
				showALLBook(GiaoDien.list);
			}
		}
	}
	private void showALLBook(LinkedList temp) {
		if(temp == null) {
			temp = new Source().readFile();
		}
		
		int count = tableModel.getRowCount();
		for(int i = count - 1; i >= 0 ;i-- ) {
			tableModel.removeRow(i);	
		}
		
		for(int i = 0; i < temp.getLength(); i++) {
			Book book = temp.get(i);
			tableModel.addRow(new Object[] {String.valueOf(book.getId()),book.getName(), book.getAuthor(), book.getReleaseCompany(), String.valueOf(book.getReleaseYear()), String.valueOf(book.isStatus())});
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == cbbDel) {
			DELETE_MODE = cbbDel.getSelectedIndex();
			if(DELETE_MODE == 4 || DELETE_MODE == 5) txtSearch.setEnabled(false);
			else { txtSearch.setEnabled(true); }
		}
	}
	
}


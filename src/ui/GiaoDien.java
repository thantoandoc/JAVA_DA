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
public class GiaoDien extends JFrame implements ActionListener, ItemListener{
	public static int WIDTH = 720;
	public static int HEIGHT = 436;
	
	private Box box, searchBox, viewModeBox;
	private JTextField txtSearch;
	private JButton btnSearch,btnShow,btnAdd, btnUpdate, btnBorrow, btnRet, btnDel, btnSave;
	private DefaultTableModel tableModel;
	private JTable table;
	private JComboBox<String> cbbSearch, cbbShow;
	private int SHOW_MODE = 0;
	private int SEARCH_MODE = 0;
	public static LinkedList list;
	
	public GiaoDien(String title) {
		super(title);
		mapWidgets();
		handleEvents();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	private void handleEvents() {
		btnAdd.addActionListener(this);
		btnShow.addActionListener(this);
		btnSearch.addActionListener(this);
		cbbShow.addItemListener(this);
		cbbSearch.addItemListener(this);
		btnUpdate.addActionListener(this);
		btnBorrow.addActionListener(this);
		btnRet.addActionListener(this);
		btnDel.addActionListener(this);
		btnSave.addActionListener(this);
	}
	
	private void mapWidgets() {
		box = Box.createVerticalBox();
		box.setBorder(new EmptyBorder(new Insets(16, 16, 16, 16)));
		searchBox = Box.createHorizontalBox();
		
		cbbSearch = new JComboBox<>(IConstants.FIND_MODE);
		txtSearch = new JTextField(20);
		btnSearch = new JButton(IConstants.TIM_KIEM);
		btnSave = new JButton("Lưu File");
		searchBox.add(cbbSearch);
		searchBox.add(Box.createHorizontalStrut(4));
		searchBox.add(txtSearch);	
		searchBox.add(Box.createHorizontalStrut(4));
		searchBox.add(btnSearch);
		
		searchBox.add(Box.createHorizontalStrut(4));
		searchBox.add(btnSave);
		
		box.add(searchBox);
		box.add(Box.createVerticalStrut(16));
		viewModeBox = Box.createHorizontalBox();
		cbbShow = new JComboBox<>(IConstants.SHOW_MODE);
		viewModeBox.add(cbbShow);	
		viewModeBox.add(Box.createHorizontalStrut(4));
		btnShow = new JButton(IConstants.XEM_THONG_TIN);
		viewModeBox.add(btnShow);
		viewModeBox.add(Box.createHorizontalStrut(4));
		
		btnAdd = new JButton(IConstants.THEM_SACH);
		btnAdd.setPreferredSize(btnSearch.getPreferredSize());
		
		btnUpdate = new JButton("Cập nhật");
		btnUpdate.setPreferredSize(btnSearch.getPreferredSize());
		btnUpdate.setEnabled(false);
		viewModeBox.add(btnAdd);
		viewModeBox.add(Box.createHorizontalStrut(4));
		viewModeBox.add(btnUpdate);
		
		btnBorrow = new JButton("Mượn Sách");
		viewModeBox.add(Box.createHorizontalStrut(4));
		viewModeBox.add(btnBorrow);
		
		btnRet = new JButton("Trả Sách");
		viewModeBox.add(Box.createHorizontalStrut(4));
		viewModeBox.add(btnRet);
		
		btnDel = new JButton("Xóa");
		viewModeBox.add(Box.createHorizontalStrut(4));
		viewModeBox.add(btnDel);
		
		box.add(viewModeBox);
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
		
		showALLBook(list);
		JScrollPane sc = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnUpdate.setEnabled(true);
			}
		});
		this.add(sc , BorderLayout.CENTER);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSave) {
			new Source().writeFile(list);
		}
		if(e.getSource() == btnDel) {
			new GDDeleteBook("Xóa Sách").setVisible(true);
			this.dispose();
		}
		if(e.getSource() == btnRet) {
			new GDReturn("Danh Sách Đã Mượn").setVisible(true);
			this.dispose();
		}
		if(e.getSource() == btnBorrow) {
			new GDBorrowBook("Danh Sách Có Thể Mượn").setVisible(true);
			this.dispose();
		}
		if(e.getSource() == btnAdd){
			
			addNewBook();
		}
		if(e.getSource() == btnShow){
			int count = tableModel.getRowCount();
			for(int i = count - 1; i >= 0 ;i-- ) {
				tableModel.removeRow(i);	
			}
			
			switch (SHOW_MODE) {
			case 0:
				showBook();
				break;
			case 1:
				showBorrowedBook();
				break;
			case 2:
				showNotBorrowedBook();
				break;
			case 3:
				showBookAmongName();
				break;
			case 4:
				showBookAmongAuthor();
				break;
			case 5:
				showBookAmongCom();
				break;
			case 6:
				showBookAmongYear();
				break;

			default:
				break;
			}
		}
		if(e.getSource() == btnSearch) {
			
			String s = txtSearch.getText().toString().trim();
			if(!s.equals("")) {
				seach(s);
			}
		}
		if(e.getSource() == btnUpdate) {
			int temp = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
			
			new GDUpdate("Cập nhật thông tin", list.getBookByID(temp)).setVisible(true);
			this.dispose();
		}
	}
	private void seach(String s) {
		
		int count = tableModel.getRowCount();
		for(int i = count - 1; i >= 0 ;i-- ) {
			tableModel.removeRow(i);	
		}
		
		switch(SEARCH_MODE) {
			case 0 :
				showALLBook(list.searchByName(s));;
				break;
			case 1 :
				showALLBook(list.searchByAuthor(s));;
				break;
			case 2 :
				showALLBook(list.searchByReleaseCompany(s));;
				break;
			default:
				break;
		}
	}
	private void showBookAmongYear() {
		if(list == null) {
			list = new Source().readFile();
		}
		LinkedList temp = list.sortByReleaseYear();
		for(int i = 0; i < temp.getLength(); i++) {
			Book book = temp.get(i);
			tableModel.addRow(new String[] {String.valueOf(book.getId()),book.getName(), book.getAuthor(), book.getReleaseCompany(), String.valueOf(book.getReleaseYear()), String.valueOf(book.isStatus())});
		}	
	}
	private void showBookAmongCom() {
		if(list == null) {
			list = new Source().readFile();
		}
		LinkedList temp = list.sortByReleaseCompany();
		for(int i = 0; i < temp.getLength(); i++) {
			Book book = temp.get(i);
			tableModel.addRow(new String[] {String.valueOf(book.getId()),book.getName(), book.getAuthor(), book.getReleaseCompany(), String.valueOf(book.getReleaseYear()), String.valueOf(book.isStatus())});
		}
	}
	private void showBookAmongAuthor() {
		if(list == null) {
			list = new Source().readFile();
		}
		LinkedList temp = list.sortByAuthor();
		for(int i = 0; i < temp.getLength(); i++) {
			Book book = temp.get(i);
			tableModel.addRow(new String[] {String.valueOf(book.getId()),book.getName(), book.getAuthor(), book.getReleaseCompany(), String.valueOf(book.getReleaseYear()), String.valueOf(book.isStatus())});
		}
	}
	private void showBookAmongName() {
		if(list == null) {
			list = new Source().readFile();
		}
		LinkedList temp = list.sortByName();
		for(int i = 0; i < temp.getLength(); i++) {
			Book book = temp.get(i);
			tableModel.addRow(new String[] {String.valueOf(book.getId()),book.getName(), book.getAuthor(), book.getReleaseCompany(), String.valueOf(book.getReleaseYear()), String.valueOf(book.isStatus())});
		}
	}
	private void showNotBorrowedBook() {
		if(list == null) {
			list = new Source().readFile();
		}
		LinkedList temp = list.getNotBorrowingBook();
		for(int i = 0; i < temp.getLength(); i++) {
			Book book = temp.get(i);
			tableModel.addRow(new String[] {String.valueOf(book.getId()),book.getName(), book.getAuthor(), book.getReleaseCompany(), String.valueOf(book.getReleaseYear()), String.valueOf(book.isStatus())});
		}
	}
	private void showBorrowedBook() {
		if(list == null) {
			list = new Source().readFile();
		}
		LinkedList temp = list.getBorrowingBook();
		for(int i = 0; i < temp.getLength(); i++) {
			Book book = temp.get(i);
			tableModel.addRow(new String[] {String.valueOf(book.getId()),book.getName(), book.getAuthor(), book.getReleaseCompany(), String.valueOf(book.getReleaseYear()), String.valueOf(book.isStatus())});
		}
	}
	private void showBook() {
		if(list == null) {
			list = new Source().readFile();
		}
		list = list.sort();
		for(int i = 0; i < list.getLength(); i++) {
			Book book = list.get(i);
			tableModel.addRow(new String[] {String.valueOf(book.getId()),book.getName(), book.getAuthor(), book.getReleaseCompany(), String.valueOf(book.getReleaseYear()), String.valueOf(book.isStatus())});
		}
	}
	private void showALLBook(LinkedList temp) {
		if(temp == null) {
			list = new Source().readFile();
			temp = new Source().readFile();
		}
		for(int i = 0; i < temp.getLength(); i++) {
			Book book = temp.get(i);
			tableModel.addRow(new Object[] {String.valueOf(book.getId()),book.getName(), book.getAuthor(), book.getReleaseCompany(), String.valueOf(book.getReleaseYear()), String.valueOf(book.isStatus())});
		}
	}
	
	private void addNewBook() {
		if(list == null) {
			showBook();
		}
		new GDBook("Thêm Mới Sách", list.getLength()).setVisible(true);
		this.dispose();
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == cbbShow) {
			SHOW_MODE = cbbShow.getSelectedIndex();
		}
		if(e.getSource() == cbbSearch) {
			SEARCH_MODE = cbbSearch.getSelectedIndex();
		}
	}
	
}

package ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.Source;
import model.Book;
import model.IConstants;
import model.LinkedList;

@SuppressWarnings("serial")
public class GDBorrowBook extends JFrame implements ActionListener{
	public static int WIDTH = 720;
	public static int HEIGHT = 436;
	
	private Box box, viewModeBox;
	private JButton btnBack, btnBorrow;
	private DefaultTableModel tableModel;
	private JTable table;
	
	
	public GDBorrowBook(String title) {
		super(title);
		mapWidgets();
		handleEvents();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);	}

	private void mapWidgets() {
		box = Box.createVerticalBox();
		box.setBorder(new EmptyBorder(new Insets(16, 16, 16, 16)));
		viewModeBox = Box.createHorizontalBox();	
		btnBack = new JButton("Trở Về");
		
		btnBorrow = new JButton("Mượn Sách");
		btnBorrow.setEnabled(false);
		viewModeBox.add(btnBack);
		viewModeBox.add(Box.createHorizontalStrut(4));
		viewModeBox.add(btnBorrow);
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
		
		JScrollPane sc = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnBorrow.setEnabled(true);
			}
		});
		this.add(sc , BorderLayout.CENTER);
		
		showNotBorrowedBook();
	}
	private void showNotBorrowedBook() {
		if(GiaoDien.list == null) {
			GiaoDien.list = new Source().readFile();
		}
		int count = tableModel.getRowCount();
		for(int i = count - 1; i >= 0 ;i-- ) {
			tableModel.removeRow(i);	
		}
		LinkedList temp = GiaoDien.list.getNotBorrowingBook();
		for(int i = 0; i < temp.getLength(); i++) {
			Book book = temp.get(i);
			tableModel.addRow(new String[] {String.valueOf(book.getId()),book.getName(), book.getAuthor(), book.getReleaseCompany(), String.valueOf(book.getReleaseYear()), String.valueOf(book.isStatus())});
		}
	}
	private void handleEvents() {
		btnBack.addActionListener(this);
		btnBorrow.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBack) {
			new GiaoDien("Quản Lý Sách").setVisible(true);
			this.dispose();
		}
		if(e.getSource() == btnBorrow) {
			int idx = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
			GiaoDien.list.getBookByID(idx).setStatus(false);
			showNotBorrowedBook();
			btnBorrow.setEnabled(false);
		}
	}
}

package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Book;
import model.IConstants;

@SuppressWarnings("serial")
public class GDBook extends JFrame implements ActionListener, ItemListener{
	public static int WIDTH = 436;
	public static int HEIGHT = 436;
	
	private JLabel lbID, lbName, lbAuthor, lbCompany, lbYear, lbStatus, lbIndex;
	private JTextField txtID, txtName, txtAuthor, txtCom, txtYear;
	private Box box;
	private Box boxID,boxName, boxAuthor,boxYear,boxStatus, boxCom, boxButton, boxIndex;
	private Dimension maxSize = null;
	private JButton btnAdd , btnReset, btnCancel;
	private JRadioButton rbtnTrue, rbtnFalse;
	private ButtonGroup bg;
	private JComboBox<String> cbb;
	private int newID = 0;
	private int POSITION_INSERT = 0;
	public GDBook(String title, int length){
		super(title);
		newID = length;
		mapWidgets();
		handleEvents();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	private void handleEvents() {
		btnAdd.addActionListener(this);
		btnReset.addActionListener(this);
		btnCancel.addActionListener(this);
		cbb.addItemListener(this);
	}
	
	private void mapWidgets() {
		box = Box.createVerticalBox();
		box.setBorder(new EmptyBorder(new Insets(16, 16, 16, 16)));
		
		lbID = new JLabel(IConstants.sID);
		txtID = new JTextField(10);
		txtID.setEditable(false);
		txtID.setText(String.valueOf(newID));
		
		lbName = new JLabel(IConstants.sNAME);
		txtName = new JTextField(20);
		
		lbAuthor = new JLabel(IConstants.sAUTHOR);
		txtAuthor = new JTextField(20);

		lbCompany = new JLabel(IConstants.sCOM);
		txtCom = new JTextField(20);

		lbYear = new JLabel(IConstants.sYEAR);
		txtYear = new JTextField(20);
		
		lbStatus = new JLabel(IConstants.sSTATUS);

		maxSize = getMaxSize(lbID, lbName, lbAuthor, lbCompany, lbYear, lbStatus);
		
		boxID = Box.createHorizontalBox();
		lbID.setPreferredSize(maxSize);
		boxID.add(lbID);
		boxID.add(Box.createHorizontalStrut(4));
		boxID.add(txtID);
		
		boxName = Box.createHorizontalBox();
		lbName.setPreferredSize(maxSize);
		boxName.add(lbName);
		boxName.add(Box.createHorizontalStrut(4));
		boxName.add(txtName);
		
		boxAuthor = Box.createHorizontalBox();
		lbAuthor.setPreferredSize(maxSize);
		boxAuthor.add(lbAuthor);
		boxAuthor.add(Box.createHorizontalStrut(4));
		boxAuthor.add(txtAuthor);
		
		boxCom = Box.createHorizontalBox();
		lbCompany.setPreferredSize(maxSize);
		boxCom.add(lbCompany);
		boxCom.add(Box.createHorizontalStrut(4));
		boxCom.add(txtCom);
		
		boxYear = Box.createHorizontalBox();
		lbYear.setPreferredSize(maxSize);
		boxYear.add(lbYear);
		boxYear.add(Box.createHorizontalStrut(4));
		boxYear.add(txtYear);
		
		rbtnTrue = new JRadioButton(IConstants.HAS_BOOK);
		rbtnFalse = new JRadioButton(IConstants.HAS_NOT_BOOK);
		bg = new ButtonGroup();
		bg.add(rbtnTrue);
		bg.add(rbtnFalse);
		rbtnFalse.setSelected(true);
		boxStatus = Box.createHorizontalBox();
		lbStatus.setPreferredSize(maxSize);
		boxStatus.add(lbStatus);
		boxStatus.add(rbtnTrue);
		boxStatus.add(Box.createHorizontalStrut(txtYear.getPreferredSize().width / 2));
		boxStatus.add(rbtnFalse);
		boxStatus.add(Box.createHorizontalStrut(txtYear.getPreferredSize().width / 2));
		
		
		
		boxIndex = Box.createHorizontalBox();
		lbIndex = new JLabel("Vị Trí Chèn: ");
		lbIndex.setPreferredSize(maxSize);
		
		String [] arrIndex = new String[newID + 1];
		for(int i = 0; i <= newID; i++) {
			arrIndex[i] = String.valueOf(i);
		}
		
		cbb = new JComboBox<>(arrIndex);
		boxIndex.add(lbIndex);
		boxIndex.add(Box.createHorizontalStrut(4));
		boxIndex.add(cbb);
		
		btnAdd = new JButton(IConstants.THEM_SACH);
		btnReset = new JButton(IConstants.DAT_LAI);
		btnCancel = new JButton(IConstants.HUY_BO);
		boxButton = Box.createHorizontalBox();
		boxButton.add(btnAdd);
		boxButton.add(Box.createHorizontalStrut(16));
		boxButton.add(btnReset);
		boxButton.add(Box.createHorizontalStrut(16));
		boxButton.add(btnCancel);
		
		
		box.add(boxID);
		box.add(Box.createVerticalStrut(16));
		box.add(boxName);
		box.add(Box.createVerticalStrut(16));
		box.add(boxAuthor);
		box.add(Box.createVerticalStrut(16));
		box.add(boxCom);
		box.add(Box.createVerticalStrut(16));
		box.add(boxYear);
		box.add(Box.createVerticalStrut(16));
		box.add(boxStatus);
		
		box.add(Box.createVerticalStrut(16));
		box.add(boxIndex);
		box.add(Box.createVerticalStrut(16));
		box.add(boxButton);
		box.add(Box.createVerticalStrut(16));
		this.add(box, BorderLayout.CENTER	);
	}

	private Dimension getMaxSize(JLabel ...lb) {
		Dimension max = new Dimension(), temp = null;
		for (JLabel jLabel : lb) {
			temp = jLabel.getPreferredSize();
			if(max.getWidth() < temp.getWidth()) max = temp;
		}
		return max;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnReset){
			resetInfo();
		}
		if(e.getSource() == btnCancel){
			new GiaoDien("Quản Lý Sách").setVisible(true);
			this.dispose();
		}
		if(e.getSource() == btnAdd) {
			if(!txtName.getText().equals("") && !txtYear.getText().equals("") && !txtAuthor.getText().equals("") && !txtCom.getText().equals("")) {
				int id = Integer.parseInt(txtID.getText().toString().trim());
				String name = txtName.getText().toString().trim();
				String aut = txtAuthor.getText().toString().trim();
				String com = txtCom.getText().toString().trim();
				int year = Integer.parseInt(txtYear.getText().toString().trim());
				boolean status = rbtnTrue.isSelected();
				addBook(id, name, aut, com, year, status);	
			}
		}
	}

	private void addBook(int id, String name, String author, String releaseCompany, int releaseYear, boolean status) {
		Book book = new Book(id, name, author, releaseCompany, releaseYear, status);
		if(POSITION_INSERT == 0) {
			GiaoDien.list.insertFirst(book);
		}else if(POSITION_INSERT == newID) {
			GiaoDien.list.insertLast(book);
		}else {
			GiaoDien.list.insertAfter(book, POSITION_INSERT);
		}
		new GiaoDien("Quản Lý Sách").setVisible(true);
		this.dispose();
	}

	private void resetInfo() {
		txtName.setText("");
		txtAuthor.setText("");
		txtCom.setText("");
		txtYear.setText("");
		rbtnFalse.setSelected(true);
		cbb.setSelectedIndex(0);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == cbb) {
			POSITION_INSERT = cbb.getSelectedIndex();
		}
	}	
	
}

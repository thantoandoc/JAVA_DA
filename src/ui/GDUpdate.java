package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Book;
import model.IConstants;

@SuppressWarnings("serial")
public class GDUpdate extends JFrame implements ActionListener {
	public static int WIDTH = 436;
	public static int HEIGHT = 436;
	
	private JLabel lbID, lbName, lbAuthor, lbCompany, lbYear, lbStatus;
	private JTextField txtID, txtName, txtAuthor, txtCom, txtYear;
	private Box box;
	private Box boxID,boxName, boxAuthor,boxYear,boxStatus, boxCom, boxButton;
	private Dimension maxSize = null;
	private JButton btnUpdate , btnReset, btnCancel;
	private JRadioButton rbtnTrue, rbtnFalse;
	private ButtonGroup bg;
	private Book oldBook;
	public GDUpdate(String title, Book book){
		super(title);
		oldBook = book;
		mapWidgets();
		handleEvents();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	private void handleEvents() {
		btnUpdate.addActionListener(this);
		btnReset.addActionListener(this);
		btnCancel.addActionListener(this);
	}
	
	private void mapWidgets() {
		box = Box.createVerticalBox();
		box.setBorder(new EmptyBorder(new Insets(16, 16, 16, 16)));
		
		lbID = new JLabel(IConstants.sID);
		txtID = new JTextField(10);
		txtID.setEditable(false);
		txtID.setText(String.valueOf(oldBook.getId()));
		
		lbName = new JLabel(IConstants.sNAME);
		txtName = new JTextField(20);
		txtName.setText(oldBook.getName());
		
		lbAuthor = new JLabel(IConstants.sAUTHOR);
		txtAuthor = new JTextField(20);
		txtAuthor.setText(oldBook.getAuthor());

		lbCompany = new JLabel(IConstants.sCOM);
		txtCom = new JTextField(20);
		txtCom.setText(oldBook.getReleaseCompany());

		lbYear = new JLabel(IConstants.sYEAR);
		txtYear = new JTextField(20);
		txtYear.setText(String.valueOf(oldBook.getReleaseYear()));
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
		if(oldBook.isStatus()) {
			rbtnTrue.setSelected(true);
		}else {
			rbtnFalse.setSelected(true);
		}
		
		
		btnUpdate = new JButton("Cập Nhật");
		btnReset = new JButton(IConstants.DAT_LAI);
		btnCancel = new JButton(IConstants.HUY_BO);
		boxButton = Box.createHorizontalBox();
		boxButton.add(btnUpdate);
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
		if(e.getSource() == btnUpdate) {
			if(!txtName.getText().equals("") && !txtYear.getText().equals("") && !txtAuthor.getText().equals("") && !txtCom.getText().equals("")) {
				int id = Integer.parseInt(txtID.getText().toString().trim());
				String name = txtName.getText().toString().trim();
				String aut = txtAuthor.getText().toString().trim();
				String com = txtCom.getText().toString().trim();
				int year = Integer.parseInt(txtYear.getText().toString().trim());
				boolean status = rbtnTrue.isSelected();
				updateBook(id, name, aut, com, year, status);	
			}
		}
	}

	private void updateBook(int id, String name, String author, String releaseCompany, int releaseYear, boolean status) {
		GiaoDien.list.get(id).setName(name);
		GiaoDien.list.get(id).setAuthor(author);
		GiaoDien.list.get(id).setReleaseCompany(releaseCompany);
		GiaoDien.list.get(id).setReleaseYear(releaseYear);
		GiaoDien.list.get(id).setStatus(status);
		new GiaoDien("Quản Lý Sách").setVisible(true);
		this.dispose();
	}

	private void resetInfo() {
		txtName.setText(oldBook.getName());
		txtAuthor.setText(oldBook.getAuthor());
		txtCom.setText(oldBook.getReleaseCompany());
		txtYear.setText(String.valueOf(oldBook.getReleaseYear()));
		if(oldBook.isStatus()) {
			rbtnTrue.setSelected(true);
		}else {
			rbtnFalse.setSelected(true);
		}
	}

}

package controller;

import model.Book;
import model.LinkedList;

public class Source {
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.insertFirst(new Book(1, "Le Cam", "Le Cam", "Gia Dinh", 1998, true));
		list.insertLast(new Book(2, "Bí Mật Bị Mất", "Lê Trung", "Gia Đình", 2017, true));
		list.insertFirst(new Book(1, "Le Cam", "Le Cam", "Gia Dinh", 1998, true));
		list.insertLast(new Book(3, "Bí Mật Bị Phát Hiện", "Lê Trung", "Gia Đình", 2017, true));
		list.insertFirst(new Book(0, "Bí Mật Còn Nguyên", "Le Cam", "Gia Dinh", 1998, true));
		list.printList();
	}
}

package controller;

import javax.swing.JFrame;

import ui.GiaoDien;

public class Class {
	public static int WIDTH = 720;
	public static int HEIGHT = 520;
	public static void main(String[] args) {
		GiaoDien giaoDien = new GiaoDien("Quan Ly Sach");
		giaoDien.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		giaoDien.setSize(WIDTH, HEIGHT);
		giaoDien.setLocation(200, 100);
		giaoDien.setVisible(true);
	}
}

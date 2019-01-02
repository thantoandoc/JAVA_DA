package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import model.Book;
import model.LinkedList;

public class Source {
	private static String inputURL = "C:\\Users\\Gracefully Broken\\Desktop\\input_data.txt";
	private static String outputURL = "C:\\Users\\Gracefully Broken\\Desktop\\output_data.txt";
	
	
	public LinkedList readFile() {
		LinkedList list = new LinkedList();
		try {
			InputStreamReader isReader = new InputStreamReader(new FileInputStream(inputURL));
			BufferedReader bufferedReader = new BufferedReader(isReader);
		
			String s = null;
			
			while((s = bufferedReader.readLine()) != null) {
				String [] a = s.split(";");
				Book book = new Book(Integer.parseInt(a[0]), a[1],a[2],a[3], Integer.parseInt(a[4]), Boolean.parseBoolean(a[5]));
				list.insertLast(book);
			}
			bufferedReader.close();
			isReader.close();
			return list;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void writeFile(LinkedList list) {
		try {
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(inputURL));
			BufferedWriter bw = new BufferedWriter(osw);
			int n = list.getLength();
			for(int i = 0; i < n; i++) {
				bw.write(list.get(i).toString());
			}
			bw.close();
			osw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
//		LinkedList list = new Source().readFile();
		LinkedList list = new LinkedList();
		list.insertLast(new Book(0, "Lập Trình Java", "BKĐN", "NXB Bách Khoa", 2014, true));
		list.insertLast(new Book(8, "Hãy Yêu Đi", "BKĐN", "Giới Tính Học", 2014, true));
		list.insertLast(new Book(1, "Lập Trình C++","CNTT", "NXB Bách Khoa _ Khoa CNTT", 2013, true));
		list.insertLast(new Book(2, "Lập Trình C", "T.N", "NXB Giáo Dục", 2012, true));
		list.insertLast(new Book(3, "Toán Học", "Giáo Sư ... ", "NXB Giáo Dục", 2014, true));
		list.insertLast(new Book(4, "Hãy Yêu Đi", "BKĐN", "Giới Tính Học", 2014, true));
		list.insertLast(new Book(7, "Hãy Yêu Đi", "BKĐN", "Giới Tính Học", 2014, true));
		list.insertLast(new Book(5, "Dạy Con Làm Giàu", "CNTT", "NXB Hà Nội", 2014, true));
		list.insertAfter(new Book(6, "Tư Duy Lập Trình", "BK TP_HCM", "NXB Giáo Dục", 2014, true), 3);
		list.deleteByAuthor("CNTT");
		System.out.println("---------------------");
		list.printList();
		
		//new Source().writeFile(list);
	}
}

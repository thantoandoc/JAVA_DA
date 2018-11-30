package model;

public class Book {
	private int id, releaseYear;
	private String name, author, releaseCompany;
	private boolean status;
	public Book() {
		super();
	}
	public Book(int id, String name, String author, String releaseCompany, int releaseYear, boolean status) {
		super();
		this.id = id;
		this.releaseYear = releaseYear;
		this.name = name;
		this.author = author;
		this.releaseCompany = releaseCompany;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getReleaseCompany() {
		return releaseCompany;
	}
	public void setReleaseCompany(String releaseCompany) {
		this.releaseCompany = releaseCompany;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return id + " " + name + " " + author + " " + releaseCompany + " " + releaseYear;
	}
}

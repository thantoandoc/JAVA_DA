package model;

public class LinkedList {
	
	private BookNode head;
	
	public LinkedList() {
		head = null;
	}
	public LinkedList(BookNode h) {
		head = h;
	}
	
	
	/*
	 * add Book into First of LinkedList
	 * book : object to add
	 * 
	 */
	// getLength
	public int getLength(){
		BookNode temp = head;
		int count = 0;
		
		while( temp != null ){
			count += 1;
			temp = temp.next;
		}
		return count;
	}
	public void insertFirst(Book book) {
		if(head == null) {
			head = new BookNode(book);
			return;
		}
		BookNode newNode = new BookNode(book);
		newNode.next = head;
		head = newNode;
	}

	/*
	 * add Book into after one position of LinkedList
	 * book : object to add
	 * 
	 */
	public void insertAfter(Book book, int pos) {
		if(head == null) {
			head = new BookNode(book);
			return;
		}
		
	}
	/*
	 * add Book into Last of LinkedList
	 * book : object to add
	 * 
	 */
	public void insertLast(Book book) {
		/*
		 * Then check Node head : 
		 * 	if head = null -> then head <- newNode
		 * 	else head != null -> head.next = newNode
		 * 						
		 */
		if(head == null) {
			head = new BookNode(book);
			return;
		}
		// create a new Node when head != null;
		BookNode newNode = new BookNode(book);
		
		// last node for find last Next and add newNode 
		BookNode last = head;
		while(last.next != null) last = last.next;
		
		last.next = newNode;
	}
	
	
	
	public void deleteFirst(){
		if( head == null) return;
		head = head.next;
	}
	public void deleteLast(){
		 if( head == null ) return;
		 BookNode last = head, prev = null;
		 while(last.next != null){
			 prev = last;
			 last = last.next;
		 }
		 if(prev == null){
			 head = null;
			 return;
		 }
		 prev.next = null;
		 
	}
		
// 4a.Search By Book Name
	public LinkedList searchByName(String name){
		LinkedList list = new LinkedList();
		BookNode temp = head;
		while (temp != null) {
			if(temp.data.getName().equals(name)){
				list.insertLast(temp.data);
			}
			temp = temp.next;
		}
		return list;
	}
// 4b.Search By Author
	public LinkedList searchByAuthor(String author){
		LinkedList list = new LinkedList();
		BookNode temp = head;
		while (temp != null) {
			if(temp.data.getAuthor().equals(author)){
				list.insertLast(temp.data);
			}
			temp = temp.next;
		}
		return list;
	}
// 4c.Search By Release Company
	public LinkedList searchByReleaseCompany(String company){
		LinkedList list = new LinkedList();
		BookNode temp = head;
		while (temp != null) {
			if(temp.data.getReleaseCompany().equals(company)){
				list.insertLast(temp.data);
			}
			temp = temp.next;
		}
		return list;
	}
	
	public void printList() {
		BookNode temp = head;
		while (temp != null) {
			System.out.println(temp.data);
			temp = temp.next;
		}
	}
//5a. Xem toàn bộ danh mục sách trong danh sách 
	public LinkedList getAllBook() {
		LinkedList list = new LinkedList();
		BookNode temp = head;
		while (temp != null) {
			list.insertLast(temp.data);
			temp = temp.next;
		}
		return list;
	}
	
// 5b.Xem những cuốn sách đang cho mượn 
	public LinkedList getBorrowingBook() {
		LinkedList list = new LinkedList();
		BookNode temp = head;
		while (temp != null) {
			if(!temp.data.isStatus()){
				list.insertLast(temp.data);
			}
			temp = temp.next;
		}
		return list;
	}
//5c.  Xem những cuốn sách chưa cho mượn 
	public LinkedList getNotBorrowingBook() {
		LinkedList list = new LinkedList();
		BookNode temp = head;
		while (temp != null) {
			if(temp.data.isStatus()){
				list.insertLast(temp.data);
			}
			temp = temp.next;
		}
		return list;
	}
//5d. Xây dựng các chức năng
	//5d1.Sắp xếp Theo vần alphabet của tên sách
	
	public LinkedList sortByName(){
		BookNode p = head;
		BookNode q = null;
		for(; p.next != null; p = p.next) {
			for(q = p.next; q != null; q = q.next) {
				if(p.data.getName().compareToIgnoreCase(q.data.getName()) > 0 
			|| p.data.getName().compareToIgnoreCase(q.data.getName()) == 0 && p.data.getId() > q.data.getId() ) {
					Book temp = p.data;
					p.data = q.data;
					q.data = temp;	
				}
			}
		}
		return new LinkedList(head);
	}
	//5d2. Sắp xếp theo tên tác giả
	public LinkedList sortByAuthor(){
		BookNode p = head;
		BookNode q = null;
		for(; p.next != null; p = p.next) {
			for(q = p.next; q != null; q = q.next) {
				if(p.data.getAuthor().compareToIgnoreCase(q.data.getAuthor()) > 0 
			|| p.data.getAuthor().compareToIgnoreCase(q.data.getAuthor()) == 0 && p.data.getId() > q.data.getId() ) {
					Book temp = p.data;
					p.data = q.data;
					q.data = temp;	
				}
			}
		}
		return new LinkedList(head);
	}
	
	//5d3. Sắp xếp theo tên nhà xuất bản
		public LinkedList sortByReleaseCompany(){
			BookNode p = head;
			BookNode q = null;
			for(; p.next != null; p = p.next) {
				for(q = p.next; q != null; q = q.next) {
					if(p.data.getReleaseCompany().compareToIgnoreCase(q.data.getReleaseCompany()) > 0 
				|| p.data.getReleaseCompany().compareToIgnoreCase(q.data.getReleaseCompany()) == 0 && p.data.getId() > q.data.getId() ) {
						Book temp = p.data;
						p.data = q.data;
						q.data = temp;	
					}
				}
			}
			return new LinkedList(head);
		}
		//5d4. Sắp xếp theo tên năm xuất bản
		public LinkedList sortByReleaseYear(){
			BookNode p = head;
			BookNode q = null;
			for(; p.next != null; p = p.next) {
				for(q = p.next; q != null; q = q.next) {
					if(p.data.getReleaseYear() > q.data.getReleaseYear() 
				|| p.data.getReleaseYear() == q.data.getReleaseYear() && p.data.getId() > q.data.getId() ) {
						Book temp = p.data;
						p.data = q.data;
						q.data = temp;	
					}
				}
			}
			return new LinkedList(head);
		}
		//5d5. Sắp xếp theo tên năm xuất bản
		public LinkedList sort(){
			BookNode p = head;
			BookNode q = null;
			for(; p.next != null; p = p.next) {
				for(q = p.next; q != null; q = q.next) {
					if(p.data.getId() > q.data.getId()) {
						Book temp = p.data;
						p.data = q.data;
						q.data = temp;	
					}
				}
			}
			return new LinkedList(head);
		}

	/*
	 * Nested Class
	 */
	class BookNode {
		private Book data;
		private BookNode next;
		public BookNode() {
		}
		public BookNode(Book data) {
			super();
			this.data = data;
			this.next = null;
		}
		
	}
}

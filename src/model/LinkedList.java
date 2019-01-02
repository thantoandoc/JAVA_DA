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
	public Book get(int idx) {
		if(idx < 0 || idx >= getLength()) {
			return null;
		}
		BookNode temp = head;
		int count = 0;
		while(count != idx) {
			temp = temp.next;
			count++;
		}
		return temp.data;
	}
	public Book getBookByID(int idx) {
		if(idx < 0 || idx >= getLength()) {
			return null;
		}
		BookNode temp = head;
		while(temp != null) {
			if(temp.data.getId() == idx) break;
			temp = temp.next;
		}
		return temp.data;
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
		if(pos < 1 || pos > getLength() + 1) {
			return;
		}
		if(head == null) {
			head = new BookNode(book);
			return;
		}
		BookNode temp = head, prev = null;
		BookNode node = new BookNode(book);
		while(pos != 0) {
			pos = pos - 1;
			prev = temp;
			temp = temp.next;
		}
		
		node.next = temp;
		prev.next = node;
		
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
	public void deleteByID(int id) {
		if(id < 0 || head == null) {
			return;
		}
		
		BookNode temp = head, prev = null;
		if (temp != null && temp.data.getId() == id) 
        { 
            head = temp.next; // Changed head 
            return; 
        } 
		
		while(temp != null && temp.data.getId() != id) {
			prev = temp; 
            temp = temp.next;
		}
		if (temp == null) return; 
		prev.next = temp.next;
	}
	public void deleteAfterID(int id) {
		
		if(id < 0 || head == null) {
			return;
		}
		
		BookNode temp = head, prev = null;
		if (temp != null && temp.data.getId() == id) 
        { 
            head.next = temp.next.next; // Changed head 
            return; 
        } 
		
		while(temp != null && temp.data.getId() != id) {
			prev = temp; 
            temp = temp.next;
		}
		if (temp == null) return; 
		prev = temp; 
        temp = temp.next;
		prev.next = temp.next;
	}
	public void deleteByName(String s) {
		if( head == null) {
			return;
		}
		
		BookNode temp = head, prev = null;
		if (temp != null && temp.data.getName().equals(s)) 
        { 
            head = temp.next; // Changed head 
            return; 
        } 
		
		while(temp != null && !temp.data.getName().equals(s)) {
			prev = temp; 
            temp = temp.next;
		}
		if (temp == null) return; 
		prev.next = temp.next;
	}
	public void deleteByAuthor(String s) {
		if( head == null) {
			return;
		}
		
		BookNode temp = head, prev = null;
		while(head != null && head.data.getAuthor().equals(s)) {
			head = head.next;
		}
		while(temp.next != null) {
			if(temp.data.getAuthor().equals(s)) {
				
				if(prev != null && temp != null) {
					 prev.next = temp.next;
				}
				temp = temp.next;
				
			} else {
				prev = temp;
				temp = temp.next;
			}
      	}
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
			if(temp.data.getName().contains(name)){
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
			if(temp.data.getAuthor().contains(author)){
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
			if(temp.data.getReleaseCompany().contains(company)){
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
		public BookNode(BookNode node) {
			this.data = node.data;
			this.next = node.next;
		}
		public BookNode(Book data) {
			super();
			this.data = data;
			this.next = null;
		}
		
	}
}

package model;

public class LinkedList {
	
	private BookNode head;
	
	/*
	 * add Book into First of LinkedList
	 * book : object to add
	 * 
	 */
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
	
	public void printList() {
		BookNode temp = head;
		while (temp != null) {
			System.out.println(temp.data);
			temp = temp.next;
		}
	}
	/*
	 * Nested Class
	 */
	class BookNode {
		Book data;
		BookNode next;
		public BookNode() {
		}
		public BookNode(Book data) {
			super();
			this.data = data;
			this.next = null;
		}
	
	}
}

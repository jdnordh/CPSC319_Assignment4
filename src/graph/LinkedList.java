package graph;

public class LinkedList<D> {
	/** points to the start of the list*/
	private Node<D> head;
	/** Points to the end of the list*/
	private Node<D> tail;
	
	private int size;
	
	/** Creates empty linked list */
	public LinkedList(){
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * Get a node's next node
	 * @return The next node
	 */
	public Node<D> head(){
		return head;
	}
	/**
	 * Set head
	 * @param n New head
	 */
	public void setHead(Node<D> n){
		head = n;
	}
	/** get the size of the list */
	public int length(){
		return size;
	}
	
	/**
	 * Remove entry with certain data
	 * @param s Data entry
	 */
	public void remove(D s){
		if (head == null) return;
		if (head.getData().equals(s)){
			head = head.getNext();
			size--;
			return;
		}
		Node<D> temp = head;
		while(temp.getNext() != null && !temp.getNext().getData().equals(s)){
			temp = temp.getNext();
		}
		if (temp.getNext() == null) {
			return;
		}
		else if (temp.getNext().getNext() == null){
			temp.setNext(null);
			size--;
			return;
		}
		else if (temp.getNext().getData().equals(s)){
			temp.setNext(temp.getNext().getNext());
			size--;
			return;
		}
	}
	
	/** Removes first item of the list */
	public void removeFirst(){
		if (head == null) return;
		if (head.getNext() == null) {
			size--;
			head = null;
		}
		else {
			head = head.getNext();
			size--;
		}
	}
	
	/**
	 * Add node to the end of the linked list
	 * @param d String to be put in the node
	 */
	public void addLast(D d){
		if (head == null){
			head = new Node<D>(d);
			tail = head;
			size++;
			return;
		}
		Node<D> temp = tail;
		tail = new Node<D>(d);
		temp.setNext(tail);
		size++;
	}
	
	/**
	 * Add node to the end of the linked list
	 * @param d String to be put in the node
	 */
	public void add(D d){
		if (head == null){
			head = new Node<D>(d);
			tail = head;
			size++;
			return;
		}
		Node<D> temp = tail;
		tail = new Node<D>(d);
		temp.setNext(tail);
		size++;
	}
	/**
	 * Add data copy of a node to the end of the linked list
	 * @param d String to be put in the node
	 */
	public void addLastC(Node<D> n){
		Node<D> temp2 = new Node<D>(n.getData());
		if (head == null){
			head = temp2;
			tail = head;
			size++;
			return;
		}
		Node<D> temp = tail;
		tail = temp2;
		temp.setNext(tail);
		size++;
	}
	
	/**
	 * Add a copy of a node to the end of a list
	 * @param n Node to be added
	 */
	public void addLast(Node<D> n){
		Node<D> temp2 = new Node<D>(n);
		if (head == null){
			head = temp2;
			tail = head;
			size++;
			return;
		}
		Node<D> temp = tail;
		tail = temp2;
		temp.setNext(tail);
		size++;
	}
	/**
	 * Adds a node at the start of the linked list
	 * @param d String to be added into the first node
	 */
	public void addFirst(D d){
		if (head == null){
			head = new Node<D>(d);
			tail = head;
			size++;
			return;
		}
		Node<D> temp = head;
		head = new Node<D>(d);
		head.setNext(temp);
		size++;
	}
	/**Prints the contents out to the console */
	public void print(){
		for (Node<D> temp=head; temp != null; temp = temp.getNext()){
			System.out.println(temp.getData());
		}
		System.out.println();
	}
	
}

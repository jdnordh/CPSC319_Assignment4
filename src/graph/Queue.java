package graph;


public class Queue<D> {
	QNode<D> head;
	QNode<D> tail;
	boolean empty;
	
	public Queue(){
		head = null;
		tail = head;
		empty = true;
	}
	
	/** check if the queue is empty */
	public boolean isEmpty(){
		return empty;
	}
	
	/** add another entry to the end */
	public void enQueue(D d){
		if (head != null){
			QNode<D> temp = tail;
			tail = new QNode<D>(d);
			temp.setNext(tail);
		}
		else {
			head = new QNode<D>(d);
			tail = head;
			empty = false;
		}
	}
	
	/** remove and return the first entry */
	public D deQueue(){
		if (head != null){
			D temp = head.getData();
			head = head.next();
			if (head == null) empty = true;
			return temp;
		}
		return null;
	}
	
	/** prints and destroys the queue */
	public void printD(){
		while (!empty) {
			System.out.println(deQueue().toString());
		}
	}
	
	/** prints the queue */
	public void print(){
		QNode<D> temp = head;
		while (temp != null){
			System.out.println(temp.getData().toString());
			temp = temp.next();
		}
	}
}

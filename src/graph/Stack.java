package graph;

public class Stack<D> {
	SNode<D> head;
	SNode<D> tail;
	boolean empty;
	
	public Stack(){
		head = null;
		tail = head;
		empty = true;
	}
	
	/** check if the queue is empty */
	public boolean isEmpty(){
		return empty;
	}
	
	/** add another entry to the end */
	public void push(D d){
		if (head != null){
			SNode<D> temp = head;
			head = new SNode<D>(d);
			head.setprev(temp);
		}
		else {
			head = new SNode<D>(d);
			tail = head;
			empty = false;
		}
	}
	
	/** remove and return the first entry */
	public D pop(){
		if (head != null){
			D temp = head.getData();
			head = head.prev();
			if (head == null) empty = true;
			return temp;
		}
		return null;
	}
	
	/** get the top data without destroying it */
	public D getTop(){
		return head.data;
	}
	
	/** prints and destroys the queue */
	public void printD(){
		while (!empty) {
			System.out.println(pop().toString());
		}
	}
	
	/** prints the queue */
	public void print(){
		SNode<D> temp = head;
		while (temp != null){
			System.out.println(temp.getData().toString());
			temp = temp.prev();
		}
	}
	
	/** reverses the stack, uses a queue */
	public void reverse(){
		if (head != null && tail != null){
			Queue<D> temp = new Queue<D>();
			while (!this.isEmpty()){
				temp.enQueue(this.pop());
			}
			while (!temp.isEmpty()){
				this.push(temp.deQueue());
			}
		}
	}
}

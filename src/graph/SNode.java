package graph;


public class SNode<D> {
	SNode<D> prev;
	D data;
	
	/** Copy constructor for Data */
	public SNode(D d){
		data = d;
		prev = null;
	}
	
	/** Copy constructor for Node*/
	public SNode(SNode<D> n){
		data = n.getData();
		prev = null;
	}
	
	/** get the data */
	public D getData(){
		return data;
	}
	
	/** Set the data */
	public void setData(D d){
		data = d;
	}
	
	/** get the prev node */
	public SNode<D> prev(){
		return prev;
	}
	
	/** set the prev node */
	public void setprev(SNode<D> n){
		prev = n;
	}
}

package graph;

public class GInt implements Comparable<GInt>{
	private int value;

	public GInt(int k){
		value = k;
	}
	
	// Getters and setters
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int compareTo(GInt o) {
		return value - o.getValue();
	}
	
	public String toString(){
		return new String(Integer.toString(value));
	}
	
	public boolean equals(Object o){
		if (!(o instanceof GInt)) return false;
		GInt temp = (GInt) o;
		if (this.compareTo(temp) == 0) return true;
		else return false;
	}
}

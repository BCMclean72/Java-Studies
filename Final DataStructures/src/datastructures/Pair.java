package datastructures;

public class Pair<T,U> {

	public Pair( T first, U second ) {
		this.first = first;
		this.second = second;
	}
	
	public Pair() {
		this.first = null;
		this.second = null;
	}
	
	public T first;
	public U second;
}

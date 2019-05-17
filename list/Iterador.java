package list;

import java.util.Iterator;

public class Iterador implements Iterator<Object>{
	
	private Node nodo;
	
	public Iterador(Node first) {
		nodo = first;
	}
	
	public boolean hasNext() {
		return nodo != null;
	}
	public Object next() {
		Object info = nodo.getInfo();
		nodo = nodo.next();
		return info;		
	}

}

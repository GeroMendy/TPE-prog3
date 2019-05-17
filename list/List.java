package list;

public class List {

	private Node first;
	private int size;
	
	public List() {
		first=null;
		size=0;
	}
	
	public void add(Object info) {
		Node aux = new Node(first,info);
		first = aux;
		size++;
	}
	public Object get(int pos) {
		Node aux = first;
		for(int i=1;i<pos;i++) {
			aux = aux.next();
		}
		return aux.getInfo();
	}
	public void remove(int pos) {
		Node sig = first.next();
		if(pos==0) {
			first = sig;
		}else {
			Node ant=first;
			for(int i=1;i<pos;i++) {
				ant = sig;
				sig = sig.next();
			}
			ant.setNext(sig);
		}
		size--;
	}
	public int getSize() {
		return size;
	}
	public Iterador iterator(){
		return new Iterador(first);
	}
	public List reverseCopy() {
		List l=new List();
		Iterador it=iterator();
		while(it.hasNext()) {
			l.add(it.next());
		}
		return l;
	}
	public void removeFront() {
		if(first!=null)first=first.next();
	}

}

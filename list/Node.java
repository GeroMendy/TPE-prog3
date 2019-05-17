package list;

public class Node {
	
	private Node next;
	private Object info;

	public Node(Node next,Object info) {
		this.next = next;
		this.info = info;
	}
	
	public void setInfo(Object info) {
		this.info = info;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public Object getInfo() {
		return info;
	}
	public Node next() {
		return next;
	}
	public boolean hasNext() {
		return next!=null;
	}
	
	
	public void p() {
		System.out.print(info+"   ");
		if(hasNext())next.p();
	}
}

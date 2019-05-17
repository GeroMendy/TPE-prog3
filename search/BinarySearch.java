package search;

public class BinarySearch {
	
	private Comparable[]arr;
	private Comparable x;
	
	public Comparable search(Comparable[]arr,Comparable example) {
		this.arr=arr;
		x=example;
		int aux=binarySearch(0,arr.length-1);
		Comparable respuesta;
		if(aux<0) {
			respuesta=null;
		}else {
			respuesta = arr[aux];
		}
		this.arr=null;
		return respuesta;
	}
	private int binarySearch(int min,int max) {
		if(min<=max) {
			int mid=(min+max)/2;
			int aux=arr[mid].compareTo(x);
			if(aux<0) return binarySearch(min,mid-1);
			else if(aux>0) return binarySearch(mid+1,max);
			else return mid;
		}else return -1;
	}

}

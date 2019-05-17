package sort;

public class MergeSort {

	private Comparable[]base,aux;
	private Comparator comp;
	private int size;

	public void sort(Comparable[]arr,Comparator c) {
		comp=c;
		sort(arr);
		comp=null;
	}
	public void sort(Comparable[]arr) {
		size=arr.length;
		base=arr;
		aux=new Comparable[size];
		mergesort(0,size-1);
		base=null;
		aux=null;
	}

	private void mergesort(int min,int max) {
		if(min<max) {
			int mid=(min+max)/2;
			mergesort(min,mid);
			mergesort(mid+1,max);
			merge(min,mid,max);
		}
	}

	private void merge(int min,int mid,int max) {
		for(int i=min;i<=max;i++) {
			aux[i]=base[i];
		}
		int i=min,j=mid+1,k=min;
		while(i<=mid&&j<=max) {
			if(switched(aux[i],aux[j])) {
				base[k]=aux[j];
				j++;
			}else {
				base[k]=aux[i];
				i++;
			}
			k++;
		}
		while(i<=mid) {
			base[k]=aux[i];
			i++;
			k++;
		}
		while(j<=max) {
			base[k]=aux[j];
			j++;
			k++;
		}
	}

	private boolean switched(Comparable a1,Comparable a2) {
		int aux;
		if(a1==null||a2==null)return false;
		else {
			if(comp!=null) {
				aux=comp.compare(a1, a2);
			}else {
				aux=a1.compareTo(a2);
			}
			return aux<0;
		}
	}
}

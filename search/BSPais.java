package search;
import base.Aeropuerto;

public class BSPais {
	
	private Aeropuerto[]arr;
	private int minC,maxC;
	private int size;
	private String x;
	
	public Aeropuerto[] search(Aeropuerto[]a,String pais) {
		Aeropuerto[]respuesta=null;
		size=a.length;
		arr=a;
		x=pais;
		minC=size;
		maxC=-1;
		binarySearch(0,size-1);
		if(minC<=maxC) {
			respuesta=new Aeropuerto[maxC-minC+1];
			for(int i=minC;i<=maxC;i++) {
				respuesta[i-minC]=arr[i];
			}
		}
		arr=null;
		return respuesta;
	}
	
	private void binarySearch(int min,int max) {
		
		if(min<=max) {
			int mid=(min+max)/2;
			int aux=arr[mid].getPais().compareTo(x);
			if(aux==0) {
				boolean nuevoMin=(minC>mid);
				if(nuevoMin)minC=mid;
				if(maxC<mid) {
					maxC=mid;
					binarySearch(mid+1,max);
				}
				if(nuevoMin)binarySearch(min,mid-1);
			}
			else if(aux<0)binarySearch(mid+1,max);
			else binarySearch(min,mid-1);
		}
	}

}
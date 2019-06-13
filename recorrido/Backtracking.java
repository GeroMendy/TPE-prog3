package recorrido;
import base.*;
import search.BinarySearch;
import list.List;

public class Backtracking {

	private List sc,camino;
	private int[]visitado;
	private int size;
	private Grafo gr;
	private Reserva[] rsvs;
	private double kmTot,kmActual;
	
	public List BactrackingServicio2(Grafo grafo,int size,String x,Aeropuerto or,Aeropuerto de,Reserva[]reservas) {
		this.size=size;
		rsvs=reservas;
		sc=new List();
		camino=new List();
		gr=grafo;
		visitado=new int[size];
		for(int i=0;i<size;i++) {
			visitado[i]=0;
		}
		visitado[de.id()]=1;
		backServ2(or.id(),x);
		return sc;
	}
	private void backServ2(int o,String x) {
		Ruta rutaActual;
		for(int i=0;i<size;i++) {
			rutaActual=gr.get(o, i);
			if(rutaActual!=null&&visitado[i]>=0) {
				if(puedeEvitarAerolinea(rutaActual,x)) {
					camino.add(rutaActual);
					if(visitado[i]>0) {
						sc.add(camino.reverseCopy());
					}else {
						visitado[i]=-1;
						backServ2(i,x);
						visitado[i]=0;
					}
					camino.removeFront();
				}
			}
		}
	}
	
	public List bactrackingAgenteViajero(Grafo grafo,int size) {
		this.size=size;
		gr=grafo;
		sc=null;
		camino=new List();
		kmTot=-1;
		kmActual=0;
		visitado=new int[size];
		for(int i=1;i<size;i++) {
			visitado[i]=0;
		}
		backAV(0,size-1);
		visitado=null;
		camino=null;
		gr=null;
		return sc;
	}	
	private void backAV(int actual,int restantes) {
		Ruta r;
		double kmAux;
		if(restantes<=0) {
			r=gr.get(actual,0);
			
			if(r!=null) {
				kmAux= r.distancia();
				kmActual += kmAux;
				if(kmTot<0||kmTot>kmActual) {
					camino.add(r);					
					kmTot=kmActual;
					
					p(r,true);	//Imprimir por pantalla.
					System.out.println("Distancia total: "+kmTot+"km.\n");
					
					sc=camino.reverseCopy();
					
					camino.removeFront();
					kmActual-=kmAux;
					
					p(r,false);
				}
			}
		}
		else {
			for (int i=1;i<size;i++) {	//inicializo en 1 para no tener que revisar el primer origen y rechazarlo en cada recursion
				r=gr.get(actual,i);
				if(visitado[i]==0&&r!=null) {
					kmAux=r.distancia();
					kmActual+=kmAux;
					visitado[i]=1;
					restantes--;
					camino.add(r);
					
					p(r,true);
					
					backAV(i,restantes);
					kmActual-=kmAux;
					visitado[i]=0;
					restantes++;
					camino.removeFront();
					
					p(r,false);
				}
			}
		}
	}
	
	private boolean puedeEvitarAerolinea(Ruta r,String x) {
		String[]keys=r.keyAerolineas();
		boolean posible=false;
		Reserva rsv=(Reserva)new BinarySearch().search(rsvs, new Reserva(r.getOrigen(),r.getDestino(),x,0)); 
		int i=0;
		while(!posible&&i<keys.length) {
			posible=!keys[i].equals(x);
			if(posible) {
				posible=(rsv.pasajesLibres(r.getAsientos(keys[i])))>0;
			}
			i++;
		}
		return posible;
	}
	private void p(Ruta r,boolean ida) {
		String direccion;
		if(ida)direccion=" - - - > ";
		else direccion=" < - X - ";
		System.out.println(r.getOrigen().getName()+direccion+r.getDestino().getName()+" distancia ruta: "+r.distancia()+"km, distancia total hasta ahora: "+kmActual+" km.");
	}
}

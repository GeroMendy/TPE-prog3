package base;

public class Grafo {

	private Ruta[][]rutas;
	private int size;
	
	public Grafo(int size) {
		this.size=size;
		rutas=new Ruta[size][size];
	}
	public void setRuta(Ruta r) {
		if(r!=null) rutas[r.getOrigen().id()][r.getDestino().id()]=r;
	}
	public Ruta get(Aeropuerto or,Aeropuerto de) {
		if(or!=null&&de!=null)return rutas[or.id()][de.id()];
		else return null;
	}
	public Ruta get(int or,int de) {
		if((or>=0 && or<size) && (de>=0 && de<size))return rutas[or][de];
		else return null;
	}
}

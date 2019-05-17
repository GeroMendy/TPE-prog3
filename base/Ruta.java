package base;
import java.util.HashMap;

public class Ruta {

	private Aeropuerto or,de;
	private boolean cab;
	private double dist;
	private HashMap<String,Integer> aeroln;
	
	public Ruta(Aeropuerto origen,Aeropuerto destino,double distancia,int cabotaje) {
		or=origen;
		de=destino;
		cab=cabotaje>0;
		dist=distancia;
		aeroln=new HashMap<String,Integer>();
	}
	
	public void addAerolinea(String name,int pasajes) {
		aeroln.put(name, pasajes);
	}
	public Aeropuerto getOrigen() {
		return or;
	}
	public Aeropuerto getDestino() {
		return de;
	}
	public double distancia() {
		return dist;
	}
	public boolean cabotaje() {
		return cab;
	}
	public String[] keyAerolineas() {
		Object[]arrAux=aeroln.keySet().toArray();
		int size=arrAux.length;
		String[]respuesta=new String[size];
		for(int i=0;i<size;i++) {
			respuesta[i]=(String)arrAux[i];
		}
		return respuesta;
	}
	public int getAsientos(String aerolinea) {
		int asientos=0;
		if(aeroln.containsKey(aerolinea))asientos=aeroln.get(aerolinea);
		return asientos;
	}
	public boolean puedeEvitarAerolinea(String aerolinea) {
		String[]keys=keyAerolineas();
		int i=0;
		boolean posible=false;
		while(!posible&&i<keys.length) {
			posible=aerolinea.equals(keys[i]);
			i++;
		}
		return posible;
	}
	public String toString() {
		String base=("( "+or.getName()+" - "+de.getName()+" )"+" | dist: "+dist);
		String[]keys=this.keyAerolineas();
		for(int i=0;i<keys.length;i++) {
			if(i==0)base=base+" | Aerolineas: [";
			base=base+" "+keys[i]+" : "+aeroln.get(keys[i]);
			if(i==keys.length-1)base=base+" ]";
		}
		return base;
	}
}

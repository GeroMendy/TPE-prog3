package base;

public class Reserva implements Comparable<Reserva>{

	private Aeropuerto or,de;
	private String aeroln;
	private int rsv;
	
	public Reserva(Aeropuerto origen,Aeropuerto destino,String aerolinea,int reservados) {
		or=origen;
		de=destino;
		aeroln=aerolinea;
		rsv=reservados;
	}
	public Aeropuerto getOrigen() {
		return or;
	}
	public Aeropuerto getDestino() {
		return de;
	}
	public String getAerolinea() {
		return aeroln;
	}
	public int pasajesLibres(int todos) {
		return todos-rsv;
	}
	public int compareTo(Reserva otra) {
		if(otra!=null) {
			int aux=aeroln.compareTo(otra.getAerolinea());
			if(aux==0) {
				aux=or.compareTo(otra.getOrigen());
				if(aux==0)aux=de.compareTo(otra.getDestino());
			}
			return aux;
		}else return 1;
	}
	public int compareTo(Comparable otra) {
		try {
			return compareTo((Reserva)otra);
		}catch(Exception e) {
			return 1;
		}
	}
	public String toString() {
		return or.getName()+" - "+de.getName()+" : "+aeroln+"("+rsv+")";
	}

	public void p() {
		System.out.println(this.toString());
	}
}

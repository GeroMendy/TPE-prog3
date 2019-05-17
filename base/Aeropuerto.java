package base;

public class Aeropuerto implements Comparable<Aeropuerto>{

	private static int numBase=0;

	private String name,ciudad,pais;
	private int id;

	public Aeropuerto(String name,String ciudad,String pais) {
		this.name=name;
		this.ciudad=ciudad;
		this.pais=pais;
		id=numBase;
		numBase++;
	}

	public int compareTo(Aeropuerto otro) {
		if(otro!=null)return this.name.compareTo(otro.getName());
		else return 1;
	}

	public String getName() {
		return name;
	}
	public String getCiudad() {
		return ciudad;
	}
	public String getPais() {
		return pais;
	}
	public int id() {
		return id;
	}
	public String toString() {
		return name+" ("+ciudad+","+pais+")";
	}
}

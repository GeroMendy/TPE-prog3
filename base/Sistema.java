package base;
import csvTools.CSVTools;
import list.List;
import list.Iterador;
import search.*;
import sort.*;

public class Sistema {

	private Aeropuerto[]arrName,arrPais;
	private Ruta[][] rutas;
	private Reserva[]rsvs;
	private int size;
	
	public Sistema(List aeropuertos,List vuelos,List reservas) {
		size = aeropuertos.getSize();
		initAerop(aeropuertos);
		initRutas(vuelos);
		initReservas(reservas);
	}
	private void initAerop(List l) {
		arrName=new Aeropuerto[size];
		arrPais=new Aeropuerto[size];
		
		MergeSort ms=new MergeSort();
		Iterador it=l.iterator();
		
		Aeropuerto aeropActual;
		String[]data;
		
		for(int i=0;i<size;i++) {
			try {
				data=(String[])it.next();
				aeropActual=new Aeropuerto(data[0],data[1],data[2]);
				arrName[i]=aeropActual;
				arrPais[i]=aeropActual;
				
			}catch(Exception e) {
				System.out.println("initialize() Aeropuertos --- "+e); 
			}
		}
		ms.sort(arrName,new CompName());
		ms.sort(arrPais,new CompPais());
	}
	private void initRutas(List l) {

		rutas=new Ruta[size][size];
		String[] data;
		Iterador it=l.iterator();
		Aeropuerto or,de;
		Ruta rutaActual;
		while(it.hasNext()) {
			try {
				data=(String[])it.next();
				or=getAeropuerto(data[0]);
				de=getAeropuerto(data[1]);
				rutaActual=new Ruta(or,de,Double.parseDouble(data[2]),Integer.parseInt(data[3]));
				
				rutas[or.id()][de.id()]=rutaActual;

				String[]aerolineas=CSVTools.cut(data[4],",");
				
				for(int i=0;i<aerolineas.length;i++) {
					String[]aeroln=CSVTools.cut(aerolineas[i], "-");
					
					String name=aeroln[0];
					name=name.replace("{", "");
					aeroln[1]=aeroln[1].replace("}", "");
					int pasajes=Integer.parseInt(aeroln[1]);
					
					rutaActual.addAerolinea(name,pasajes);
				}
			}catch(Exception e) {
				System.out.println("initialize() Rutas --- "+e);
			}
		}
	}
	private void initReservas(List l) {

		Iterador it=l.iterator();
		int size=l.getSize();
		
		rsvs=new Reserva[size];
		
		String[]data;
		Aeropuerto or,de;
		MergeSort ms=new MergeSort();
		
		try {
			for(int i=0;i<size;i++){
				data=(String[])it.next();
				or=getAeropuerto(data[0]);
				de=getAeropuerto(data[1]);
				Reserva rsvActual=new Reserva(or,de,data[2],Integer.parseInt(data[3]));
				rsvs[i]=rsvActual;
			}
		}catch(Exception e) {
			System.out.println("initialize() Reservas - "+e);
		}
		ms.sort(rsvs);
	}

	private Ruta getRuta(int origen,int destino) {
		return rutas[origen][destino];
	}
	private Ruta getRuta(Aeropuerto origen,Aeropuerto destino) {
		Ruta r=null;
		if(origen!=null&&destino!=null) {
			r=rutas[origen.id()][destino.id()];
		}
		return r;
	}
	private Aeropuerto getAeropuerto(String name) {
		BinarySearch busc=new BinarySearch();
		return (Aeropuerto)busc.search(arrName, new Aeropuerto(name,"",""));
	}
	private int getAsientos(Ruta r,String aeroln) {
 		int asientos=r.getAsientos(aeroln);
 		BinarySearch busc=new BinarySearch();
 		Reserva aux=(Reserva)busc.search(rsvs,new Reserva(r.getOrigen(),r.getDestino(),aeroln,0));
 		if(aux!=null)return aux.pasajesLibres(asientos);
 		else return asientos;
	}
	private void back(int o,int[]visit,String x,List sc,List recActual) {
		Ruta rutaActual;
		for(int i=0;i<size;i++) {
			rutaActual=getRuta(o, i);
			if(rutaActual!=null&&visit[i]>=0) {
				if(puedeEvitarAerolinea(rutaActual,x)) {
					recActual.add(rutaActual);
					if(visit[i]>0) {
						sc.add(recActual.reverseCopy());
					}else {
						visit[i]=-1;
						back(i,visit,x,sc,recActual);
						visit[i]=0;
					}
					recActual.removeFront();
				}
			}
		}
	}
	private boolean puedeEvitarAerolinea(Ruta r,String x) {
		String[]keys=r.keyAerolineas();
		boolean posible=false;
		int i=0;
		while(!posible&&i<keys.length) {
			posible=!keys[i].equals(x);
			if(posible) {
				posible=(getAsientos(r,keys[i]))>0;
			}
			i++;
		}
		return posible;
	}
	
	public String getAeropuertos() {
		String resp="";
		for(int i=0;i<size;i++) {
			resp=resp+arrName[i].toString();
		}
		return resp;
	}
	public String getReservas() {
		String resp="";
		for(int i=0;i<size;i++) {
			resp=resp+rsvs[i].toString()+".\n";
		}
		return resp;
	}
	
	public String service1(String origen,String destino,String aerolinea) {
		Aeropuerto o,d;
		String respuesta="No se realizan vuelos sin escala desde "+origen+" a "+destino;
		o=getAeropuerto(origen);
		d=getAeropuerto(destino);
		if(o!=null&&d!=null) {
			Ruta r=getRuta(o, d);
			if(r!=null) {
				int pasajes=getAsientos(r,aerolinea);
				respuesta="desde "+origen+" a "+destino+" hay "+r.distancia() + " km. y quedan " + pasajes + " pasajes disponibles";
			}
		}
		return respuesta+"\n";
	}
	public String service2(String origen,String destino,String aerolineaEvitada) {
		String respuesta="";
		List sc=new List(),recActual=new List();
		int or=getAeropuerto(origen).id(),de=getAeropuerto(destino).id();
		int[]visit=new int[size];
		for(int i=0;i<size;i++) {
			visit[i]=0;
		}
		visit[de]=1;
		back(or,visit,aerolineaEvitada,sc,recActual);
		Iterador itSc=sc.iterator();
		int numViajes=1;
		while(itSc.hasNext()) {
			recActual=(List)itSc.next();
			respuesta=respuesta+"Opcion "+numViajes+" - Escalas:"+(recActual.getSize()-1);
			numViajes++;
			Iterador itRec=recActual.iterator();
			while(itRec.hasNext()) {
				Ruta rAux=(Ruta)itRec.next();
				String[]keys=rAux.keyAerolineas();
				String aerolineas=" Posibles Aerolineas: ";
				for(int i=0;i<keys.length;i++) {
					int asientos=getAsientos(rAux,keys[i]);
					if(asientos>0&&!keys[i].equals(aerolineaEvitada)) {
						aerolineas=aerolineas+" - "+keys[i];
					}
				}
				respuesta=respuesta+"\n( "+rAux.getOrigen().getName()+" - "+rAux.getDestino().getName()+" ) "+rAux.distancia()+aerolineas;
			}
			respuesta=respuesta+"\n";
		}
		return respuesta;
	}
	public String service3(String paisOrigen,String paisDestino) {
		String respuesta="";
		BSPais busc=new BSPais();
		Aeropuerto[] or=busc.search(arrPais, paisOrigen);
		Aeropuerto[] de=busc.search(arrPais, paisDestino);
		Ruta rutaActual;
		boolean anotada;
		if(or!=null&&de!=null) {
			for(int i=0;i<or.length;i++) {
				for(int j=0;j<de.length;j++) {
					rutaActual=getRuta(or[i], de[j]);
					if(rutaActual!=null) {
						anotada=false;
						String[] keys=rutaActual.keyAerolineas();
						int asientos;
						for(int k=0;k<keys.length;k++) {
							asientos=this.getAsientos(rutaActual, keys[k]);
							if(asientos>0) {
								if(!anotada) {
									respuesta=respuesta+"\n("+or[i].getName()+" - "+de[j].getName()+") - "+rutaActual.distancia()+"km. - ";
									anotada=true;
								}
								respuesta=respuesta+keys[k]+": "+asientos+" asientos libres.";
							}
						}
					}
				}
			}
		}
		if(respuesta.equals(""))respuesta="No se realizan vuelos directos desde "+paisOrigen+" a "+paisDestino;
		return respuesta;
	}

}
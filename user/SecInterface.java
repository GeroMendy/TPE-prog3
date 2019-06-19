package user;

import base.Sistema;
import list.List;
import list.Iterador;
import base.Ruta;
import base.Aeropuerto;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SecInterface implements User{
	
	private BufferedReader br;
	
	public SecInterface() {
		br=new BufferedReader(new InputStreamReader(System.in));
	}

	public String run(Sistema sist) {
		String respuesta="";
		int opcion=0;
		try {
			System.out.println("Seleccione el servicio a utilizar:\n	-1:Recorrer mediante un algoritmo Greedy.\n	-2:Recorrer mediante Backtracking.");
			opcion=Integer.parseInt(br.readLine());
			switch(opcion) {
			case 1:respuesta=agenteViajeroGreedy(sist);break;
			case 2:respuesta=agenteViajeroBack(sist);break;
			}
		}catch(Exception e) {
			System.out.println(e);
			opcion=0;
		}
		
		return respuesta;
	}
	
	private String agenteViajeroGreedy(Sistema sist) {
		String respuesta = "";
		List l=sist.AgenteViajeroGreedy();
		Iterador it = l.iterator();
		Ruta r;
		Aeropuerto o,d;
		if(l.getSize()<sist.getSize())respuesta="\n\nERROR : No es posible realizar un recorrido completo mediante este algoritmo.\n\n";
		while(it.hasNext()) {
			r=(Ruta)it.next();
			o=r.getOrigen();
			d=r.getDestino();
			respuesta=respuesta+"\n"+o.getName()+" ("+o.getCiudad()+" - "+o.getPais()+") ---> "+d.getName()+" ("+d.getCiudad()+" - "+d.getPais()+") - "+r.distancia();
		}
		return respuesta;
	}
	private String agenteViajeroBack(Sistema sist) {
		String respuesta="";
		double dist=0;
		System.out.println(sist);
		List l = sist.AgenteViajeroBack();

		if(l==null)return "\n\nERROR : No es posible realizar el recorrido sin repetir aeropuertos.\n\n";
		Iterador it=l.iterator();
		Ruta r;
		Aeropuerto o,d;		
		while(it.hasNext()) {
			r=(Ruta)it.next();
			o=r.getOrigen();
			d=r.getDestino();
			respuesta=respuesta+"\n"+o.getName()+" ("+o.getCiudad()+" - "+o.getPais()+") ---> "+d.getName()+" ("+d.getCiudad()+" - "+d.getPais()+") - "+r.distancia();
			dist+=r.distancia();
		}
		respuesta=respuesta+"\nRecorrido Total : "+dist+"km.";
		return respuesta;
	}
}

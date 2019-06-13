package user;

import base.Sistema;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClientInterface implements User{

	private BufferedReader br;
	
	public ClientInterface() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	public String run(Sistema sist) {

		int opcion=0;
		String respuesta="";
		try {
			System.out.println("Seleccione el servicio a utilizar: \n	-1:Listar aeropuertos.\n	-2:Listar reservas\n	-3:service1\n	-4:service2.\n	-5:service3.");
			opcion=Integer.parseInt(br.readLine());

			switch(opcion) {
			case 1:respuesta=getAerop(sist);break;
			case 2:respuesta=getReservas (sist);break;
			case 3:respuesta=service1(sist);break;
			case 4:respuesta=service2(sist);break;
			case 5:respuesta=service3(sist);break;
			}

		}catch(Exception e) {
			opcion=0;
			System.out.println(e);
		}
		return respuesta;
	}
	private String service1(Sistema sist) {
		String or="",de="",aeroln="";
		try {

			System.out.println("Ingrese el aeropuerto de origen");
			or=br.readLine();
			System.out.println("Ingrese el aeropuerto de destino");
			de=br.readLine();
			System.out.println("Ingrese la aerolinea deseada");
			aeroln=br.readLine();

		}catch(Exception e) {
			System.out.println(e);
		}
		return sist.service1(or, de, aeroln);

	}

	private String service2(Sistema sist) {
		String or="",de="",aeroln="";
		try {

			System.out.println("Ingrese el aeropuerto de origen");
			or=br.readLine();
			System.out.println("Ingrese el aeropuerto de destino");
			de=br.readLine();
			System.out.println("Ingrese la aerolinea a evitar");
			aeroln=br.readLine();

		}catch(Exception e) {
			System.out.println(e);
		}
		return sist.service2(or, de, aeroln);
	}

	private String service3(Sistema sist) {
		String or="",de="";
		try {

			System.out.println("Ingrese el pais de origen");
			or=br.readLine();
			System.out.println("Ingrese el pais de destino");
			de=br.readLine();

		}catch(Exception e) {
			System.out.println(e);
		}
		return sist.service3(or, de);
	}

	private String getAerop(Sistema sist) {
		return sist.getAeropuertos();
	}

	private String getReservas(Sistema sist) {
		return sist.getReservas();
	}
}

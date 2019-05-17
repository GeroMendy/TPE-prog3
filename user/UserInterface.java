package user;
import base.Sistema;
import csvTools.CSVTools;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UserInterface {

	private Sistema sist;
	private BufferedReader br;
	private String writePath ="";
	
	public void execute(String pathAeropuertos, String pathRutas, String pathReservas, String pathRespuesta) {

		String aerop =pathAeropuertos;
		String rutas =pathRutas;
		String reservas =pathReservas;
		writePath= pathRespuesta;

		sist=new Sistema(CSVTools.read(aerop),CSVTools.read(rutas),CSVTools.read(reservas));
		br=new BufferedReader(new InputStreamReader(System.in));

		int opcion=0;
		String respuesta;
		boolean repetir=true;
		while(repetir) {
			respuesta="";
			try {
				System.out.println("Seleccione el servicio a utilizar: \n	-1:Listar aeropuertos.\n	-2:Listar reservas\n	-3:service1\n	-4:service2.\n	-5:service3");
				opcion=Integer.parseInt(br.readLine());

				switch(opcion) {
				case 1:respuesta=getAerop();break;
				case 2:respuesta=getReservas ();break;
				case 3:respuesta=service1();break;
				case 4:respuesta=service2();break;
				case 5:respuesta=service3();break;
//				case condicionSalida:repetir=false;break;				
				}
				System.out.println("Desea guardar el resultado?'y' o 'n'");
				if(br.readLine().charAt(0)=='y')write(respuesta);
				
			}catch(Exception e) {
				opcion=0;
				System.out.println(e);
			}
			System.out.println(respuesta);
		}

	}
	private String service1() {
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

	private String service2() {
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

	private String service3() {
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

	private String getAerop() {
		return sist.getAeropuertos();
	}

	private String getReservas() {
		return sist.getReservas();
	}
	private void write(String x) {
		CSVTools.write(writePath, x);
	}
}

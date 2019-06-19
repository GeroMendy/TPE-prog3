package test;

import user.UserInterface;

public class Main {

	public static void main(String[]args) {
		
		String pathAeropuertos ="tpe\\Datasets\\Aeropuertos.csv";
		String pathRutas = "tpe\\Datasets\\Rutas.csv";
		String pathReservas ="tpe\\Datasets\\Reservas.csv";
		String pathRespuesta ="tpe\\Datasets\\Respuesta.csv";
		
		UserInterface user = new UserInterface(pathAeropuertos,pathRutas,pathReservas,pathRespuesta);
		
//		user.ingresarComoCliente();				Para los metodos de la parte 1.
		user.ingresarComoOrganismoSeguridad();	
	}
}

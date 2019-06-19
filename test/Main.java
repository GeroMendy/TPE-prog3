package test;

import user.UserInterface;

public class Main {

	public static void main(String[]args) {
		
		String pathAeropuertos ="";
		String pathRutas = "";
		String pathReservas ="";
		String pathRespuesta =""; //path a donde se quiere guardar la respuesta.
		
		UserInterface user = new UserInterface(pathAeropuertos,pathRutas,pathReservas,pathRespuesta);
		
//		user.ingresarComoCliente();				Para los metodos de la parte 1.
		user.ingresarComoOrganismoSeguridad();	
	}
}

package user;

import base.Sistema;
import csvTools.CSVTools;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UserInterface {

	private Sistema s;
	private String pathResp;
	private BufferedReader br;

	public UserInterface(String pathAeropuertos, String pathRutas, String pathReservas, String pathRespuesta) {
		s=new Sistema(CSVTools.read(pathAeropuertos),CSVTools.read(pathRutas),CSVTools.read(pathReservas));
		pathResp=pathRespuesta;
		br=new BufferedReader(new InputStreamReader(System.in));
	}
	public void ingresarComoCliente() {
		run(new ClientInterface());
	}
	public void ingresarComoOrganismoSeguridad() {
		run(new SecInterface());
	}
	private void run(User u) {
		String info;
		char c='0';
		boolean repetir=true;
		while(repetir) {
			repetir=false;
			info=u.run(s);
			System.out.println(info);
			try {
				boolean charValido=false;
				while(!charValido) {
					System.out.println("Desea guardar la respuesta? Y / N");
					c=br.readLine().charAt(0);
					charValido=(c=='n'||c=='N');
					if(c=='y'||c=='Y') {
						charValido=true;
						write(pathResp,info);
					}
				}
				charValido=false;
				while(!charValido) {
					System.out.println("Desea realizar otra operacion? Y / N");
					c=br.readLine().charAt(0);
					charValido=(c=='n'||c=='N'||c=='y'||c=='Y');
					repetir=(c=='y'||c=='Y');
				}
			}catch(Exception e) {
				System.out.println(e);
				repetir=true;
			}
		}
	}
	private void write(String path,String x) {
		CSVTools.write(path, x);
	}
}

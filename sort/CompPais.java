package sort;
import base.Aeropuerto;

public class CompPais implements Comparator{

	public int compare(Comparable a1,Comparable a2) {
		try {
			Aeropuerto aerop1=(Aeropuerto)a1;
			Aeropuerto aerop2=(Aeropuerto)a2;
			return (aerop2.getPais().compareTo(aerop1.getPais()));	
		}catch(Exception e) {
			return 0;
		}
	}
}

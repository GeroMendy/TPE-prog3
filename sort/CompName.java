package sort;
import base.Aeropuerto;

public class CompName implements Comparator{

	public int compare(Comparable a1,Comparable a2) {
		try {
			Aeropuerto aerop1=(Aeropuerto)a1;
			Aeropuerto aerop2=(Aeropuerto)a2;
			return (aerop1.getName().compareTo(aerop2.getName()));
		}catch(Exception e) {
			return 0;
		}
	}
}

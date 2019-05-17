package csvTools;
import list.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVTools {
	
	private static int time=0;

    public static List read(String path) {
        String csvFile = path;
        String line = "";
        String csvSplitBy = ";";
        List l=new List();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] items = cut(line,csvSplitBy);
                l.add(items);
                
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return l;
    }
    public static String[] cut(String line,String limite) {
    	if(line!=null&&limite!=null)return line.split(limite);
    	else return null;
    }
    public static void userLogIn(String name) {
  //  	user=name;
    }
    public static void userLogOut() {
  //  	user=LOGED_OUT;
    }
    
	public static void write(String path,String info) {
		BufferedWriter bw = null;
		try {
			File file = new File(path+time);
			if (!file.exists()) {
				file.createNewFile();
			}
			time++;

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			
//			time++;
			
			String contenido="";

//			String contenido =(user+" - Ejecucion "+time+"\n"+info+"\n");
			
			contenido=contenido+"\n"+info+"\n";
			bw.newLine();
			bw.write(contenido);

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error cerrando el BufferedWriter" + ex);
			}
		}
	}
    
}

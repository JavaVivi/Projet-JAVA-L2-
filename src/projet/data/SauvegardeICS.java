package projet.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author David Szathmary
 * @version 1.0
 *
 */
public class SauvegardeICS implements Sauvegarde {
	
	static FichierICS v = new FichierICS("", "", "", "", "","","","","");
	/**
	 * Cette méthode prends en paramètre le fichier entré par l'utilisateur. 
	 * Si le fichier n'est pas de type .ics, le programme s'arrête et lève une
	 * exception. Sinon, on ouvre les flux de lecture et on lit le fichier ligne
	 * par ligne. A chaque ligne, on regarde par quoi elle commence et selon cette
	 * information, on va découper la ligne après le prefixe...pour récupérer la
	 * chaine de caractères qui suit. Cette chaine de caractères va ensuite être
	 * assigné à l'attribut de notre objet de type FichierICS. Ces instructions 
	 * permettent donc d'afficher le contenu du fichier en sauvegardant ses 
	 * informations dans un objet structuré, qui va être utilisé dans la 
	 * sérialisation. 
	 * @param f
	 * @throws WrongExtensionException
	 */
	public static void lecture_et_sauvegarde_fichier(File f) throws WrongExtensionException {
		if(f.getName().endsWith(".ics")) {
			FileReader fr;
			try {
				fr = new FileReader(f);
				BufferedReader br = new BufferedReader (fr);
				try {
					String line = br.readLine();
					 while (line != null)
				        {
				    		if(line.startsWith("VERSION:")) {
				    			String version = line.substring(8);
				    			v.setVersion(version);
				    		}
				    		if(line.startsWith("PRODID:")) {
				    			String prodid = line.substring(7);
				    			v.setProdid(prodid);
				    		}
				    		if(line.startsWith("UID:")) {
				    			String UID = line.substring(4);
				    			v.setUID(UID);
				    		}
				    		if(line.startsWith("DTSTAMP:")) {
				    			String dtStamp = line.substring(8);
				    			v.setDtStamp(dtStamp);
				    		}
				    		if(line.startsWith("DTSTART:")){
				    			String dtStart = line.substring(8);
				    			v.setDtStart(dtStart);
				    		}
				    		if(line.startsWith("DTEND:")) {
				    			String dtEnd = line.substring(6);
				    			v.setDtEnd(dtEnd);
				    		}
				    		if(line.startsWith("SUMMARY:")) {
				    			String summ = line.substring(8);
				    			v.setSummary(summ);
				    		}
				    		if(line.startsWith("LOCATION:")) {
				    			String loc = line.substring(9);
				    			v.setLocation(loc);
				    		}
				    		if(line.startsWith("DESCRIPTION:")) {
				    			String desc = line.substring(12);
				    			v.setDesc(desc);
				    		}
				    		
				    		line = br.readLine();
				        }
					 br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new WrongExtensionException("Mauvais type de fichier.");
		}
	}
	
	/** Methode permettant de serialiser l'objet de type FichierICS dans un fichier binaire. Le nom du fichier à produire est 
	 * passé en paramètre par l'utilisateur.
	 * @param f
	 * @see #serialisation_lecture(File)
	 */
	public static void serialisation(File f) {
		try {
			if(f.getName().endsWith(".ser")) {
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(v);
			fos.close();
			oos.close();
		    } 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**Méthode de relecture du fichier binaire de sauvegarde.
	 * @param f
	 * @see #serialisation(File)
	 */
	public static void serialisation_lecture(File f) {
		try {
			if(f.getName().endsWith(".ser")) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			try {
				System.out.println(ois.readObject());
				ois.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			} 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** Méthode de génération du fichier .html à partir de l'objet crée à partir du fichier d'entrée.
	 * @param f
	 */
	public static void fragment_HTML(File f) {
		if(f.getName().endsWith(".html")) {
			try {
				f.createNewFile();
				FileWriter fw = new FileWriter(f);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("<div class=\"icalendar\">");
				bw.newLine();
				bw.write("<a class=\"prodid\" href=\"" + v.getProdid() + ">e</a>");
				bw.newLine();
				bw.write("<a class=\"uid\" href=\"" + v.getUID() + ">e</a>");
				bw.newLine();
				bw.write("<span class=\"dtstamp\">" + v.getDtStamp() + "</span>");
				bw.newLine();
				bw.write("<span class=\"dtstart\">" + v.getDtStart() + "</span>");
				bw.newLine();
				bw.write("<span class=\"dtend\">" + v.getDtEnd() + "</span>");
				bw.newLine();
				bw.write("<span class=\"summary\">" + v.getSummary() + "</span>");
				bw.newLine();
				bw.write("<span class=\"location\">" + v.getLocation() + "</span>");
				bw.newLine();
				bw.write("<span class=\"desc\">" + v.getDesc() + "</span>");
				bw.newLine();
				bw.write("</div>");
				bw.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/** Affiche l'objet v ( crée à partir d'un fichier).
	 * @return 
	 */
	public static String afficherFile () {
        return v.toString();
    }

}

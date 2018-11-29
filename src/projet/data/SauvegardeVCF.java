/**
 * 
 */
package projet.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author vcaze
 *
 */
public class SauvegardeVCF implements Sauvegarde {
	
	static FichierVCF v = new FichierVCF("", "", "", "", "");
	/**
	 * Cette méthode prends en paramètre le fichier entré par l'utilisateur. 
	 * Si le fichier n'est pas de type .vcf, le programme s'arrête et lève une
	 * exception. Sinon, on ouvre les flux de lecture et on lit le fichier ligne
	 * par ligne. A chaque ligne, on regarde par quoi elle commence et selon cette
	 * information, on va découper la ligne après le prefixe...pour récupérer la
	 * chaine de caractères qui suit. Cette chaine de caractères va ensuite être
	 * assigné à l'attribut de notre objet de type FichierVCF. Ces instructions 
	 * permettent donc d'afficher le contenu du fichier en sauvegardant ses 
	 * informations dans un objet structuré, qui va être utilisé dans la 
	 * sérialisation. 
	 * @param f
	 * @throws WrongExtensionException
	 */
	public static void lecture_et_sauvegarde_fichier(File f) throws WrongExtensionException {
		if(f.getName().endsWith(".vcf")) {
			FileReader fr;
			try {
				fr = new FileReader(f);
				BufferedReader br = new BufferedReader (fr);
				try {
					String line = br.readLine();
					 while (line != null)
				        {
						 	System.out.println (line);
				    		
				    		if(line.startsWith("VERSION:")) {
				    			String version = line.substring(8);
				    			v.setVersion(version);
				    		}
				    		if(line.startsWith("N:")) {
				    			String name = line.substring(2);
				    			v.setName(name);
				    		}
				    		if(line.startsWith("EMAIL;INTERNET:")) {
				    			String mail = line.substring(15);
				    			v.setEmail(mail);
				    		}
				    		if(line.startsWith("ORG:")) {
				    			String org = line.substring(4);
				    			v.setOrg(org);
				    		}
				    		if(line.startsWith("TEL;CELL:")){
				    			String tel = line.substring(9);
				    			v.setTel(tel);
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
	
	/** Methode permettant de serialiser l'objet de type FichierVCF dans un fichier binaire. Le nom du fichier à produire est 
	 * passé en paramètre par l'utilisateur.
	 * @param f
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
	
	/**Génère le fichier html à partir de l'objet de type FichierVCF.
	 * @param f
	 */
	public static void fragment_HTML(File f) {
		if(f.getName().endsWith(".html")) {
			try {
				f.createNewFile();
				FileWriter fw = new FileWriter(f);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("<div class=\"vcard\">");
				bw.newLine();
				bw.write("<span class=\"fn\">" + v.getName() + "</span>");
				bw.newLine();
				bw.write("<a class=\"email\" href=\"" + v.getEmail() + ">e</a>");
				bw.newLine();
				bw.write("<span class=\"org\">" + v.getOrg() + "</span>");
				bw.newLine();
				bw.write("<span class=\"tel\">" + v.getTel() + "</span>");
				bw.newLine();
				bw.write("</div>");
				bw.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//tests à jetés
	public static void main(String[] args) {
		File f = new File("/home/etu/vcaze/Bureau/Projet-JAVA-L2--master/Files/vCard David.vcf");
		File f2 = new File("/home/etu/vcaze/Bureau/Projet-JAVA-L2--master/Files/vCard David.ser");
		try {
			lecture_et_sauvegarde_fichier(f);
			serialisation(f2);
			serialisation_lecture(f2);
			
		} catch (WrongExtensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

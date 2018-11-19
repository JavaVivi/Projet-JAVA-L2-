/**
 * 
 */
package projet.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
					 System.out.println(v);
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
	
	public static void main(String[] args) {
		File f = new File("/home/etu/vcaze/Bureau/Projet-JAVA-L2--master/Files/vCard David.vcf");
		try {
			lecture_et_sauvegarde_fichier(f);
		} catch (WrongExtensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
	

package projet.data;
import java.io.File;
import java.io.FilenameFilter;


/**
 * @author vcaze
 * @version 1.0
 */

/**
 * Filtre implémente l'interface FilenameFilter qui permet de filtrer les fichiers par leurs noms. Ici on utilise la fin du
 * nom de fichier, aka l'extension. 
*/
public class Filtre implements FilenameFilter{
	
	/* (non-Javadoc)
	 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
	 */
	public boolean accept(File file, String fileName) {
	   return fileName.endsWith(".vcf") || fileName.endsWith(".ics");
	}
}

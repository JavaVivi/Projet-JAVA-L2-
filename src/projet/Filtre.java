/**
 * 
 */
package projet.data;

/**
 * @author vcaze
 *
 */

import java.io.File;
import java.io.FilenameFilter;

//le filtre utilise l'interface FilenameFilter qui permet de filtrer des fichiers ( par 
//leur extension ici )
public class Filtre implements FilenameFilter{
	
	/* (non-Javadoc)
	 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
	 */
	public boolean accept(File file, String fileName) {
	return fileName.endsWith(".vcs") || fileName.endsWith(".ics");
	}
}


/**
 * 
 */
package projet.data;

import java.io.File;

/**
 * @author vcaze
 *
 */
public interface Sauvegarde {
	public void lecture_et_sauvegarde_fichier(File f);
	public void serialisation();
}

package projet.data;

import java.io.File;


/**
 * @author vcaze
 * @version 1.0
 *
 */
public class ListFiles {
	
	/** Liste les fichiers d'un dossier en récupérant uniquement les .ics et .vcf à l'aide de la classe Filtre. Permet un appel
	 * récursif sur les sous-dossiers.
	 * @param dir
	 */
	public static void liste_files(File dir) {
		//on commence par créer un nouvel objet de type Filtre, qui va nous permettre 
		//de récupérer les bonnes extensions. A noter que ce filtre n'est pas indispensable,
		//il permet surtout d'organiser le code.
		Filtre f = new Filtre();
		//si le paramètre dir est un repertoire, on liste ses fichiers dans un tableau
		//de fichiers
		if(dir.isDirectory()) {
			File[] list = dir.listFiles();
			//si le tableau est non vide, on le parcourt...
			if(list != null) {
				for(int i = 0; i < list.length; i++) {
					//et si la ieme valeur du tableau est un fichier...
					if(list[i].isFile()) {
						//on teste si le fichier correspond au filtre ( donc aux extensions
						//voulues ) et on affiche le nom du fichier si c'est le cas. Sinon,
						//on fais un appel récursif sur la case suivante
						if(f.accept(list[i], list[i].getName())){
							System.out.println(list[i].getName());
						}
					} else {
						liste_files(list[i]);
					}
				}
			}
		}
	}

}

/**
 * 
 */
package projet.data;

import java.io.File;


/**
 * @author vcaze
 *
 */
public class ListFiles {
	
	/**
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
	//ce main est indispensable. Notre méthode est statique, et nécessite un main dans la 
	//classe pour pouvoir être utilisée. Lorsque l'on va assigner un paramètre ( ex : -d )
	//à cette classe, elle va appeler son main et l'exécuter
	public static void main(String[] args) {
		liste_files(new File("dir"));
	}
  }

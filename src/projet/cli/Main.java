package projet.cli;
import java.io.File;

import projet.data.*; 

/**
 * @author David Szathmary
 * @version 1.0
 *
 */
public class Main {

	/** Le main correspond au mode console, où les paramètres sont gerés.
	 * @param args
	 */
	
	public static void main (String[] args) {
		
		/**
		 * Si l'utilisateur ne rentre pas d'arguments, le programme affiche la liste des options possibles.
		 */
		if (args.length == 0) {			
			System.out.println("-d nomFichier : liste les fichier d'un dossier\n");
			System.out.println("-i nomFichier : affiche le fichier \n");
			System.out.println("-i nomFichierIn -o nomFichierOut : sauvegarde le fichier dans un .ser");
			System.out.println("-i nomFichierIn -h nomFichierOut : genere un fragment html");
			System.out.println("-gui : lance en mode interface graphique");
		}
		
		if (args.length >= 1) {
			/**
			 * L'argument -d liste les fichiers du dossier spécifié, en appelant la méthode static liste_files de la classe
			 * ListFiles. 
			 */
			if (args[0].equals("-d")) {			
				ListFiles.liste_files((new File (args[1])));
			}
			/**
			 * L'argument -i affiche le fichier spécifié, en appelant la méthode de lecture de la classe correspondante au
			 * type de fichier. Le fichier spécifié sera toujours le fichier passé en entrée.
			 */
			if (args[0].equals("-i")) {			//definit le fichier en entr�e (si seul, affiche simplement le fichier)
				String iFileName = args[1];
				try {
					if (iFileName.endsWith(".vcf")) {
						SauvegardeVCF.lecture_et_sauvegarde_fichier(new File(iFileName));
					}
					if (iFileName.endsWith(".ics")) {
						SauvegardeICS.lecture_et_sauvegarde_fichier(new File(iFileName));
					}
				} catch (WrongExtensionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				/**
				 * Affiche le fichier.
				 */
				if (args.length == 2) {
					if (iFileName.endsWith(".vcf")) {		
						System.out.println(SauvegardeVCF.afficherFile());
					}
					if (iFileName.endsWith(".ics")) {
						System.out.println(SauvegardeICS.afficherFile());
					}
				}
				/**
				 * L'argument -o permet de spécifier le fichier de sortie pour sérialiser notre fichier d'entrée dans un .ser
				 * dont le chemin est spécifié par l'utilisateur.
				 */
			
				if (args.length >=3) {
					if(args[2].equals("-o")) {		
						String oFileNameSER = args[3];
						if (iFileName.endsWith(".vcf")) {
							SauvegardeVCF.serialisation(new File(oFileNameSER));
						}
						if (iFileName.endsWith(".ics")) {
							SauvegardeICS.serialisation(new File(oFileNameSER));
						}
						}
					/**
					 * L'argument -h permet de générer un fichier .html à partir du fichier passé en entrée. 
					 */
					if(args[2].equals("-h")) {		
						String oFileNameHTML = args[3];
						if (iFileName.endsWith(".vcf")) {
							SauvegardeVCF.fragment_HTML(new File (oFileNameHTML));
						}
						if (iFileName.endsWith(".ics")) {
							SauvegardeICS.fragment_HTML(new File (oFileNameHTML));
						}
					}	
				}
			}
			if (args[0].equals("-gui")) {
				new GUIProjet();
			}
		}
	}
}

import java.io.File;

import data.SauvegardeICS;
import data.SauvegardeVCF;
import data.WrongExtensionException;

public class Main {

	public static void main (String[] args) {
		
		if (args.length == 0) {			//affichage des options
			System.out.println("-d nomFichier : liste les fichier d'un dossier\n");
			System.out.println("-i nomFichier : affiche le fichier \n");
			System.out.println("-i nomFichierIn -o nomFichierOut : sauvegarde le fichier dans un .ser");
			System.out.println("-i nomFichierIn -h nomFichierOut : genere un fragment html");
		}
		
		if (args.length >= 1) {
			if (args[0].equals("-d")) {			//liste les fichiers du dossier sp�cifi�
				ListFiles.liste_files((new File (args[1])));
			}
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
				if (args.length == 2) {
					if (iFileName.endsWith(".vcf")) {		//affichage du fichier
						System.out.println(SauvegardeVCF.afficherFile());
					}
					if (iFileName.endsWith(".ics")) {
						System.out.println(SauvegardeICS.afficherFile());
					}
				}
			
				if (args.length >=3) {
					if(args[2].equals("-o")) {		//sauvegarde le fichier en entr�e dans un .ser
						String oFileNameSER = args[3];
						if (iFileName.endsWith(".vcf")) {
							SauvegardeVCF.serialisation(new File(oFileNameSER));
						}
						if (iFileName.endsWith(".ics")) {
							SauvegardeICS.serialisation(new File(oFileNameSER));
						}
						}
					if(args[2].equals("-h")) {		//genere un .html a partir du fichier en entr�e
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
		}
	}
}

/**
 * 
 */
package projet.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author vcaze
 *
 */
public class FileUse {
	private File f;

	/** CONSTRUCTEUR
	 * @param f
	 */
	public FileUse(File f) {
		f = new File("f");
	}

	/** GETTERS ET SETTERS
	 * @return the f
	 */
	public File getF() {
		return f;
	}

	/**
	 * @param f the f to set
	 */
	public void setF(File f) {
		this.f = f;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "File [f=" + f + "]";
	}
	
	/** Methode de lecture et d'affichage du fichier, on ne met pas de méthode de modification
	 * en mode console 
	 * @param f
	 */
	public void file_read(File f) {
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			try {
				while ((line = br.readLine()) != null) {
					   System.out.println(line);
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				br.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not found.");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//serialisation du fichier
	public void file_ser(File f) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("sauvegarde.ser"));
			oos.writeObject(f);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

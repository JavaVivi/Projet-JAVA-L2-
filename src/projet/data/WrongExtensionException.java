package projet.data;

/**
 * @author vcaze	
 * @version 1.0
 */
public class WrongExtensionException extends Exception {

	/**
	 * Classe d'exception appelée lorsque le type de fichier passé en paramètre
	 * n'est pas correct
	 */
	private static final long serialVersionUID = 3012321050286637944L;

	/**
	 * @param message
	 */
	public WrongExtensionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	
	
}

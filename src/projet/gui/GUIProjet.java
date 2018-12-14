
package projet.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


/**
 * @author vcaze
 * @version 1.0
 *
 */
public class GUIProjet extends JFrame {
	

	private static final long serialVersionUID = -3693072772118476042L;
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItemO, menuItemD, menuItemS, menuItemH, menuItemQ;
	private JTextArea fichiers, contenu;
	//private JButton ouvrir, sauvegarder, generer, aide;
	private JFileChooser jfc;
	
	/**
	 *  Constructeur permettant de créer l'interface graphique.
	 */
	public GUIProjet() { 
		
		JLabel jf = new JLabel("Fichiers : ");
		fichiers = new JTextArea(15,15);
		fichiers.setEditable(false);
		
		menuBar = new JMenuBar();
		
		JLabel jc = new JLabel("Contenu : ");
		contenu = new JTextArea(20,25);
		/**
		 * La zone contenu n'est pas éditable, il s'agit juste d'une zone informative.
		 */
		contenu.setEditable(true);
		
		menu = new JMenu("Fichier");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);
		
		/**
		 * 
		 */
		menuItemO = new JMenuItem("Ouvrir");
		menuItemO.addActionListener(new ActionOuvrir());
		menu.add(menuItemO);
		menu.addSeparator();
		
		/**
		 * "Récupérer chemins" permet d'ouvrir un dossier pour afficher la liste des fichiers .vcf et .ics s'y trouvant.
		 */
		menuItemD = new JMenuItem("Récuperer chemins");
		menuItemD.addActionListener(new ActionChemins());
		menu.add(menuItemD);
		menu.addSeparator();
		
		menuItemS = new JMenuItem("Sauvegarder");
		menuItemS.addActionListener(new ActionEnregistrer());
		menu.add(menuItemS);
		menu.addSeparator();
		
		menuItemH = new JMenuItem("Generer HTML");
		menuItemH.addActionListener(new ActionGenerer());
		menu.add(menuItemH);
		menu.addSeparator();
		
		menuItemQ = new JMenuItem("Quitter");
		menuItemQ.addActionListener(new ActionQuitter());
		menu.add(menuItemQ);
		
		
		this.setTitle("Carte de visite & calendrier");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(menuBar);
		this.getContentPane().setLayout(new FlowLayout());
		this.getContentPane().add(jf);
		this.getContentPane().add(fichiers);
		this.getContentPane().add(jc);
		this.getContentPane().add(contenu);
		
		this.pack();
		this.setVisible(true);
	
	}
	
	/** On ouvre un fichier. Si le fichier est de la bonne extension, on ouvre les flux de lecture et on affiche chaque 
	 * ligne du fichier dans la zone éditable "contenu", grâce au BufferedReader.
	 * A noter que la zone se réinitialise à chaque ouverture de fichier pour éviter l'affichage de plusieurs fichiers à
	 * la suite.
	 * @author vcaze
	 * @version 1.0
	 */
	class ActionOuvrir implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			jfc = new JFileChooser();
			int returnVal = jfc.showOpenDialog(menuItemO);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				contenu.setText("");
				int r = JOptionPane.showConfirmDialog(null, "Voulez vous ouvrir ce fichier ?", "Info", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(r == JOptionPane.YES_OPTION) {
					File file = jfc.getSelectedFile();
					if(file.getName().endsWith(".vcf") || file.getName().endsWith(".ics")) {
						BufferedReader br;
						try {
							br = new BufferedReader(new FileReader(file));
							String line = br.readLine();
							while(line != null) {
								contenu.setText(contenu.getText() + line + "\n");
								line = br.readLine();
							}
							
							br.close();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if(file.getName().endsWith(".ser")) {
						try {
							ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
							contenu.setText(String.valueOf(ois.readObject()));
							ois.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				
			}
			
		}	
	}
	
	
	/** On ouvre un JFileChooser en mode "dossier seulement" et on liste son contenu dans un tableau. Si le tableau n'est pas
	 * vide, on affiche dans la zone de texte fichiers le chemin du dossier choisi puis on parcourt le tableau. Si
	 * la case i du tableau correspond à un fichier, on récupère son nom. Puis on vérifie que le fichier est de type .cf ou
	 * .ics. Si toutes les conditions sont réunies, on peut afficher le nom du fichier dans la zone fichiers. 
	 * @author vcaze
	 * @version 1.0
	 */
	class ActionChemins implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			jfc = new JFileChooser();
			jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = jfc.showOpenDialog(menuItemD);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
					File dir = jfc.getSelectedFile();
					String dirPath = dir.getPath();
					File[] list = dir.listFiles();
					if(list != null) {
						fichiers.append("Dossier choisi : " + dirPath + "\n");
						for(int i=0; i<list.length; i++) {
							String path = list[i].getName();
							if(list[i].isFile()) {
								if(list[i].getName().endsWith(".vcf") || list[i].getName().endsWith(".ics")) {
									fichiers.append("   " + path + "\n");
									
								}  
							}
						}
					}
			   } 
		 }
	}
	
	/** On ouvre un JFileChooser de sauvegarde. Si l'utilisateur entre son nom de fichier à sauvegarder en .vcf ou .ics, on
	 * récupère le contenu de "contenu" et on le sauvegarde dans ce fichier ( cela écrase aussi le contenu d'un fichier dejà
	 * existant ). Si le nom est en .ser, on fais la même récupération...mais sur un fichier binaire de sauvegarde.
	 * @author vcazeclass ActionGenerer
	 * @version 1.0
	 */
	class ActionEnregistrer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			jfc = new JFileChooser();
			int returnVal = jfc.showSaveDialog(menuItemS);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				int r = JOptionPane.showConfirmDialog(null, "Voulez vous enregistrer ce fichier ?", "Info", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(r == JOptionPane.YES_OPTION) {
					File file = jfc.getSelectedFile();
					if(file.getName().endsWith(".vcf") || file.getName().endsWith(".ics")) {
						BufferedWriter bw;
						try {
							bw = new BufferedWriter(new FileWriter(file));
							bw.write(contenu.getText());
							bw.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else if(file.getName().endsWith(".ser")) {
						FileOutputStream fos;
						try {
							fos = new FileOutputStream(file);
							ObjectOutputStream oos = new ObjectOutputStream(fos);
							oos.writeObject(contenu.getText());
							oos.close();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
						
					}
				}
			}
		}
	}
		
	
	
	/** On ouvre un JFileChooser de sauvegarde, on crée un tableau de String pour les lignes de "contenu" si l'utilisateur
	 * entre un nom de fichier en .html. On coupe chaque ligne par le ":", et on écris les lignes du .html suivant
	 * les conventions de balisages. 
	 * @author vcaze
	 * @version 1.0
	 */
	class ActionGenerer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			jfc = new JFileChooser();
			int returnVal = jfc.showSaveDialog(menuItemH);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				int r = JOptionPane.showConfirmDialog(null, "Voulez vous générer le fichier HTML ?", "Info", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(r == JOptionPane.YES_OPTION) {
					File file = jfc.getSelectedFile();
					if(file.getName().endsWith(".html")){
						FileWriter fw;
						try {
							fw = new FileWriter(file);
							BufferedWriter bw = new BufferedWriter(fw);
							String[] lines = contenu.getText().split("\n");
							int i = 0;
							while(i < lines.length) {
								String[] line = lines[i].split(":");
								if(lines[i].startsWith("BEGIN")) {
									bw.write("<div class=\"" + line[1] + "\">");
									bw.newLine();
									i++;
								}
								bw.write("<span class=\"" + line[0] + "\">" + line[1] + "</span>");
								bw.newLine();
								i++;
							}	
							bw.write("</div>");
							bw.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
				    }
			     }
		     }
		 
		}
	}
	
		
	
		
	
	/** Ouvre un dialogue de fermeture. 
	 * @author vcaze
	 * @version 1.0
	 */
	class ActionQuitter implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int r = JOptionPane.showConfirmDialog(null, "Voulez vous quitter ?", "Info", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(r == JOptionPane.YES_OPTION) {
				System.exit(0);
			}	
		}
	}
	
	
	public static void main(String[] args) {
		new GUIProjet();
	}
	
	
	

	}


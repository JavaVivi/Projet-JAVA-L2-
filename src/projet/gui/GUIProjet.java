/**
 * 
 */
package projet.gui;

import projet.data.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JButton;
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
	
	public GUIProjet() {
		
		JLabel jf = new JLabel("Fichiers : ");
		fichiers = new JTextArea(15,15);
		fichiers.setEditable(false);
		
		menuBar = new JMenuBar();
		
		JLabel jc = new JLabel("Contenu : ");
		contenu = new JTextArea(20,25);
		contenu.setEditable(true);
		
		menu = new JMenu("Fichier");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);
		
		menuItemO = new JMenuItem("Ouvrir");
		//menuItemO.addActionListener(new ActionOuvrir());
		menu.add(menuItemO);
		menu.addSeparator();
		
		menuItemD = new JMenuItem("Récuperer chemins");
		menuItemD.addActionListener(new ActionChemins());
		menu.add(menuItemD);
		menu.addSeparator();
		
		menuItemS = new JMenuItem("Sauvegarder");
		//menuItemS.addActionListener(new ActionEnregistrer());
		menu.add(menuItemS);
		menu.addSeparator();
		
		menuItemH = new JMenuItem("Generer HTML");
		//menuItemH.addActionListener(new ActionGenerer());
		menu.add(menuItemH);
		menu.addSeparator();
		
		menuItemQ = new JMenuItem("Quitter");
		menuItemQ.addActionListener(new ActionQuitter());
		menu.add(menuItemQ);
		menu.addSeparator();
		
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
	
	
	class ActionChemins implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			jfc = new JFileChooser();
			jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = jfc.showOpenDialog(menuItemO);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
					File dir = jfc.getSelectedFile();
					File[] list = dir.listFiles();
					if(list != null) {
						for(int i=0; i<list.length; i++) {
							String path = list[i].getAbsolutePath();
							if(list[i].isFile()) {
								if(list[i].getName().endsWith(".vcf") || list[i].getName().endsWith(".ics")) {
									//String path = list[i].getAbsolutePath();
									fichiers.append(path + "\n");
									
								}  
							}
						}
					}
			   } 
		 }
	}
	
	
		
	
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

package model;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.swing.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.*;

import dijkstra.Dijkstra;
import dijkstra.GraphInterface;
import dijkstra.PreviousInterface;
import dijkstra.VertexInterface;
import maze.ABox;
import maze.DBox;
import maze.EBox;
import maze.Maze;
import maze.MazeReadingException;
import maze.WBox;
import ui.*;

/*
 * Constructeur du model, on crée d'abord un labyrinthe vide (que des EBox) de taille par 
 * défault 5x12 pleine de EBox
 * On pourra aisément modifier le labyrinthe et sa taille par la suite en modifiant les
 * attributs height et width.
 * Le modèle contient une ref sur l'application, contient l'information du mode sélectionné,
 * c-a-d si l'on veut dessiner des WBox, EBox, ABox ou DBox.
 * On détecte aussi si le clic gauche est enfoncé, si le labyrinthe a été modifié ou sauvegardé.
 */
public final class MazeAppModel {
	private GraphInterface maze;
	private MazeApp mazeApp;
	private int height = 5;
	private int width = 12;
	private String selectedMode = "W";
	private Color selectedColor;
	private boolean modified = false ;
	private VertexInterface arrival;
	private VertexInterface departure;
	private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>() ;
	private ArrayList<VertexInterface> path = new ArrayList<VertexInterface>();
	private boolean solved = false;
	private boolean saved = false;
	private boolean clicked = false;
	
	public MazeAppModel(MazeApp mazeApp) {
		this.mazeApp = mazeApp;
		VertexInterface[][] boxes = new VertexInterface[height][width];
		this.maze = new Maze(boxes,height,width);
		for(int i=0;i < height;i++) {
			for(int j = 0; j < width;j++) {
				boxes[i][j] = new EBox(maze,i,j);
			}
		}
		this.setSelectedColor();
	}
	
	/*
	 * Est appelée par BoxPanelMouseListener
	 */
	public void setClicked(boolean bool) {
		this.clicked = bool;
	}
	/*
	 * indique si le clique gauche est enfoncé.
	 */
	public boolean getClicked() {
		return this.clicked;
	}
	
	/*
	 * indique où se trouve l'arrivée du labyrinthe, si cet attribut est nul, une exception
	 * du nom de "MazeSolvingException" sera levée lorsqu'on voudra résoudre le labyrinthe.
	 */
	public VertexInterface getArrival() {
		return this.arrival;
	}
	/*
	 * modifie l'emplacement du départ
	 */
	public void setArrival(VertexInterface arrival) {
		this.arrival = arrival;
		this.modified = true;
		this.setSaved(false);
		this.stateChanges();
	}
	/*
	 * indique où se trouve le départ du labyrinthe, si cet attribut est nul, une exception
	 * du nom de "MazeSolvingException" sera levée lorsqu'on voudra résoudre le labyrinthe.
	 */
	public VertexInterface getDeparture() {
		return this.departure;
	}
	/*
	 * modifie l'emplacement de l'arrivée
	 */
	public void setDeparture(VertexInterface departure) {
		this.departure = departure;
		this.modified = true;
		this.setSaved(false);
		this.stateChanges();
	}
	
	/*
	 * choisi la couleur avec laquelle sera peinte la BoxPanel en fonction du mode sélectionné.
	 */
	public void setSelectedColor() {
		if(selectedMode == "W") {
			this.selectedColor = Color.RED; 
		}else if(selectedMode == "E") {
			this.selectedColor = Color.GREEN;
		}else if(selectedMode == "D") {
			this.selectedColor = Color.YELLOW;
		}else if(selectedMode == "A") {
			this.selectedColor = Color.ORANGE;
		}
		this.stateChanges();
	}
	
	public Color getSelectedColor() {
		return this.selectedColor;
	}
	
	/*
	 * Est appelée dès que le labyrinthe est modifiée, et donc pas encore résolu, cela indique 
	 * l'utilisateur n'a pas encore cliqué sur "solve" et que la solution n'a pas encore
	 * été affichée.
	 */
	public void setSolved() {
		this.solved = false;
	}
	/*
	 * l'attribut modified est true dès que le labyrinthe est modifié.
	 */
	public void setModified(boolean bool) {
		this.modified = bool;
	}
	
	/*
	 * retourne la largeur (en nombre de case) du labyrinthe (axe horizontal)
	 */
	public int getWidth() {
		return this.width;
	}
	public void setWidth(int width) {
		if(this.width != width) {
			this.width = width;
			this.modified = true;
			this.setSaved(false);
			this.stateChanges();
		}
	}
	
	/*
	 * retourne la hauteur (en nombre de case) du labyrinthe (axe vertical)
	 */
	public int getHeight() {
		return this.height;
	}
	public void setHeight(int height) {
		if(this.height != height) {
			this.height = height;
			this.modified = true;
			this.setSaved(false);
			this.stateChanges();
		}
	}
	
	/*
	 * appelée pour modifier le modele
	 */
	public GraphInterface getMaze() {
		return this.maze;
	}
	
	/*
	 * modifie le mode sélectionné (WBox, EBox ...)
	 */
	public String getSelectedMode() {
		return this.selectedMode;
	}
	public void setSelectedMode(String selectedMode) {
		this.selectedMode = selectedMode;
		modified = true;
		this.setSelectedColor();
	}
	
	/*
	 * Demande au modèle l'instance de la MBox testée, en fonction de sa classe, 
	 * on indique la couleur avec laquelle la BoxPanel sera affichée dans un attribut de 
	 * la BoxPanel.
	 * Si solved est true et que la MBox est contenue dans le path, c'est à dire le chemin
	 * de la solution, alors on l'affiche en bleue
	 */
	public final void chooseColorBox(int i , int j) {
		VertexInterface bboxes[][] = this.maze.getBoxes();
		VertexInterface box = bboxes[i][j];
		if(box instanceof EBox) {
			//this.mazeApp.getMazePanel().getBoxesPanel()[i][j].setColor(Color.GREEN);
			if((path.contains(this.maze.getBoxes()[i][j]))&&(solved == true)) {
				this.mazeApp.getMazePanel().getBoxesPanel()[i][j].setColor(Color.BLUE);
			}else {
				this.mazeApp.getMazePanel().getBoxesPanel()[i][j].setColor(Color.GREEN);
			}
		}else if(box instanceof WBox) {
			this.mazeApp.getMazePanel().getBoxesPanel()[i][j].setColor(Color.RED);
		}else if(box instanceof DBox) {
			this.mazeApp.getMazePanel().getBoxesPanel()[i][j].setColor(Color.YELLOW);
		}else if(box instanceof ABox) {
			this.mazeApp.getMazePanel().getBoxesPanel()[i][j].setColor(Color.ORANGE);
		}
	}
	
	public void addObserver(ChangeListener listener) {
		this.listeners.add(listener) ;
	}
	
	/*
	 * appelée dès que le modèle est modifié
	 */
	public void stateChanges() {
        ChangeEvent evt = new ChangeEvent(this) ;
        //System.out.println("evt "+evt);
        for (ChangeListener listener : listeners) {
        	System.out.println("listener "+listener);
        	listener.stateChanged(evt);
        }
    }
	
	/*
	 * Sauvegarde le labyrinthe actuel.
	 */
	public void saveToFile() {
		System.out.println("model.saveToFile() does something");
		// parent component of the dialog
		JFrame parentFrame = new JFrame();
		 
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		 
		int userSelection = fileChooser.showSaveDialog(parentFrame);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    System.out.println("Save as file: " + fileToSave.getAbsolutePath()+".txt");
		    this.maze.saveToTextFile(fileToSave.getAbsolutePath()+".txt");
		    setSaved(true);
		    setModified(false);
		}
		
	}
	
	/*
	 * Charge un labyrinthe pré-existant dans un répertoire local.
	 * Peut engendrer des exceptions s'il n'y a pas de départ ou d'arrivée,
	 * s'il y a un problème de lecture ou une incohérence (largeur de labyrinthe variable 
	 * selon les lignes par exemple).
	 */
	public void loadToFile() {
		// parent component of the dialog
		setSaved(true);
	    setModified(false);
		JFrame parentFrame = new JFrame();
		 
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to load");   
		 
		int userSelection = fileChooser.showOpenDialog(parentFrame);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToLoad = fileChooser.getSelectedFile();
		    System.out.println("Load file: " + fileToLoad.getAbsolutePath());
		    //this.maze.initFromTextFile(fileToSave.getAbsolutePath());
		    Reader reader = null;
			BufferedReader br = null;
		    try {
		    	System.out.println("getting to load");
		    	
		    	//create a new lab of correct size and call the function to initialize it
		    	reader = new FileReader(fileToLoad.getAbsolutePath());
				br = new BufferedReader(reader);
				
				String line = null;
				String label = null;
				int height = 1;
				line = br.readLine();
				int width = line.length();
				while((line = br.readLine())!= null) {
					height++;
					width = line.length();
				}
		    	
				System.out.println("HEIGHT: "+ height);
				System.out.println("WIDTH: "+ width);
				
				
				GraphInterface mazeLocal;
				this.setHeight(height);
				this.setWidth(width);
				
				VertexInterface[][] boxes2 = new VertexInterface[height][width];
				mazeLocal = new Maze(boxes2,height,width);
				for(int i=0;i < height;i++) {
					for(int j = 0; j < width;j++) {
						boxes2[i][j] = new EBox(mazeLocal,i,j);
					}
				}
				
				this.maze = mazeLocal;
				
				//Repaint the MazePanel to fit the size of the new lab
				resize();
				
				this.maze.initFromTextFile(fileToLoad.getAbsolutePath());
				
				//Find the arrival and departure in the new lab to set them
				int[] coordD;
				coordD = searchDeparture(boxes2, fileToLoad.getAbsolutePath());
				setDeparture(this.maze.getBoxes()[coordD[0]][coordD[1]]);
				
				int[] coordA;
				coordA = searchArrival(boxes2, fileToLoad.getAbsolutePath());
				setArrival(this.maze.getBoxes()[coordA[0]][coordA[1]]);
				
			} catch (MazeReadingException e) {
				System.out.println("Loading error with file: "+fileToLoad.getAbsolutePath());
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				System.out.println("File not Found haha: "+fileToLoad.getAbsolutePath());
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Error with scanning: "+fileToLoad.getAbsolutePath());
				e.printStackTrace();
			} catch (MazeSolvingException e) {
				System.out.println("No departure or no arrival: "+fileToLoad.getAbsolutePath());
				e.printStackTrace();
			}
		}
		stateChanges();
	}
	
	/*
	 * indique si le modèle a été modifié
	 */
	public boolean isModified() {
		return modified;
	}
	/*
	 * indique si le modèle a été sauvegardé
	 */
	public boolean isSaved() {
		return saved;
	}
	
	/*
	 * rafraichit la fenetre dès que la souris passe au dessus d'une case BoxPanel.
	 */
	public void setBoxHovered(int i, int j, boolean bool) {
		this.maze.setBoxHovered(i, j, bool);
		stateChanges();
	}
	
	/*
	 * En fonction du mode sélectionné, si on click ou on maintient le click appuyé
	 * et on se déplace, alors on modifie la case correspondante du modèle en rechargeant
	 * MBox avec la bonne instance.
	 */
	public void setBox(int i, int j) {
		this.maze.setBox(i, j, selectedMode);
		if(selectedMode=="A") {
			if(arrival!=null) {
				int x = arrival.getRef()[0];
				int y = arrival.getRef()[1];
				this.setBoxForce(x, y, "E");
				this.arrival = this.maze.getBoxes()[i][j];
			}else {
				this.arrival = this.maze.getBoxes()[i][j];
			}
		}
		if(selectedMode=="D") {
			if(departure!=null) {
				int x = departure.getRef()[0];
				int y = departure.getRef()[1];
				this.setBoxForce(x, y, "E");
				this.departure = this.maze.getBoxes()[i][j];
			}else {
				this.departure = this.maze.getBoxes()[i][j];
			}
		}
		modified = true;
		setSaved(false);
		stateChanges();
	}
	/*
	 * Il n'y a qu'une seule arrivée et qu'un seul départ, on appelle cette fonction
	 * pour modifier l'ancien départ ou l'ancienne arrivée en EBox losqu'on positionne un
	 * nouveau départ ou une nouvelle arrivée.
	 */
	public void setBoxForce(int i, int j, String label) {
		this.maze.setBox(i, j, label);
		this.modified = true;
		setSaved(false);
	}
	
	/*
	 * reset le labyrinthe
	 */
	public void reset() {
		System.out.println("Full Reset");
		for(int i=0;i < height;i++) {
			for(int j = 0; j < width;j++) {
				this.setBoxForce(i, j, "E");
			}
		}
		modified = false;
		this.setSaved(true);
		stateChanges();
	}
	
	/*
	 * résoud le labyrinthe et n'affichera aucune solution s'il n'y en a pas,
	 * pas d'erreur mais aucun chemin ne sera affiché.
	 */
	public void solve() {
		System.out.println("Solving");
		if((arrival==null)||(departure==null)) {
			System.out.println("Put arrival and departure");
			JFrame jFrame = new JFrame();
			JOptionPane.showMessageDialog(jFrame, "Put arrival and departure");
		}else {
			PreviousInterface previous = Dijkstra.dijkstra(maze, departure);
			
			VertexInterface currentCase = arrival;
			path = new ArrayList<VertexInterface>();
			while(currentCase!=null) {
				currentCase.printPos();
				if((currentCase.getLabel2()!="A")&&(currentCase.getLabel2()!="D")){
					path.add(currentCase);
				}
				currentCase = previous.getDad(currentCase);
				
			}
			for(VertexInterface[] line : this.maze.getBoxes()) {
				for(VertexInterface box : line) {
					if(path.contains(box)) {
						System.out.print(". ");
					} else {
						System.out.print(box.getLabel2()+" ");
					}
				}
				System.out.println();
			}
			stateChanges();
			solved = true;
		}
	}
	
	/*
	 * N'est pas une fonction test, mais permet de modifier la taille du labyrinthe
	 * en fonction de ce qu'a indiqué l'utilisateur
	 * En indiquant dans les champs setHeight et setWidth de l'interface graphique les
	 * nouvelles dimensions, l'appuie sur Enter indique dans ces cases textes
	 * appellent la fonction changeDimensions qui recrée un nouveau labyrinthe vide aux dimensions
	 * appropriées. Ici on modifie uniquement le modèle.
	 */
	public void changeDimensions(int height, int width) {
		System.out.println("test");
		GraphInterface mazeLocal;
		this.setHeight(height);
		this.setWidth(width);
		VertexInterface[][] boxes2 = new VertexInterface[height][width];
		mazeLocal = new Maze(boxes2,height,width);
		for(int i=0;i < height;i++) {
			for(int j = 0; j < width;j++) {
				boxes2[i][j] = new EBox(mazeLocal,i,j);
			}
		}
		this.maze = mazeLocal;
		//this.setSelectedColor();
		resize();
	}
	
	/*
	 * Une fois la taille dans le modèle modifiée, on appelle resize de l'application,
	 * qui recrée un nouveau MazePanel (le panel contenant toutes les cases du labyrinthes)
	 * aux dimensions appropriées et supprime le précédent
	 */
	public void resize() {
		this.mazeApp.resize();
	}
	
	public void prepareHeight(int height) {
		changeDimensions(height,width);
	}
	
	public void prepareWidth(int width) {
		changeDimensions(height,width);
	}
	
	public void setSaved(boolean boo) {
		this.saved = boo;
	}
	
	/*
	 *  searchDeparture cherche l'emplacement du départ et lance une erreur si rien n'est trouvé
	 */
	public int[] searchDeparture(VertexInterface[][] boxes,String fileName) throws MazeSolvingException {
		//Find the arrival and departure in the new lab to set them
		int[] coordD = new int[2];
		int i_d = -1;
		int j_d = -1;
		for(VertexInterface[] list : boxes) {
			for(VertexInterface box : list) {
				if(box instanceof DBox) {
					System.out.println("departure: "+box.getRef()[0]+"; "+box.getRef()[1]);
					i_d = box.getRef()[0];
					j_d = box.getRef()[1];
				}
			}
		}
		System.out.println("Departure: x = "+i_d+" y = "+j_d);
		coordD[0] = i_d;
		coordD[1] = j_d;
		if((i_d == -1)&&(j_d == -1)) {
			throw new MazeSolvingException(fileName,"No Departure");
		}
		return coordD;
	}
	/*
	 *  searchArrival cherche l'emplacement de l'arrivée et lance une erreur si rien n'est trouvé
	 */
	public int[] searchArrival(VertexInterface[][] boxes,String fileName) throws MazeSolvingException {
		//Find the arrival and departure in the new lab to set them
		int[] coordA = new int[2];
		int i_a = -1;
		int j_a = -1;
		for(VertexInterface[] list : boxes) {
			for(VertexInterface box : list) {
				if(box instanceof ABox) {
					System.out.println("arrival: "+box.getRef()[0]+"; "+box.getRef()[1]);
					i_a = box.getRef()[0];
					j_a = box.getRef()[1];
				}
			}
		}
		System.out.println("Departure: x = "+i_a+" y = "+j_a);
		coordA[0] = i_a;
		coordA[1] = j_a;
		if((i_a == -1)&&(j_a == -1)) {
			throw new MazeSolvingException(fileName,"No Arrival");
		}
		return coordA;
	}
	
}

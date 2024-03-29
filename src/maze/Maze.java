package maze;

import java.io.*;
import java.util.ArrayList;

import dijkstra.GraphInterface;
import dijkstra.VertexInterface;

public class Maze implements GraphInterface{
	private int height;
	private int width;
	private VertexInterface[][] boxes;
	
	/*
	 * contient les MBox du mod�le du labyrinthe
	 */
	public Maze(VertexInterface[][] boxes, int height, int width) {
		super();
		this.boxes = boxes;
		this.height = height;
		this.width = width;
	}
	
	/*
	 * indique si deux cases sont li�es (on peut passer d'une case � l'autre en un pas).
	 */
	@Override
	public boolean links(VertexInterface r, VertexInterface y) {
		int[] ref_r = r.getRef();
		int[] ref_y = y.getRef();
		if(!(r instanceof WBox)&&!(y instanceof WBox)) {
			int x_r = ref_r[0];
			int y_r = ref_r[1];
			int x_y = ref_y[0];
			int y_y = ref_y[1];
			if((x_r+1==x_y&&y_r==y_y)||(x_r-1==x_y&&y_r==y_y)||(x_r==x_y&&y_r+1==y_y)||(x_r==x_y&&y_r-1==y_y)) {
				if(this.testIfIsIn(r)&&this.testIfIsIn(y)) {
					return true;
				}
			}
		}
		return false;  // Il faut faire attention � ne pas sortir du graph > test indices
	}
	/*
	 * teste si la case est contenue dans le labyrinthe (bonnes dimensions)
	 */
	private boolean testIfIsIn(VertexInterface r) {
		int[] ref_r = r.getRef();
		int x_r = ref_r[0];
		int y_r = ref_r[1];
		if((x_r<this.height)&&(x_r>=0)&&(y_r<this.width)&&(y_r>=0)) {
			return true;
		}else {
			return false;
		}
	}
	
	/*
	 * Affiche le labyrinthe pour les tests
	 */
	public void printMaze() {
		for(VertexInterface[] line : this.boxes) {
			for(VertexInterface box : line) {
				box.printLabel();
			}
			System.out.println();
		}
	}
	
	/*
	 * indique le poid de parcours entre deux cases, ici ce sera toujours 1, (1 pas 
	 * pour parcourir entre 2 cases juxtapos�es)
	 */
	@Override
	public int getWeightLink(VertexInterface r, VertexInterface y) {
		if(this.testIfIsIn(r)&&this.testIfIsIn(y)) {
			return 1;
		}
		return 99999;
	}
	
	/*
	 * retourne toutes les MBox pour les parcourir
	 */
	@Override
	public ArrayList<VertexInterface> getAllVertex() {
		ArrayList<VertexInterface> all = new ArrayList<VertexInterface>();
		for(VertexInterface[] line : this.boxes) {
			for(VertexInterface box : line) {
				all.add(box);
				box.printLabel();
			}
		}
		System.out.println();
		return all;
	}
	
	/*
	 * renvoie les successeurs d'une case (les voisins accessibles)
	 */
	@Override
	public ArrayList<VertexInterface> getSuccessors(VertexInterface r) {
		ArrayList<VertexInterface> successors = new ArrayList<VertexInterface>();
		String label;
		for(VertexInterface[] line : this.boxes) {
			for(VertexInterface box : line) {
				label = box.getLabel2();
				if(label=="E") {
					if(this.links(r, box)) {
						successors.add(box);
						box.printPos();
					}
				}else if(label=="A") {
					if(this.links(r, box)) {
						System.out.println("Arrivee atteinte");
						successors.add(box);
						box.printPos();
					}
				}
				
				/*
				if(this.links(r, box)) {
					successors.add(box);
					box.printLabel();
				}
				*/
			}
		}
		return successors;
	}
	
	/*
	 * pour charger le labyrinthe � partir d'un fichier texte
	 */
	public final void initFromTextFile(String fileName) throws MazeReadingException {
		Reader reader = null;
		BufferedReader br = null;
		try {
			reader = new FileReader(fileName);
			br = new BufferedReader(reader);
			
			int i = 0;
			String line = null;
			String label = null;
			while((line = br.readLine())!= null) {
	            //System.out.print(line);
	            //System.out.println(line.length());
	            if (line.length() != this.width) {
	            	throw new MazeReadingException(fileName,i,"NumberOfColumns");
	            } else {
	            	for(int j = 0;j<line.length();j++) {
	            		char b = line.charAt(j);
	            		label = String.valueOf(b);
	            		System.out.print(b);
	            		if(label.equals("E")) {
	            			this.boxes[i][j] = new EBox(this,i,j);
	            		}else if(label.equals("W")) {
	            			this.boxes[i][j] = new WBox(this,i,j);
	            		}else if(label.equals("D")) {
	            			this.boxes[i][j] = new DBox(this,i,j);
	            		}else if(label.equals("A")) {
	            			this.boxes[i][j] = new ABox(this,i,j);
	            		}else {
	            			System.out.println(i);
	            			System.out.println(j);
	            			System.out.println(b);
	            			throw new MazeReadingException(fileName,i,"UnrecognizedLabel");
	            		}
	            	}
	            	System.out.println();
	            	i++;
	            	if (i>this.height) {
	            		throw new MazeReadingException(fileName,i,"NumberOfRows");
	            	}
	            }
	        }
			
        	
		} catch (MazeReadingException e) {
			System.out.println("MazeReading: "+e);
			
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound: "+e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO: "+e);
			e.printStackTrace();
		} finally {
			try {
				br.close();
				reader.close();
			} catch (Exception e) {
			}
		}
	}
	
	/*
	 * pour sauvegarder le labyrinthe dans un fichier texte.
	 */
	public final void saveToTextFile(String fileName) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(fileName);
			for(VertexInterface[] line : this.boxes) {
				for(VertexInterface box : line) {
					pw.print(box.getLabel2());
				}
				pw.println();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			try {
				pw.flush();
				pw.close();
			} catch (Exception e) {
			}
		}
		
	}

	@Override
	public VertexInterface[][] getBoxes() {
		return this.boxes;
	}
	
	public void setBox(int i, int j, String label) {
		if(label == "W") {
			boxes[i][j] = new WBox(this,i,j);
		}else if(label == "E") {
			boxes[i][j] = new EBox(this,i,j);
		}else if(label == "A") {
			boxes[i][j] = new ABox(this,i,j);
		}else if(label == "D") {
			boxes[i][j] = new DBox(this,i,j);
		}else {
			System.out.println("Error of label in Maze.setBox");
		}
		
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public int getWidth() {
		return this.width;
	}
	
	public void setBoxHovered(int i, int j, boolean bool) {
		boxes[i][j].setBoxHovered(bool);
	}
	
}

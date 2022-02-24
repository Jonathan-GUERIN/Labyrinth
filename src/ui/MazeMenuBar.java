package ui;

import javax.swing.*;

public class MazeMenuBar extends JMenuBar{
	private FileMenu fileMenu;
	//private QuitMenuItem quitMenuItem;
	//private LoadItem loadItem;
	//private SaveItem saveItem;
	
	public MazeMenuBar(MazeApp mazeApp) {
		super();
		fileMenu = new FileMenu(mazeApp);
		add(fileMenu);
		//quitMenuItem = new QuitMenuItem(mazeApp);
		//add(quitMenuItem);
		//loadItem = new LoadItem(mazeApp);
		//add(loadItem);
		//saveItem = new SaveItem(mazeApp);
		//add(saveItem);
	}
	
}

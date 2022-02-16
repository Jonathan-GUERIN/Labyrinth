package ui;

import javax.swing.*;

public class FileMenu extends JMenu{
	private final QuitMenuItem quitMenuItem;
	private final LoadItem loadItem;
	private final SaveItem saveItem;
	
	public FileMenu(MazeApp mazeApp) {
		super("File");
		quitMenuItem = new QuitMenuItem(mazeApp);
		add(quitMenuItem);
		loadItem = new LoadItem(mazeApp);
		add(loadItem);
		saveItem = new SaveItem(mazeApp);
		add(saveItem);
	}
	
}

package ui;

public class MazeSolvingException extends Exception {
	private final String file;
	private final String msg;
	/*
	 * Exception lev�e lorsqu'il n'y a pas de d�part ou d'arriv�e
	 */
	public MazeSolvingException(String file, String msg) {
		super(msg+"; "+"; "+file);
		this.file = file;
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg+"; "+"; "+file;
	}
}

package maze;

public class MazeReadingException extends Exception {
	private final String file;
	private final int line;
	private final String msg;
	public MazeReadingException(String file, int line, String msg) {
		super(msg+"; "+line+"; "+file);
		this.file = file;
		this.line = line;
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg+"; "+line+"; "+file;
	}
}

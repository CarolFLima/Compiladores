
public class LexicalException extends RuntimeException {

	public LexicalException(String msg){
		super(msg);
	}
	
	public LexicalException(Throwable e){
		super(e);
	}
	
	public LexicalException(char value, int row, int column){
		super("Value: " + value + " Posicao(  " + row + ", " + column+ ")");
	}
	
}

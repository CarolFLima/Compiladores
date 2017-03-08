
public class Token {

	private Terminals type;
	private String value;
	private int row;
	private int column;
	
	public Token(Terminals type, String value, int row, int column){
		this.type = type;
		this.value = value;
		this.row = row;
		this.column = column;
	}
	
	// **************** Getters and setters ****************
	public Terminals getType() {
		return type;
	}
	public void setType(Terminals type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	
	// **************** Methods ****************
	
	@Override 
	public String toString(){

		StringBuilder builder = new StringBuilder();
		
		builder.append("[").append(this.getType());
		builder.append(", ").append(this.getValue());
		builder.append(", Linha: ").append(this.getRow());
		builder.append(", Coluna: ").append(this.getColumn());
		builder.append("]");
		
		return builder.toString();
	}
	
	
}


public enum Terminals {


	CENTRAL("reservedword", "central") ,
	FOR ("reservedword", "for") ,
	WHILE ("reservedword", "while") ,
	IF ("reservedword", "if") ,
	ELSE ("reservedword", "else") ,
	SWITCH ("reservedword", "switch") ,
	CASE ("reservedword", "case") ,
	DEFAULT ("reservedword", "default") ,
	BREAK ("reservedword", "break") ,
	RETURN ("reservedword", "return") ,
	VOID ("reservedword", "void") ,
	CONSTANT ("reservedword", "cons") ,
	INTEGER ("reservedword", "int") ,
	FLOAT ("reservedword", "float") ,
	CHARACTER ("reservedword", "char") ,
	STRING ("reservedword", "string") ,
	BOOLEAN ("reservedword", "boolean") ,
	FALSE ("reservedword", "false") ,
	TRUE ("reservedword", "true") ,
	DEFINE ("reservedword", "define") ,
	ARRAY ("reservedword", "cons") ,

	IDENTIFIER ("identifier", "") ,
	DATA_TYPE ("data_type", "") ,
	
	ASSIGN ("operator", "=") ,
	PLUS ("operator", "+") ,
	MINUS ("operator", "-") ,
	TIMES ("operator", "*") ,
	DIV ("operator", "/") ,
	MOD ("operator", "%") ,
	EXP ("operator", "ˆ") ,
	NEG ("operator", "-") ,
	GREATER ("operator", ">") ,
	LESS ("operator", "<") ,
	EQUALG ("operator", ">=") ,
	EQUALL ("operator", "<=") ,
	EQUAL ("operator", "==") ,
	DIFF ("operator", "!=") ,
	NOT ("operator", "!") ,
	AND ("operator", "&&") ,
	OR ("operator", "||") ,
	PLUSA ("operator", "++") ,
	MINUSA ("operator", "--") ,
	PLUSE ("operator", "+=") ,
	LESSE ("operator", "-=") ,
	TIME ("operator", "*=") ,
	DIVE ("operator", "/=") ,
	
	AC ("delimiter", "{") ,
	FC ("delimiter", "}") ,
	AP ("delimiter", "(") ,
	FP ("delimiter", ")") ,
	ACO ("delimiter", "[") ,
	FCO ("delimiter", "]") ,
	AA ("delimiter", "\"") ,
	FA ("delimiter", "\"") ;

	private String type;
	private String value;

	private Terminals(String type, String value){
		this.type = type;
		this.value = value;
	}

	// **************** Getters and setters ****************
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	// **************** Methods ****************
	public static boolean contains(String type, String value){
		for (Terminals terminal : Terminals.values()) {
			if(terminal.getValue().equals(value)){
				if(terminal.getType().equals(type)){
					return true;
				}
			}
		}
		return false;
	}

	
	
}

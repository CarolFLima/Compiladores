
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
    
    IDENTIFIER ("Identifier", "") ,
    
    ASSIGN ("operator", "=") ,
    PLUS ("operator", "+") ,
    MINUS ("operator", "-") ,
    DIV ("operator", "/") ,
    EXP ("operator", "ˆ") ,
    NEG ("operator", "-") ,
    GREATER ("operator", ">") ,
    LESS ("operator", "<") ,
    EQUALG ("operator", ">=") ,
    EQUALL ("operator", "<=") ,
    EQUAL ("operator", "==") ,
    DIFF ("operator", "!=") ,
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
}

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class LexicalAnalyzer {

	private int row;

	private int column;

	private List<Character> source;	

	public LexicalAnalyzer(String file){
		this.row = this.column = 1;
		this.source = this.buildSource(file);
	}

	private List<Character> buildSource(String file) {
		List<Character> source = new LinkedList<Character>();

		try {
			InputStream input = new FileInputStream(file);

			for (int c = input.read(); c > 0; c = input.read()){
				if(c == '\n'){
					source.add('\n');
				} else if (c == '\t'){
					source.add('\t');
				} else {
					source.add((char) c);
				}
			}

			input.close();
		} catch (IOException e) {
			//TODO
		}

		return source;
	}

	// Test
	public void printSource(){	
		System.out.println(source.toString());
	}

	private List<Character> getSource() {
		return this.source;	
	}

	private void setSource(List<Character> source) {
		this.source = source;
	}	

	public boolean hasNext(){
		return !this.getSource().isEmpty();
	}

	private Character nextCharacter() {
		return getSource().remove(0);
	}

	private void insertCharacter(Character character){
		getSource().add(0, character);
	}

	public Token nextToken(){

		if(hasNext()){
			Character character = this.nextCharacter(); 

			if(character == ' ' || character == '\t' ){
				return this.nextToken();
			}
			if(character == '\n'){
				//TODO definir \0 depois
				return this.nextToken();
			} 

			if(character == '%'){
				Character nextCharacter = this.nextCharacter();
				if(nextCharacter == '%' || nextCharacter == '*'){
					return this.scanComment(character);
				} 
				return this.scanOperator(character);
			}

			if((int) character == 34 )
				return this.scanCharacterChain();				
			
			if((int) character == 39)
				return this.scanChar();

			if(character.isDigit(character) || character == '.')
				return this.scanNumber(character);

			if(Terminals.contains("operator", character.toString()))
				return this.scanOperator(character);

			if(character == '&' || character == '|')
				return this.scanOperator(character);

			if(Terminals.contains("delimiter", character.toString()))
				return this.buildToken("delimiter", character.toString());
			
			if(Character.isLetter(character) || character == '_')
				return this.scanWord(character);

			throw new LexicalException("No more tokens.");	
		}
		return null;
	}


	private Token buildToken(String type, String value) {
		// TODO Auto-generated method stub
		System.out.println("Value: " + value + " Type: " + Terminals.getTerminal(type, value).toString());
		return new Token(Terminals.getTerminal(type, value), value, this.row, this.column - value.length());
	}
	
	private Token scanWord(Character initial) {
		StringBuilder word = new StringBuilder();
		Character nextCharacter = initial;
		if(!Character.isLetter(initial)){
			// TODO joga um errinho aqui pq nao comecou com letra
		}
		
		while(true){
			if(!this.hasNext()){
				// TODO para no fim de tudo e manda uma lexicalexception pq o ultimo valor n pode ser um numero
			}
			
			if(!Character.isLetterOrDigit(nextCharacter) && nextCharacter != '_'){
				if(Terminals.contains("reservedword", word.toString())){
					this.insertCharacter(nextCharacter);
					return buildToken("reservedword", word.toString());	
				}
				// TODO return this.buildToken("IDENTIFIER", word.toString());
				this.insertCharacter(nextCharacter);
				return buildToken("identifier", word.toString());	
				
			}
			word.append(nextCharacter);
			nextCharacter = this.nextCharacter();
		}

	}

	private Token scanNumber(Character initial) {

		int dotFlag = 0;
		StringBuilder word = new StringBuilder();
		Character nextCharacter = initial;
		
		while(true){
			
			if(!this.hasNext()){
				// TODO para no fim de tudo e manda uma lexicalexception pq o ultimo valor n pode ser um numero
			}

			if(!Character.isDigit(nextCharacter) && nextCharacter != '.'){
				this.insertCharacter(nextCharacter);
				return buildToken("data_type", word.toString());
			}

			if(nextCharacter == '.' && dotFlag < 1){
				dotFlag++;
			} else if (dotFlag > 1){
				System.out.println("DEU ERRO"); // TODO lexical exception
			}
			
			word.append(nextCharacter);
			nextCharacter = this.nextCharacter();

		}
	}

	private Token scanChar(){
		StringBuilder word = new StringBuilder();
		Character nextCharacter = this.nextCharacter();
		word.append(nextCharacter);
		
		if((int) this.nextCharacter() != 39){
			// TODO throw um erro aqui caso tenha mais de um char entre os apostrofos
		}
		return buildToken("data_type", word.toString());
	}
	
	private Token scanCharacterChain() { 
		StringBuilder word = new StringBuilder();
		Character nextCharacter = this.nextCharacter();
		while(true){
			if(!this.hasNext()){
				// TODO para no fim de tudo e manda uma lexicalexception pq o ultimo valor n pode ser um numero
			}
			if((int) nextCharacter == 34){
				return buildToken("data_type", word.toString());
			}
			
			word.append(nextCharacter);
			nextCharacter = this.nextCharacter();
		}
	}

	private Token scanOperator(Character initial) {
		StringBuilder word = new StringBuilder();
		word.append(initial);

		if(!this.hasNext()){
			//TODO throw error
		}
		
		Character nextCharacter = this.nextCharacter();
		word.append(nextCharacter);
		
		if(Terminals.contains("operator", word.toString()))
			return buildToken("operator", word.toString());
		else 
			return buildToken("operator", initial.toString());
		
	}

	private Token scanComment(Character character) {
		// TODO Auto-generated method stub
		return null;
	}






}

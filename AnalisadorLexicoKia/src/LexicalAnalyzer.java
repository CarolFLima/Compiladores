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
		//System.out.println("Esta printando de novo");
		getSource().add(0, character);
		//this.printSource();
	}

	public Token nextToken(){

		if(hasNext()){
			Character character = this.nextCharacter(); 

			if(character == ' ' || character == '\t' ){
				//System.out.println("Entrou no if do espaco");
				return this.nextToken();
			}
			if(character == '\n'){
				//System.out.println("Entrou no if do barra n");
				//TODO definir \0 depois
				return this.nextToken();
			} 

			if(character == '%'){
				Character nextCharacter = this.nextCharacter();
				if(nextCharacter == '%' || nextCharacter == '*'){
					return this.scanComment(character);
				} 
				return scanOperator(character);
			}

			if(Character.isLetter(character) || character == '_')
				return scanWord(character);

			if((int) character == 34 )
				return scanCharacterChain();				
			
			if((int) character == 39)
				return scanChar();

			if(character.isDigit(character) || character == '.')
				return scanNumber(character);

			if(Terminals.contains("operator", character.toString()))
				return scanOperator(character);

			if(character == '&' || character == '|')
				return scanOperator(character);

			if(Terminals.contains("delimiter", character.toString()))
				return this.buildToken("delimiter", character.toString());


			throw new LexicalException("No more tokens.");	
		}
		return null;
	}


	private Token buildToken(String type, String value) {
		// TODO Auto-generated method stub
		System.out.println("Value: " + value + " Type:" + type);
		return this.nextToken();
	}

	private Token scanNumber(Character initial) {

		int dotFlag = 0;
		Terminals type = Terminals.INTEGER;
		StringBuilder word = new StringBuilder();
		Character nextCharacter = initial;
		
		while(true){
			
			if(!hasNext()){
				// TODO para no fim de tudo e manda uma lexicalexception pq o ultimo valor n pode ser um numero
			}

			if(!Character.isDigit(nextCharacter) && nextCharacter != '.'){
				this.insertCharacter(nextCharacter);
				return buildToken(type.toString(), word.toString());
			}

			if(nextCharacter == '.' && dotFlag < 1){
				type = Terminals.FLOAT;
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
		return buildToken(Terminals.CHARACTER.toString(), word.toString());
	}
	
	private Token scanCharacterChain() { 
		StringBuilder word = new StringBuilder();
		Character nextCharacter = this.nextCharacter();
		while(true){
			if(!hasNext()){
				// TODO para no fim de tudo e manda uma lexicalexception pq o ultimo valor n pode ser um numero
			}
			if((int) nextCharacter == 34){
				return buildToken(Terminals.STRING.toString(), word.toString());
			}
			
			word.append(nextCharacter);
			nextCharacter = this.nextCharacter();
		}
	}

	private Token scanWord(Character character) {
		// TODO Auto-generated method stub

		return this.nextToken();
	}

	private Token scanOperator(Character character) {
		// TODO Auto-generated method stub
		return null;
	}

	private Token scanComment(Character character) {
		// TODO Auto-generated method stub
		return null;
	}






}

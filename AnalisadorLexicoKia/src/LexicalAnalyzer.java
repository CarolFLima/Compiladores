import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class LexicalAnalyzer {

	private int row;

	private int column;

	private List<Character> source;	

	private int sourcePointer = 0;

	public LexicalAnalyzer(String file){
		this.row = this.column = 1;
		this.source = this.buildSource(file);
	}

	private List<Character> buildSource(String file) {
		List<Character> source = new LinkedList<Character>();

		try {
			InputStream input = new FileInputStream(file);

			for (int c = input.read(); c > 0; c = input.read())
				source.add((char) c);

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

	//			public void nextToken(){
	//				this.getSource().add(5, '\0');
	//				
	//				int i = 0;
	//				while(true){
	//					if(hasNext()){
	//						
	//						Character c = this.getSource().get(0);
	//							
	//						if((int) c == 39){
	//							System.out.println("Eh aspas");
	//						}
	//						System.out.println("Index: " + i + ", " + c);
	//						this.getSource().remove(0);
	//						i++;
	//						
	//					} else {
	//						return;
	//					}
	//				}
	//			
	//			}

	private Character nextCharacter() {

		return getSource().remove(0);
	}

	private void insertCharacter(Character character){
		getSource().add(0, character);
	}

	public Token nextToken(){

		if(hasNext()){
			Character character = this.nextCharacter(); 
			System.out.println("passou aqui " );
			if(character == '\t')
				System.out.println("Teve barra t");
			if(character == ' ' || character == '\t' || character == '\n'){
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

			if((int) character == 34 || (int) character == 39 )
				return scanCharacter(character);

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
		System.out.println(value);
		return null;
	}

	private Token scanNumber(Character initial) {
		// TODO Auto-generated method stub
		
		int dotFlag = 0;
		Terminals type = Terminals.INTEGER;
		StringBuilder word = new StringBuilder();
		Character nextCharacter = initial;
		while(true){
			word.append(nextCharacter);

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
			nextCharacter = nextCharacter();

		}
	}

	private Token scanCharacter(Character character) {
		// TODO Auto-generated method stub
		return this.nextToken();
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


public class Main {

	public static void main(String[] args) {
		
		/*
		// Test
		String file = "files/helloWorld.txt";
		LexicalAnalyzer la = new LexicalAnalyzer(file);
		
		la.printSource();
		
		while(la.hasNext()){
			// Retorna um token
			la.nextToken();
		}
		*/
		
		
		SyntacticAnalyzer sa = new SyntacticAnalyzer();
		sa.init();

	}

}

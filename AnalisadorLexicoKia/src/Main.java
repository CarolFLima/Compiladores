
public class Main {

	public static void main(String[] args) {
		
		// Test
		String file = "files/helloWorld.txt";
		LexicalAnalyzer la = new LexicalAnalyzer(file);
		
		la.printSource();
	}

}

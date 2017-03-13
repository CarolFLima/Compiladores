
public class Main {

	public static void main(String[] args) {

		// Test file 1
		String file1 = "files/helloWorld.txt";
		LexicalAnalyzer la1 = new LexicalAnalyzer(file1);

		while(la1.hasNext()){
			la1.nextToken();
		}

		// Test file 2
		String file2 = "files/fibonacci.txt";
		LexicalAnalyzer la2 = new LexicalAnalyzer(file2);

		while(la2.hasNext()){
			la2.nextToken();
		}

		// Test file 3
		String file3 = "files/quick_sort.txt";
		LexicalAnalyzer la3 = new LexicalAnalyzer(file3);

		while(la3.hasNext()){
			la3.nextToken();
		}
	}

}

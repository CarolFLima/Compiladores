import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
		List<Character> source = new ArrayList<Character>();
		
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
}

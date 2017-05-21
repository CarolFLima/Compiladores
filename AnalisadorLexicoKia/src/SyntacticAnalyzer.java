
public class SyntacticAnalyzer {

	String file = "files/helloWorld.txt";
	LexicalAnalyzer la = new LexicalAnalyzer(file);

	Token currToken;

	public void init(){
		this.next();
		this.LCmd();
	}

	private void next(){
		if(la.hasNext()){
			currToken = la.nextToken();
		} else {
			System.out.println("Acabaram os tokens");
			while(true){
				//Criar uma solucao mais elegante
			}
			//throw new LexicalException("No more tokens.");	// MUDAR AQUI PRA ERRO SINTATICO
		}
		
		System.out.println("Achei um " + currToken.getType().toString() );

	}

	private void LCmd(){
		System.out.println("=======================");
		System.out.println("Comecou aqui o LCMD");
		this.Cmd();
		this.LCnre();
	}

	private void LCnre() {
		next();
		if(currToken.getType().equals(Terminals.PV)){
			this.Cmd();
			this.LCnre();
		}
	}

	private void Cmd() {
		if(currToken.getType().equals(Terminals.INTEGER) || currToken.getType().equals(Terminals.FLOAT) || 
				currToken.getType().equals(Terminals.CHARACTER) || currToken.getType().equals(Terminals.STRING) || 
				currToken.getType().equals(Terminals.BOOLEAN) || currToken.getType().equals(Terminals.CONSTANT) ||
				currToken.getType().equals(Terminals.VOID)){
			this.Decl();

		} else if(currToken.getType().equals(Terminals.IF)){
			this.Cond();
			// chamar Cond()
		} else if(currToken.getType().equals(Terminals.WHILE) || currToken.getType().equals(Terminals.FOR)){
			// chamar Iter()
		} else if(currToken.getType().equals(Terminals.IDENTIFIER)){
			// chamar Atrib()
		} else {
			// throw Erro(currToken, "nao esperado")
		}
	}

	private void Decl(){
		Tipo();
		next();
		LId();

	}

	private void Tipo(){
		System.out.println("Achou um tipo");
	}

	private void LId(){
		if(currToken.getType().equals(Terminals.IDENTIFIER)){
			next();
			LInre();
		}
	}

	private void LInre(){
		if(currToken.getType().equals(Terminals.VG)){
			next();
			if(currToken.getType().equals(Terminals.IDENTIFIER)){
				next();
				LInre();
			} else {
				// lanca um erro de id esperado
			}
		}
	}

	private void Cond(){
		this.next();
		if(currToken.getType().equals(Terminals.AP)){
			this.next();
			this.Ebool();
			this.next();
			if(currToken.getType().equals(Terminals.FP)){ // TODO temos a opcao de nao abrir chaves?
				this.next();
				if(currToken.getType().equals(Terminals.AC)){
					this.next();
					this.LCmd();
					this.Else();
				}
			}
		} 
	}

	private void Else(){
		this.next();
		if(currToken.getType().equals(Terminals.FC)){
			this.next();
			if(currToken.getType().equals(Terminals.ELSE)){
				this.next();
				if(currToken.getType().equals(Terminals.AC))
					this.LCmd();
				this.next();
				if(currToken.getType().equals(Terminals.FC))
					this.next();
			}
		}
	}

	private void Ebool(){

	}
}
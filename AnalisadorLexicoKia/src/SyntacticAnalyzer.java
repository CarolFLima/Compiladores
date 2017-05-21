
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
		System.out.println("Achou no LCMD " + currToken.getType());
		this.Cmd();
		this.LCnre();
	}

	private void LCnre() {
		
		//TODO entra aqui so se nao for chaves
		if(!currToken.getType().equals(Terminals.FC))
			this.next();
		if(currToken.getType().equals(Terminals.PV)){
			this.next();
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
		} else if(currToken.getType().equals(Terminals.WHILE) || currToken.getType().equals(Terminals.FOR)){
			this.Iter();
		} else if(currToken.getType().equals(Terminals.IDENTIFIER)){
			this.Atrib();
		} else {
			//throw Erro(currToken, "nao esperado")
			System.out.println("NAO ACHOU NENHUM COMANDO " + currToken.getType());
		}
	}

	private void Decl(){
		this.Tipo();
		this.next();
		this.LId();
	}

	private void Tipo(){
		System.out.println("Achou um TIPO");
	}

	private void LId(){
		if(currToken.getType().equals(Terminals.IDENTIFIER)){
			this.next();
			this.LInre();
		}
	}

	private void LInre(){
		if(currToken.getType().equals(Terminals.VG)){
			this.next();
			if(currToken.getType().equals(Terminals.IDENTIFIER)){
				this.next();
				this.LInre();
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

	private void Iter(){
		if(currToken.getType().equals(Terminals.FOR)){
			this.next();
			if(currToken.getType().equals(Terminals.AP)){
				this.next();
				
				this.Decl();
				
				if(currToken.getType().equals(Terminals.PV)){
					this.next();
					this.Expr();
					this.next();
					if(currToken.getType().equals(Terminals.PV)){
						this.next();
						this.Expr();
						this.next();
//						System.out.println("===================Achou um " + currToken.getType());
						if(currToken.getType().equals(Terminals.FP)){
							this.next();
							if(currToken.getType().equals(Terminals.AC)){
								this.LCmd();
								this.next();
							}
							if(currToken.getType().equals(Terminals.FC)){
								return;
							}
						}
					}
				}
			}

		} else if(currToken.getType().equals(Terminals.WHILE)){
			this.next();
			if(currToken.getType().equals(Terminals.AP)){
				this.next();
				this.Expr();
				this.next();
				if(currToken.getType().equals(Terminals.FP)){
					this.next();
					if(currToken.getType().equals(Terminals.AC)){
						this.next();
						this.LCmd();
						//System.out.println("++++++++++ Achou aqui " + currToken.getType());
						if(currToken.getType().equals(Terminals.FC)){
							System.out.println("Acabou o WHILE");
							return;
						}
					}
				}
			}
		}
	}

	private void Atrib(){
		System.out.println("COMECOU UM ATRIB");
		this.next();
		System.out.println("+++++++++++ Aqui " + currToken.getType());
		if(currToken.getType().equals(Terminals.ASSIGN)){
			this.next();
			this.Expr();
			System.out.println("Encerrou o ATRIB");
		}
	}
	
	private void Expr(){
		return;
	}
	private void Ebool(){

	}
}

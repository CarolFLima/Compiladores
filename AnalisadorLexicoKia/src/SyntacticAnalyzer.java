
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

		//System.out.println("Achei um " + currToken.getType().toString() + "  " + currToken.getValue() );

	}

	private void LCmd(){
		this.Cmd();
		this.LCnre();
	}

	private void LCnre() {
		//TODO entra aqui so se nao for chaves
		//if(!currToken.getType().equals(Terminals.FC))
			//this.next();
		if(currToken.getType().equals(Terminals.PV)){
			this.next();
			while(currToken.getType().equals(Terminals.FC)) //tirar isso dps
				this.next();
			this.Cmd();
			this.LCnre();
		} /*else if(currToken.getType().equals(Terminals.FC)){
			this.next();
			this.LCmd();
		}*/
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
				if(currToken.getType().equals(Terminals.FP)){
					this.next();
					System.out.println("++++++++++ Achou aqui " + currToken.getType());
					if(currToken.getType().equals(Terminals.AC)){
						this.next();
						this.LCmd();
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
		this.next();
		if(currToken.getType().equals(Terminals.ASSIGN)){
			this.next();
			this.EArit();
		}
	}
	
	private void Expr(){
		System.out.println("Chamou a expressão " + currToken.getValue());
		this.Ebool();
		this.next(); 
	}
	
	private void Ebool(){
		this.Tbool();
		this.Ebnre();
	}
	
	private void Ebnre(){
		if(currToken.getType().equals(Terminals.OR)){
			this.Ebool();
			this.Ebnre();
		} else {
			System.out.println("Epsilon");
		}
	}
	
	private void Tbool(){
		this.Fbool();
		this.Tbnre();
	}
	
	private void Tbnre(){
		if(currToken.getType().equals(Terminals.AND)){
			this.Fbool();
			this.Tbnre();
		} else {
			System.out.println("Epsilon");
		}
	}
	
	
	private void Fbool(){
		if(currToken.getType().equals(Terminals.NOT)){
			
		} else if(currToken.getType().equals(Terminals.TRUE)){
			
		} else if(currToken.getType().equals(Terminals.FALSE)){
			
		} else if(currToken.getType().equals(Terminals.AP)){
			this.Ebool();
			if(currToken.getType().equals(Terminals.FP)){
				
			}
		} 
		this.ERel();
	}
	
	private void ERel(){
		this.EArit();
		if(currToken.getType().equals(Terminals.EQUAL) || currToken.getType().equals(Terminals.EQUALG) || currToken.getType().equals(Terminals.EQUALL)|| 
				currToken.getType().equals(Terminals.LESS) || currToken.getType().equals(Terminals.LESSE) ||
				currToken.getType().equals(Terminals.GREATER)|| currToken.getType().equals(Terminals.DIFF)){
			this.next();
			this.EArit();
		}
	}
	
	private void EArit(){
		System.out.println("EArit = TArit EArne " + currToken.getValue());
		this.TArit();
		this.EArne();
	}
	
	private void EArne(){
		//this.next();
		if(currToken.getType().equals(Terminals.PLUS) || currToken.getType().equals(Terminals.MINUS)){
			System.out.println("EArne = opa (" + currToken.getValue() + ") TArit Earne");
			this.TArit();
			this.EArne();
			
		} else {
			System.out.println("EArne = Epsilon");
		}
	}
	
	private void TArit(){
		System.out.println("TArit = FArit " + currToken.getValue());
		//this.next();
		this.FArit();
		this.TArne();
		//System.out.println("TArit = FArit TArne " + currToken.getValue());
	}

	private void TArne(){
		
		this.next();
		if(currToken.getType().equals(Terminals.TIMES) || currToken.getType().equals(Terminals.DIV) ||
				currToken.getType().equals(Terminals.MOD)){
			System.out.println("EArne = opm (" + currToken.getValue() + ") TArit Earne");
			this.next();
			this.FArit();
			this.TArne();
			
		} else {
			System.out.println("TArne = Epsilon");
		}
	}
	
	private void FArit(){
		if(currToken.getType().equals(Terminals.MINUS)){
			this.next();
			this.FArit(); //next
		} else if(currToken.getType().equals(Terminals.DATA_TYPE) || currToken.getType().equals(Terminals.IDENTIFIER)){
			System.out.println("FArit = id (" + currToken.getValue() + ")");
			//this.next();
			return;
		} else if(currToken.getType().equals(Terminals.AP)){
			this.next();
			this.EArit();
			if(currToken.getType().equals(Terminals.FP)){
				
			}
		} else {
			this.next();
		}
	}
}

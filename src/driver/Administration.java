package driver;

import scanner.ScanMe;
import scanner.Symbol;
import scanner.Token;
import symbolTable.SymbolTable;

public class Administration {
	
	ScanMe scan;
	SymbolTable symTable;
	public int lineNo;
	public Administration(ScanMe sc, SymbolTable st) {
		// TODO Auto-generated constructor stub
		scan = sc;
		symTable = st;
		lineNo = 1;
	}
	public void start()
	{
		while(true)
		{
			Token tok = scan.nextToken();
			if(tok.getSymbol() == Symbol.EOF)
				break;
			if(tok.getSymbol() == Symbol.NEWLINE)
				System.out.println();
			else 
			System.out.print(tok.getSymbol()+ " ");
		}
		
	}
	public void NewLine() {
		// TODO Auto-generated method stub
		lineNo++;
	}
	public void error(int errorType, Symbol sym, int flag)
	{
		switch(errorType)
		{
			case 1:
				System.err.println("Lexical Error: Undefined Symbol at line "+lineNo);	
				break;
			case 2:
				if(flag == 1)
				{
					System.err.println("Syntax Error: Missing '"+sym.getLexeme() +"'at line "+lineNo);
				}
				else if(flag == 2)
				{
					System.err.println("Syntax Error: Illegal token '"+sym.getLexeme()+"' at line "+lineNo);
				}
				break;
		}
	}

}

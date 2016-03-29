package driver;

import scanner.ScanMe;
import scanner.Symbol;
import scanner.Token;

public class Administration {
	
	ScanMe scan;
	int lineNo;
	public Administration(ScanMe sc) {
		// TODO Auto-generated constructor stub
		scan = sc;
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

}

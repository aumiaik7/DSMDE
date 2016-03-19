package driver;

import scanner.ScanMe;
import scanner.Symbol;
import scanner.Token;

public class Administration {
	
	ScanMe scan;
	public Administration(ScanMe sc) {
		// TODO Auto-generated constructor stub
		scan = sc;
	}
	public void start()
	{
		while(true)
		{
			Token tok = scan.nextToken();
			if(tok.getSymbol() == Symbol.EOF)
				break;
			System.out.println(tok.getSymbol());
		}
		
	}

}

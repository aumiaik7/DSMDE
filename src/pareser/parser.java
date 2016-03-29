package pareser;

import java.util.Vector;

import com.sun.xml.internal.messaging.saaj.packaging.mime.Header;

import driver.Administration;
import scanner.ScanMe;
import scanner.Symbol;
import scanner.Token;

public class parser {
	
	ScanMe scanner;
	Administration admin;
	Token lookAheadToken;
	Vector<Symbol> stopSet;
	firstFollow ff = new firstFollow();
	public parser(ScanMe sc, Administration ad) {
		// TODO Auto-generated constructor stub
		scanner = sc;
		admin = ad;
	}
	
	public void DsmdeFormat(Symbol sym)
	{
		Header(stopSet);
	}
	
	void Header(Vector<Symbol> stops)
	{
		banner(stops);
		if(in(ff.firstOfHeader()))
		{
			Header(stops);
		}
	}

	

	void banner(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		match(Symbol.MM, stops);
	}

	void match(Symbol sym, Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		if(lookAheadToken.getSymbol() == sym)
		{
			while(true)
			{
				lookAheadToken = scanner.nextToken();
				
				//newline detected
				if(lookAheadToken.getSymbol() == Symbol.NEWLINE)
				{
					admin.NewLine();
				}
				else if(lookAheadToken.getSymbol() != Symbol.UNDEFINED)
				{
					break;
				}
			}
		}
	}
	
	
	void lookAheadToken()
	{
		while(true)
		{
			lookAheadToken = scanner.nextToken();
			
			//newline detected
			if(lookAheadToken.getSymbol() == Symbol.NEWLINE)
			{
				admin.NewLine();
			}
			else if(lookAheadToken.getSymbol() != Symbol.UNDEFINED)
			{
				break;
			}
		}
	}
	
	private boolean in(Vector<Symbol> set) {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < set.size(); i++)
		{
			if(lookAheadToken.getSymbol() == set.elementAt(i))
				return true;
		}
		return false;
	}
	

}

package pareser;

import java.util.Vector;

import scanner.Symbol;

public class firstFollow {
	
	public Vector<Symbol> firstOfHeader()
	{
		Vector<Symbol> fOfBanner = firstOfBanner();
		return fOfBanner;
	}

	private Vector<Symbol> firstOfBanner() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfBanner = null ;
		fOfBanner.add(Symbol.MM);
		return fOfBanner;
	}

}

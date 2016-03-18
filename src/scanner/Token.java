package scanner;

public class Token {
	Symbol symbol;
	double value;
	
	public Token(Symbol sym, double val) {
		// TODO Auto-generated constructor stub
			symbol = sym;
			value = val;	
	}
	
	public Symbol getSymbol()
	{
		return symbol;
	}
	public double getValue()
	{
		return value;
	}

}

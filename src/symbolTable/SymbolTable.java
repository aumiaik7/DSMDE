package symbolTable;
import java.util.Vector;

import scanner.*;

public class SymbolTable {

	private static final int SYMTABLESIZE = 307;
	Vector<Token> hashTable = new Vector<Token>(SYMTABLESIZE);
	
	
	int position = 0;
	
	public static final String[] keyWords = {"%%MatrixMarket", "Matrix", "DSM", "MDM", "DMM", "Coordinate", "Array",
							"Integer", "Real", "Complex", "Pattern", "General", "Symmetric", "SkewSymmetric",
							"Hermitian", "IC", "IR", "%beginDomain", "%endDomain", "%beginModElement","%endModElement",
							"%beginAttribute", "%endAttribute"};
	
	public SymbolTable() {
		// TODO Auto-generated constructor stub
		for( int i = 0; i < SYMTABLESIZE; ++i )
			hashTable.add(new Token(Symbol.UNDEFINED,-1));
		
		int i = 0;
		for(Symbol sym : Symbol.values() )
		{
			if(i == 23)
				break;
			
			Token tok = new Token(sym, -1);
			insert(tok);
			i++;
			
		}
		//print();
	}
	private Symbol insert(Token tok) {
		String lexeme = keyWords[tok.getSymbol().getCode() - 256];
		
		int isOccupied = search(lexeme);
		
		//position is empty keyword can be inserted 
		if(isOccupied == -1)
		{	
			Token tk = new Token(tok.getSymbol(),-1);
			hashTable.set(position, tk);
			
			return tok.getSymbol();
			//occupied++;
		}
		
		//position is occupied finds the next empty position and insert
		else
		{
			while(true)
			{
							
				Token tempTk;
				tempTk = hashTable.elementAt(position); 	
							
				//263 is NONAME symbol so position is empty insert here
				if(tempTk.getSymbol() == Symbol.UNDEFINED)
				{	
					
					Token tk = new Token(tok.getSymbol(),-1);
					hashTable.set(position,tk);
					
				}
				//go to the next position			
				else
				{
								
					Symbol sym = tempTk.getSymbol();
					
					if(sym.getCode() <= 278  && lexeme == keyWords[sym.getCode() - 256] ) //encoding scheme for matching lexeme with keyword
					{					
						return sym; // for keyword
					}				
										
					//last position so reset
					else
					{
						if(position > 306)
						{
							position = 0;
						}
				
						else
							position++;				
					
					}
				}
			}
		}
	
		
		
		
		
		
	}
	
	//search function
	int search(String lex)
	{
		position = hashfn(lex);
		Token tk;
			
		tk = hashTable.elementAt(position); 
		if(tk.getSymbol() == Symbol.UNDEFINED)
			return -1;
		else
			return position;					
	}
	
	//search using token
	public Token search2(String lex)
	{
		int position = hashfn(lex);
		Token tk;
		tk = hashTable.elementAt(position);
		if(tk.getSymbol() == Symbol.UNDEFINED)
		{
			try{
				 Double val = Double.parseDouble(lex);
				 if(lex.contains("."))
					 tk = new Token(Symbol.NUMDOUBLE,val);
				 else
					 tk = new Token(Symbol.NUMINT,val);
			}
			catch (NumberFormatException e)
			{
				
			}
			
			return tk;
		}
		else
		{
			while(true)
			{
							
				Token tempTk;
				tempTk = hashTable.elementAt(position); 	
							
				if(tempTk.getSymbol() == Symbol.UNDEFINED)
				{	
					
					try{
						 Double val = Double.parseDouble(lex);
						 if(lex.contains("."))
							 tempTk = new Token(Symbol.NUMDOUBLE,val);
						 else
							 tempTk = new Token(Symbol.NUMINT,val);
					}
					catch (NumberFormatException e)
					{
						
					}
					return tempTk;
				}
				//go to the next position			
				else
				{
								
					Symbol sym = tempTk.getSymbol();
					
					if(sym.getCode() <= 278  && lex.equals(keyWords[sym.getCode() - 256] )) //encoding scheme for matching lexeme with keyword
					{					
						return tempTk; // for keyword
					}				
										
					//last position so reset
					else
					{
						if(position > 306)
						{
							position = 0;
						}
				
						else
							position++;				
					
					}
				}
			}
		}
		
	}
	
	//hash function
	int hashfn(String lex)
	{
		int asciiValue = 0;
		for(int i = 0; i < lex.length(); i++)
		{
			asciiValue += (int)(lex.charAt(i)); 	
		}	
		
		return asciiValue%307;
		
	}
	
	//print symbol table
	void print()
	{

			
		for(int i=0; i < hashTable.size(); i++)
		{
			Token tempTk;
			tempTk = hashTable.elementAt(i); 	
					
			//263 is NONAME symbol
			if(tempTk.getSymbol() != Symbol.UNDEFINED)
			{
				System.out.println("Symbol ID: "+tempTk.getSymbol() +" Lexeme: "+" Position: "+i);
			}
		}
	}

	
}

/**@file Scanner.java
 * @author Ahamad Imtiaz Khan
 * @brief This the scanner class
 * This class generates meaningful tokens from source file 
 */
package scanner;


import java.io.FileReader;
import java.util.Scanner;

import symbolTable.SymbolTable;


public class ScanMe {
	
	//FileInputStream sourceFile = null;
	Scanner scanner = null;	
	SymbolTable symTable;
	Token nextTok  = null;
	int isnewLineToken = 0;
	String[] tokensWithNL;
	/**
	 * constructor
	 * @param srcFile
	 */
	public ScanMe(FileReader srcFile,SymbolTable st) {
		// TODO Auto-generated constructor stub
		scanner = new Scanner(srcFile);
		scanner.useDelimiter("[ |\\t]+");
		//scanner.useDelimiter("\t");
		symTable = st;
	}
	/**
	 * 
	 * @return token as enum Symbol
	 */
	public Token nextToken()
	{
		String tokenAsString = "";
		
			if(nextTok != null)
			{
				nextTok = null;
				return new Token(Symbol.NEWLINE, -1);
			}
		    else if(scanner.hasNext())
			{
			
					Token tok = null;
					
					if(tokensWithNL != null)
					{
						if(isnewLineToken == tokensWithNL.length )
						{
							tokensWithNL = null;
							isnewLineToken = 0;
							nextTok = null;
							
							tokenAsString = scanner.next();	
							
							if(tokenAsString.contains("\n"))
							{
								tokensWithNL = tokenAsString.split("\\r?\\n",-1);
								tok = symTable.search2(tokensWithNL[isnewLineToken]);
								if(isnewLineToken + 1 < tokensWithNL.length)
									nextTok = new Token(Symbol.NEWLINE, -1);
								isnewLineToken++;
							}
							else
							{
								tok = symTable.search2(tokenAsString);
							}
						}
						else
						{
							if(tokensWithNL[isnewLineToken].equals(""))
							{
								tok = new Token(Symbol.NEWLINE, -1);
								//return tok;
							}
							else
							{
								tok = symTable.search2(tokensWithNL[isnewLineToken]);
								if(isnewLineToken + 1 < tokensWithNL.length)
									nextTok = new Token(Symbol.NEWLINE, -1);
							}
							
							isnewLineToken++;
						}
					}
					else
					{
						tokenAsString = scanner.next();	
						
						tokensWithNL = null;
						isnewLineToken = 0;
						nextTok = null;
						if(tokenAsString.contains("\n"))
						{
							tokensWithNL = tokenAsString.split("\\r?\\n",-1);
							tok = symTable.search2(tokensWithNL[isnewLineToken]);
							if(isnewLineToken + 1 < tokensWithNL.length)
								nextTok = new Token(Symbol.NEWLINE, -1);
							isnewLineToken++;
						}
						else
						{
							tok = symTable.search2(tokenAsString);
						}
					}
					
					return tok;
			}
			else
			{
				Token tok = null;
				nextTok = null;
				if(tokensWithNL != null)
				{
				
					    if(isnewLineToken == tokensWithNL.length )
						{
							tokensWithNL = null;
							isnewLineToken = 0;
							tok = new Token(Symbol.EOF, -1);
						}
						/*else if(tokensWithNL[isnewLineToken].equals(""))
						{
							isnewLineToken++;
							tok = new Token(Symbol.UNDEFINED, -1);
						}*/
						
						else
						{
							if(tokensWithNL[isnewLineToken].equals(""))
							{
								tok = new Token(Symbol.NEWLINE, -1);
								//return tok;
							}
							else 
							{
								tok = symTable.search2(tokensWithNL[isnewLineToken]);
								if(isnewLineToken + 1 < tokensWithNL.length)
									nextTok = new Token(Symbol.NEWLINE, -1);
							}
							
							
							isnewLineToken++;
						}
						
					
				}
				else 
					tok = new Token(Symbol.EOF, -1);
				return tok;
			}
				
				
	}
	
	public void read() 
	{
		while(scanner.hasNext())
		{
			String token = scanner.next();
			 if(token.equals("buba"))
				 System.out.print(Symbol.MM);
			 // System.out.print(scanner.next()+" ");	
			 
			 
		}
			
	}
}
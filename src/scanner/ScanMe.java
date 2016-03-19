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
	/**
	 * constructor
	 * @param srcFile
	 */
	public ScanMe(FileReader srcFile,SymbolTable st) {
		// TODO Auto-generated constructor stub
		scanner = new Scanner(srcFile);
		symTable = st;
	}
	/**
	 * 
	 * @return token as enum Symbol
	 */
	public Token nextToken()
	{
		String tokenAsString = "";
			if(scanner.hasNext())
			{
				
				if(scanner.hasNextInt() || scanner.hasNextFloat())
				{
					double value = Double.parseDouble(scanner.next());
					Token tok = new Token(Symbol.NUM, value);
					return tok;
				}
				else
				{
					tokenAsString = scanner.next();	
					
					Token tok = symTable.search2(tokenAsString);
					
					return tok;
					/*
					if(tokenAsString.equals("%%MatrixMarket"))
					{
						Token tok = new Token(Symbol.MM, -1);
						return tok;
					}
					else if(tokenAsString.equals("Matrix"))
					{
						Token tok = new Token(Symbol.MATRIX, -1);
						return tok;
					}
					else if(tokenAsString.equals("DSM"))
					{
						Token tok = new Token(Symbol.DSM, -1);
						return tok;
					}
					else if(tokenAsString.equals("MDM"))
					{
						Token tok = new Token(Symbol.MDM, -1);
						return tok;
					}
					else if(tokenAsString.equals("DMM"))
					{
						Token tok = new Token(Symbol.DMM, -1);
						return tok;
					}
					else if(tokenAsString.equals("Coordinate"))
					{
						Token tok = new Token(Symbol.COORD, -1);
						return tok;
					}
					else if(tokenAsString.equals("Array"))
					{
						Token tok = new Token(Symbol.ARRAY, -1);
						return tok;
					}
					else if(tokenAsString.equals("Integer"))
					{
						Token tok = new Token(Symbol.INT, -1);
						return tok;
					}
					else if(tokenAsString.equals("Real"))
					{
						Token tok = new Token(Symbol.REAL, -1);
						return tok;
					}
					else if(tokenAsString.equals("Complex"))
					{
						Token tok = new Token(Symbol.COMPLEX, -1);
						return tok;
					}
					else if(tokenAsString.equals("Pattern"))
					{
						Token tok = new Token(Symbol.PATTERN, -1);
						return tok;
					}
					else if(tokenAsString.equals("General"))
					{
						Token tok = new Token(Symbol.GENERAL, -1);
						return tok;
					}
					else if(tokenAsString.equals("Symmetric"))
					{
						Token tok = new Token(Symbol.SYMETRIC, -1);
						return tok;
					}
					else if(tokenAsString.equals("SkewSymmetric"))
					{
						Token tok = new Token(Symbol.SKSYMETRIC, -1);
						return tok;
					}
					else if(tokenAsString.equals("Hermitian"))
					{
						Token tok = new Token(Symbol.HERMITIAN, -1);
						return tok;
					}
					else if(tokenAsString.equals("IC"))
					{
						Token tok = new Token(Symbol.IC, -1);
						return tok;
					}
					else if(tokenAsString.equals("IR"))
					{
						Token tok = new Token(Symbol.IR, -1);
						return tok;
					}
					else if(tokenAsString.equals("%beginDomain"))
					{
						Token tok = new Token(Symbol.BDOMAIN, -1);
						return tok;
					}
					else if(tokenAsString.equals("%endDomain"))
					{
						Token tok = new Token(Symbol.EDOMAIN, -1);
						return tok;
					}	
					else if(tokenAsString.equals("%beginModElement"))
					{
						Token tok = new Token(Symbol.BMODE, -1);
						return tok;
					}
					else if(tokenAsString.equals("%beginAttribute"))
					{
						Token tok = new Token(Symbol.BATTRIB, -1);
						return tok;
					}
					else if(tokenAsString.equals("%endAttribute"))
					{
						Token tok = new Token(Symbol.DSM, -1);
						return tok;
					}
					else
					{
						Token tok = new Token(Symbol.UNDEFINED, -1);
						return tok;
					}*/
				}
					
				
			}
			else
			{
				Token tok = new Token(Symbol.EOF, -1);
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
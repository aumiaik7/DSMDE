package parser;

import java.util.Vector;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Documentation;

import driver.Administration;
import scanner.ScanMe;
import scanner.Symbol;
import scanner.Token;

public class Parser {
	
	ScanMe scanner;
	Administration admin;
	Token lookAheadToken;
	Vector<Symbol> stopSet;
	FirstFollow ff = new FirstFollow();
	public Parser(ScanMe sc, Administration ad) {
		// TODO Auto-generated constructor stub
		scanner = sc;
		admin = ad;
		lookAheadToken();
	}
	
	public void DsmdeFormat(Symbol sym)
	{
		Header(stopSet);
		if(in(ff.firstOfHeader()))
		{
			DsmdeFormat(sym);
		}
		Comments(stopSet);
		Data(stopSet);
		
	}
	
	

	

	void Header(Vector<Symbol> stops)
	{
		banner(stops);
		
		if(in(ff.fisrtOfObjectType()))
		{
			objectType(stops);
		}
		
		if(in(ff.fisrtOfQualifiers()))
		{
			Qualifiers(stops);
		}
		
	}
	
	void banner(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		match(Symbol.MM, stops);
	}
	   
	void objectType(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		if(lookAheadToken.getSymbol() == Symbol.MATRIX)
			match(Symbol.MATRIX, stops);
		else if(lookAheadToken.getSymbol() == Symbol.DSM)
			match(Symbol.DSM, stops);
		else if(lookAheadToken.getSymbol() == Symbol.MDM)
			match(Symbol.MDM, stops);
		else if(lookAheadToken.getSymbol() == Symbol.DMM)
			match(Symbol.DMM, stops);
	}
	
	void Qualifiers(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		if(in(ff.firstOfNDomain()))
		{
			NDomain(stops);
		}
		QualList(stops);
			
	}

	void QualList(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		formatType(stops);
		if(in(ff.firstOfStructure()))
		{
			structure(stops);
		}
		if(in(ff.firstOfNiattribute()))
		{
			NIAttribute(stops);
		}
		if(in(ff.firstOfNumericType()))
		{
			numericType(stops);
		}
		if(in(ff.firstOfOrientn()))
		{
			orientn(stops);	
		}
		
		match(Symbol.NEWLINE, stops);
		
		if(in(ff.firstOfQualList()))
		{
			QualList(stops);
		}
		
	}

	void formatType(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		if(lookAheadToken.getSymbol() == Symbol.COORD)
			match(Symbol.COORD, stops);
		else if(lookAheadToken.getSymbol() == Symbol.ARRAY)
			match(Symbol.ARRAY, stops);
		else
		{
			//error
		}
	}
	
	void numericType(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		if(lookAheadToken.getSymbol() == Symbol.INT)
			match(Symbol.INT, stops);
		else if(lookAheadToken.getSymbol() == Symbol.REAL)
			match(Symbol.REAL, stops);
		else if(lookAheadToken.getSymbol() == Symbol.COMPLEX)
			match(Symbol.COMPLEX, stops);
		else 
			match(Symbol.PATTERN, stops);
		
		if(in(ff.firstOfNumericType()))
			numericType(stops);
	}
	
	void structure(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		if(lookAheadToken.getSymbol() == Symbol.GENERAL)
			match(Symbol.GENERAL, stops);
		else if(lookAheadToken.getSymbol() == Symbol.SYMETRIC)
			match(Symbol.SYMETRIC, stops);
		else if(lookAheadToken.getSymbol() == Symbol.SKSYMETRIC)
			match(Symbol.SKSYMETRIC, stops);
		else 
			match(Symbol.HERMITIAN, stops);
	}

	void NDomain(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		Integer(stops);
	}
	
	void NIAttribute(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		Integer(stops);
	}
	
	void orientn(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		if(lookAheadToken.getSymbol() == Symbol.IC)
			match(Symbol.IC, stops);
		else
			match(Symbol.IR, stops);
	}
	
	void Comments(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		if(in(ff.firstOfTextLine()))
		{
			TextLine(stops);
			//Comments(stops);
		}
		
		Documentation(stops);
		
		if(in(ff.firstOfTextLine()))
		{
			TextLine(stops);
			//Comments(stops);
		}
	}
	
	void TextLine(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		String line = scanner.nextTextLine();
		verify(line);
		lookAheadToken();
		match(Symbol.NEWLINE,stops);
		if(in(ff.firstOfTextLine()))
		{
			TextLine(stops);
		}
	}

	void Documentation(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		if(in(ff.firstOfDomainNames()))
		{
			DomainNames(stops);
		}
		else if(in(ff.firstOfModElementNames()))
		{
			ModElementNames(stops);
		}
		else if(in(ff.firstOfInteractAttributeNames()))
		{
			InteractAttributeNames(stops);
		}
		else
		{
			//error
		}
		
		if(in(ff.firstOfDocumentation()))
		{
			Documentation(stops);
		}
		
	}
	


	void DomainNames(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		beginD(stops);
		match(Symbol.NEWLINE,stops);
		TextLine(stops);
		endD(stops);
		match(Symbol.NEWLINE,stops);
	}

	
	
	private void ModElementNames(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		beginME(stops);
		match(Symbol.NEWLINE,stops);
		TextLine(stops);
		endME(stops);
		match(Symbol.NEWLINE,stops);
		
	}


	private void InteractAttributeNames(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		
		beginIA(stops);
		match(Symbol.NEWLINE,stops);
		TextLine(stops);
		endIA(stops);
		match(Symbol.NEWLINE,stops);
		
	}
	
	void beginD(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		match(Symbol.BDOMAIN,stops);
	}

	void endD(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		match(Symbol.EDOMAIN,stops);
	}
	
	void beginME(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		match(Symbol.BMODE,stops);
	}
	
	void endME(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		match(Symbol.EMODE,stops);
	}

	void beginIA(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		match(Symbol.BATTRIB,stops);
	}

	void endIA(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		match(Symbol.EATTRIB,stops);
	}
	
	void Data(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		NRows(stops);
		NCols(stops);
		if(lookAheadToken.getSymbol() == Symbol.NEWLINE)
		{
			ArrayData(stops);
		}
		else
		{
			CoordData(stops);
		}
	}
	
	
	void CoordData(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		Nnz(stops);
		match(Symbol.NEWLINE,stops);
		CoordDataLine(stops);
		
	}

	
	void ArrayData(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		ArrayDataLine(stops);
	}
	
	void CoordDataLine(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		
		RowIndex(stops);
		ColIndex(stops);
		Values(stops);
		match(Symbol.NEWLINE,stops);
		
		if(lookAheadToken.getSymbol() == Symbol.NUMINT)
			CoordData(stops);
		
	}
	
	
	void NRows(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		Integer(stops);
	}

	void NCols(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		Integer(stops);
		
	}
	
	void Nnz(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		Integer(stops);
	}
	
	private void RowIndex(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		Integer(stops);
		
	}

	private void ColIndex(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		Integer(stops);
		
	}
	

	void ArrayDataLine(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		Values(stops);
		match(Symbol.NEWLINE,stops);
		
		if(in(ff.firstOfValues()))
			ArrayDataLine(stops);
	}
	
	void Values(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		if(in(ff.firstOfIAttribute()))
		{
			IAttribute(stops);
		}
	}

	void IAttribute(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		if(in(ff.firstOfInteger()))
		{
			Integer(stops);
		}
		else if(in(ff.firstOfReal()))
		{
			Real(stops);
		}
		
		if(in(ff.firstOfIAttribute()))
		{
			IAttribute(stops);
		}
	}


	void Integer(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		match(Symbol.NUMINT,stops);
		
	}
	
	void Real(Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		match(Symbol.NUMDOUBLE,stops);
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
					break;
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
				break;
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
	
	void verify(String line) {
		// TODO Auto-generated method stub
		String parts[] = line.split(" ");
		if(parts[0].equals("%"))
		{
			//okay
		}
		else
		{
			//error
		}
		
	}

}

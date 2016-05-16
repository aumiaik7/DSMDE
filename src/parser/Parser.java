package parser;

import java.util.Vector;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Documentation;

import data.DataHolder;
import data.HeaderFields.FormatType;
import data.HeaderFields.ObjectType;
import data.HeaderFields.Orientn;
import data.HeaderFields.Structure;
import driver.Administration;
import scanner.ScanMe;
import scanner.Symbol;
import scanner.Token;

public class Parser {
	
	static int ScanE = 1, ParseE = 2,textLine = 0;
	boolean undefinedGoer = true;
	ScanMe scanner;
	Administration admin;
	Token lookAheadToken;
	//Vector<Symbol> stopSet = new Vector<Symbol>();
	FirstFollow ff = new FirstFollow();
	DataHolder dataHold;
	public Parser(ScanMe sc, Administration ad, DataHolder data) {
		// TODO Auto-generated constructor stub
		scanner = sc;
		admin = ad;
		dataHold = data;
		lookAheadToken();
	}
	
	public void DsmdeFormat(Symbol sym)
	{
		//System.out.print(" ");//System.out.print("DsmdeFormat");
		
		Vector<Symbol> stopSet = new Vector<Symbol>();
		stopSet.addAll(ff.firstOfComments());
		stopSet.addAll(ff.firstOfData());
		stopSet.add(sym);
		
		Header(stopSet);
		
		if(in(ff.firstOfHeader()))
		{
			DsmdeFormat(sym);
		}
		
		stopSet.clear();
		stopSet.addAll(ff.firstOfData());
		stopSet.add(sym);
		
		Comments(stopSet);
		
		stopSet.clear();
		stopSet.add(sym);
		
		Data(stopSet);
		System.out.print("Parsing Done");//System.out.print(admin.lineNo);
		
	}
	
	

	

	void Header(Vector<Symbol> stops)
	{
		//System.out.print(" ");//System.out.print("Header");
		//stopSet.clear();
		Vector<Symbol> stopSet = new Vector<Symbol>();
		stopSet.addAll(stops);
		stopSet.addAll(ff.firstOfObjectType());
		stopSet.addAll(ff.firstOfQualifiers());
		
		banner(stopSet);
		
		if(in(ff.firstOfObjectType()))
		{
			stopSet.clear();
			stopSet.addAll(stops);
			stopSet.addAll(ff.firstOfHeader());
			stopSet.addAll(ff.firstOfQualifiers());
			objectType(stopSet);
		}
		
		if(in(ff.firstOfQualifiers()))
		{
			stopSet.clear();
			stopSet.addAll(stops);
			stopSet.addAll(ff.firstOfHeader());
			Qualifiers(stopSet);
		}
		
		if(in(ff.firstOfHeader()))
			Header(stops);
		
	}
	
	void banner(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("banner");
		match(Symbol.MM, stops);
	}
	   
	void objectType(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("objectType");
		if(lookAheadToken.getSymbol() == Symbol.MATRIX)
		{
			match(Symbol.MATRIX, stops);
			dataHold.setObjectType(ObjectType.MATRIX);
		}
		else if(lookAheadToken.getSymbol() == Symbol.DSM)
		{
			match(Symbol.DSM, stops);
			dataHold.setObjectType(ObjectType.DSM);
		}
		else if(lookAheadToken.getSymbol() == Symbol.MDM)
		{
			match(Symbol.MDM, stops);
			dataHold.setObjectType(ObjectType.MDM);
		}
		else if(lookAheadToken.getSymbol() == Symbol.DMM)
		{
			match(Symbol.DMM, stops);
			dataHold.setObjectType(ObjectType.DMM);
		}
		
	}
	
	void Qualifiers(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("Qualifiers");
		if(in(ff.firstOfNDomain()))
		{
			Vector<Symbol> stopSet = new Vector<Symbol>();
			//stopSet.clear();
			stopSet.addAll(stops);
			stopSet.addAll(ff.firstOfQualList());
			NDomain(stopSet);
		}
		QualList(stops);
			
	}

	void QualList(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("QualList");
		Vector<Symbol> stopSet = new Vector<Symbol>();
		//stopSet.clear();
		stopSet.addAll(stops);
		stopSet.addAll(ff.firstOfStructure());
		stopSet.addAll(ff.firstOfNiattribute());
		stopSet.addAll(ff.firstOfNumericType());
		stopSet.addAll(ff.firstOfOrientn());
		stopSet.add(Symbol.NEWLINE);
		
		formatType(stopSet);
		
		if(in(ff.firstOfStructure()))
		{
			stopSet.clear();
			stopSet.addAll(stops);
			stopSet.addAll(ff.firstOfNiattribute());
			stopSet.addAll(ff.firstOfNumericType());
			stopSet.addAll(ff.firstOfOrientn());
			stopSet.add(Symbol.NEWLINE);
			structure(stopSet);
		}
		if(in(ff.firstOfNiattribute()))
		{
			stopSet.clear();
			stopSet.addAll(stops);
			stopSet.addAll(ff.firstOfNumericType());
			stopSet.addAll(ff.firstOfOrientn());
			stopSet.add(Symbol.NEWLINE);
			NIAttribute(stopSet);
		}
		if(in(ff.firstOfNumericType()))
		{
			stopSet.clear();
			stopSet.addAll(stops);
			stopSet.addAll(ff.firstOfNumericType());
			stopSet.addAll(ff.firstOfOrientn());
			stopSet.add(Symbol.NEWLINE);
			numericType(stopSet);
		}
		if(in(ff.firstOfOrientn()))
		{
			stopSet.clear();
			stopSet.addAll(stops);
			stopSet.add(Symbol.NEWLINE);
			orientn(stopSet);	
		}
		
		match(Symbol.NEWLINE, stops);
		//admin.NewLine();
		
		if(in(ff.firstOfQualList()))
		{
			QualList(stops);
		}
		
	}

	void formatType(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("formatType");
		if(lookAheadToken.getSymbol() == Symbol.COORD)
		{
			match(Symbol.COORD, stops);
			dataHold.setFormatType(FormatType.COORDINATE);
		}
		else if(lookAheadToken.getSymbol() == Symbol.ARRAY)
		{
			match(Symbol.ARRAY, stops);
			dataHold.setFormatType(FormatType.ARRAY);
		}
		else
		{
			//error
		}
	}
	
	void numericType(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("numericType");
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
		//System.out.print(" ");//System.out.print("structure");
		if(lookAheadToken.getSymbol() == Symbol.GENERAL)
		{
			match(Symbol.GENERAL, stops);
			dataHold.setStructure(Structure.GENERAL);
		}
		else if(lookAheadToken.getSymbol() == Symbol.SYMMETRIC)
		{
			match(Symbol.SYMMETRIC, stops);
			dataHold.setStructure(Structure.SYMMETRIC);
		}
		else if(lookAheadToken.getSymbol() == Symbol.SKSYMMETRIC)
		{
			match(Symbol.SKSYMMETRIC, stops);
			dataHold.setStructure(Structure.SKSYMMETRIC);
		}
		else 
		{
			match(Symbol.HERMITIAN, stops);
			dataHold.setStructure(Structure.HERMITIAN);
		}
	}

	void NDomain(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("NDomain");
		Integer(stops);
	}
	
	void NIAttribute(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("NIAttribute");
		int niAttr = Integer(stops);
		dataHold.setNiAttribute(niAttr);
	}
	
	void orientn(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("orientn");
		if(lookAheadToken.getSymbol() == Symbol.IC)
		{
			match(Symbol.IC, stops);
			dataHold.setOrientn(Orientn.IC);
		}
		else
		{
			match(Symbol.IR, stops);
			dataHold.setOrientn(Orientn.IR);
		}
	}
	
	void Comments(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("Comments");
		if(in(ff.firstOfTextLine()))
		{
			Vector<Symbol> stopSet = new Vector<Symbol>();
			//stopSet.clear();
			stopSet.addAll(stops);
			stopSet.addAll(ff.firstOfDocumentation());
			stopSet.addAll(ff.firstOfTextLine());
			TextLine(stopSet);
			//Comments(stops);
		}
		Vector<Symbol> stopSet = new Vector<Symbol>();
		//stopSet.clear();
		stopSet.addAll(stops);
		stopSet.addAll(ff.firstOfTextLine());
		Documentation(stopSet);
		
		if(in(ff.firstOfTextLine()))
		{
			TextLine(stops);
			//Comments(stops);
		}
	}
	
	void TextLine(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("TextLine");
		//String line = scanner.nextTextLine();
		//verify(line);
		//lookAheadToken();
		textLine = 1;
		match(Symbol.COMMENT,stops);
		while(lookAheadToken.getSymbol()!= Symbol.NEWLINE)
			lookAheadToken = scanner.nextToken();
		match(Symbol.NEWLINE,stops);
		//admin.NewLine();
		if(in(ff.firstOfTextLine()))
		{
			TextLine(stops);
		}
		textLine = 0;
	}

	void Documentation(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("Documentation");
		
		if(in(ff.firstOfDomainNames()))
		{
			Vector<Symbol> stopSet = new Vector<Symbol>();
			//stopSet.clear();
			stopSet.addAll(stops);
			stopSet.addAll(ff.firstOfModElementNames());
			stopSet.addAll(ff.firstOfInteractAttributeNames());
			stopSet.addAll(ff.firstOfTextLine());
			stopSet.addAll(ff.firstOfDocumentation());
			
			DomainNames(stopSet);
			if(in(ff.firstOfTextLine()))
			{
								
				TextLine(stopSet);
				//Comments(stops);
			}
		}
		
		if(in(ff.firstOfModElementNames()))
		{
			Vector<Symbol> stopSet = new Vector<Symbol>();
			//stopSet.clear();
			stopSet.addAll(stops);
			stopSet.addAll(ff.firstOfInteractAttributeNames());
			stopSet.addAll(ff.firstOfTextLine());
			stopSet.addAll(ff.firstOfDocumentation());
			
			ModElementNames(stopSet);
			
			if(in(ff.firstOfTextLine()))
			{
				TextLine(stopSet);
				//Comments(stops);
			}
		}
		
		if(in(ff.firstOfInteractAttributeNames()))
		{
			Vector<Symbol> stopSet = new Vector<Symbol>();
			//stopSet.clear();
			stopSet.addAll(stops);
			stopSet.addAll(ff.firstOfTextLine());
			stopSet.addAll(ff.firstOfDocumentation());
			
			InteractAttributeNames(stopSet);
			
			if(in(ff.firstOfTextLine()))
			{
				TextLine(stopSet);
				//Comments(stops);
			}
		}
		
		
		
		if(in(ff.firstOfDocumentation()))
		{
			Documentation(stops);
		}
		
		Vector<Symbol> stopSet = new Vector<Symbol>();
		//stopSet.clear();
		stopSet.addAll(stops);
		//stopSet.addAll(ff.firstOfTextLine());
		stopSet.addAll(ff.followOfDocumentation());
		
		syntaxCheck(stops);
		
	}
	


	void DomainNames(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("DomainNames");
		Vector<Symbol> stopSet = new Vector<Symbol>();
		//stopSet.clear();
		stopSet.addAll(stops);
		stopSet.add(Symbol.NEWLINE);
		stopSet.addAll(ff.firstOfTextLine());
		stopSet.add(Symbol.EDOMAIN);
		
		beginD(stopSet);
		match(Symbol.NEWLINE,stops);
		//admin.NewLine();
		
				
		TextLine(stopSet);
		
		stopSet.clear();
		stopSet.addAll(stops);
		stopSet.add(Symbol.NEWLINE);
		
		endD(stopSet);
		
		match(Symbol.NEWLINE,stops);
		//admin.NewLine();
	}

	
	
	private void ModElementNames(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("ModElementNames");
		
		Vector<Symbol> stopSet = new Vector<Symbol>();
		//stopSet.clear();
		stopSet.addAll(stops);
		stopSet.add(Symbol.NEWLINE);
		stopSet.addAll(ff.firstOfTextLine());
		stopSet.add(Symbol.EMODE);
		
		beginME(stopSet);
		
		match(Symbol.NEWLINE,stops);
		//admin.NewLine();
		
		TextLine(stopSet);
		
		stopSet.clear();
		stopSet.addAll(stops);
		stopSet.add(Symbol.NEWLINE);
		
		endME(stopSet);
		
		match(Symbol.NEWLINE,stops);
		//admin.NewLine();
		
	}


	private void InteractAttributeNames(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("InteractAttributeNames");
		Vector<Symbol> stopSet = new Vector<Symbol>();
		//stopSet.clear();
		stopSet.addAll(stops);
		stopSet.add(Symbol.NEWLINE);
		stopSet.addAll(ff.firstOfTextLine());
		stopSet.add(Symbol.EATTRIB);
		
		beginIA(stopSet);
		
		match(Symbol.NEWLINE,stopSet);
		//admin.NewLine();
		
		TextLine(stopSet);
		
		stopSet.clear();
		stopSet.addAll(stops);
		stopSet.add(Symbol.NEWLINE);

		endIA(stopSet);
		
		match(Symbol.NEWLINE,stops);
		//admin.NewLine();
		
	}
	
	void beginD(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("beginD");
		match(Symbol.BDOMAIN,stops);
	}

	void endD(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("endD");
		match(Symbol.EDOMAIN,stops);
	}
	
	void beginME(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("beginME");
		match(Symbol.BMODE,stops);
	}
	
	void endME(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("endME");
		match(Symbol.EMODE,stops);
	}

	void beginIA(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("beginIA");
		match(Symbol.BATTRIB,stops);
	}

	void endIA(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("endIA");
		match(Symbol.EATTRIB,stops);
	}
	
	void Data(Vector<Symbol> stops) {
		undefinedGoer = false;
		//System.out.print(" ");//System.out.print("Data");
		Vector<Symbol> stopSet = new Vector<Symbol>();
		//stopSet.clear();
		stopSet.addAll(stops);
		stopSet.add(Symbol.NUMINT);
		stopSet.add(Symbol.NEWLINE);
		stopSet.addAll(ff.firstOfArrayDataLine());
		stopSet.addAll(ff.firstOfCoorDataLine());
		NRows(stopSet);
		NCols(stopSet);
		if(lookAheadToken.getSymbol() == Symbol.NEWLINE)
		{
			//Vector<Symbol> stopSet = new Vector<Symbol>();
			stopSet.clear();
			stopSet.addAll(stops);
			stopSet.add(Symbol.NEWLINE);
			stopSet.addAll(ff.firstOfArrayDataLine());
			stopSet.addAll(ff.followOfValues());
			
			ArrayData(stopSet);
		}
		else
		{
			//Vector<Symbol> stopSet = new Vector<Symbol>();
			stopSet.clear();
			stopSet.addAll(stops);
			stopSet.add(Symbol.NEWLINE);
			stopSet.addAll(ff.firstOfCoorDataLine());
			
			CoordData(stopSet);
		}
	}
	
	
	void CoordData(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("CoordData");
		Nnz(stops);
		match(Symbol.NEWLINE,stops);
		//admin.NewLine();
		CoordDataLine(stops);
		
	}

	
	void ArrayData(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("ArrayData");
		ArrayDataLine(stops);
	}
	
	void CoordDataLine(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("CoordDataLine");
		//initialize the matrix
		if(!dataHold.isInitialized())
			dataHold.initMatrix();
		
		Vector<Symbol> stopSet = new Vector<Symbol>();
		//stopSet.clear();
		stopSet.addAll(stops);
		stopSet.add(Symbol.NUMINT);
		stopSet.addAll(ff.firstOfValues());
		stopSet.addAll(ff.followOfValues());
		int rowIndex = RowIndex(stopSet);
		
		stopSet.clear();
		stopSet.addAll(stops);
		stopSet.addAll(ff.firstOfValues());
		stopSet.addAll(ff.followOfValues());
		
		int colIndex = ColIndex(stopSet);
		
		stopSet.clear();
		stopSet.addAll(stops);
		stopSet.addAll(ff.followOfValues());
		
		Vector<Double> values = Values(stopSet);
		
		match(Symbol.NEWLINE,stops);
		
		if(rowIndex != -1 && colIndex !=0)
		{
			dataHold.setItem(rowIndex,colIndex,values);	
		}
		
		//admin.NewLine();
		
		if(lookAheadToken.getSymbol() == Symbol.NUMINT)
			CoordDataLine(stops);
		else
			dataHold.setItem(rowIndex+1,colIndex,values);
	}
	
	
	void NRows(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("NRows");
		int rows = Integer(stops);
		dataHold.setRows(rows);
	}

	void NCols(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("NCols");
		int cols = Integer(stops);
		dataHold.setCols(cols);
	}
	
	void Nnz(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("Nnz");
		int nonZero = Integer(stops);
		dataHold.setNzN(nonZero);
	}
	
	int RowIndex(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("RowIndex");
		return Integer(stops);
		
	}

	int ColIndex(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("ColIndex");
		return Integer(stops);
		
	}
	

	void ArrayDataLine(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("ArrayDataLine");
		Vector<Symbol> stopSet = new Vector<Symbol>();
		//stopSet.clear();
		stopSet.addAll(stops);
		stopSet.addAll(ff.followOfValues());
		//stopSet.add(Symbol.NUMINT);
		stopSet.add(Symbol.NUMDOUBLE);

		Values(stopSet);
		
		match(Symbol.NEWLINE,stopSet);
		//admin.NewLine();
		
		if(in(ff.firstOfValues()))
			ArrayDataLine(stops);
	}
	
	Vector<Double> Values(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("Values");
		Vector<Double> values = new Vector<Double>();
		if(in(ff.firstOfIAttribute()))
		{
			Vector<Double> val = new Vector<Double>();
			values = IAttribute(stops,val);
		}
		else if(in(ff.followOfValues()))
		{
			//do nothing
		}
		Vector<Symbol> stopSet = new Vector<Symbol>();
		//stopSet.clear();
		stopSet.addAll(stops);
		stopSet.addAll(ff.followOfValues());
		
		syntaxCheck(stops);
		return values;
	}

	Vector<Double> IAttribute(Vector<Symbol> stops, Vector<Double> values) {
		//System.out.print(" ");//System.out.print("IAttribute");
		if(in(ff.firstOfInteger()))
		{
			int val =  Integer(stops);
			if (val != -1)
				values.add((double)val);
		}
		else if(in(ff.firstOfReal()))
		{
			double val = Real(stops);
			if (val != -1.0)
				values.add(val);
		}
		
		if(in(ff.firstOfIAttribute()))
		{
			IAttribute(stops,values);
		}
		
	return values;
	}


	int Integer(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("Integer");
		Token tempTok = lookAheadToken;
		if(match(Symbol.NUMINT,stops))
			return (int)tempTok.getValue();
		else 
			return -1; 
		
	}
	
	double Real(Vector<Symbol> stops) {
		//System.out.print(" ");//System.out.print("Real");
		Token tempTok = lookAheadToken;
		if(match(Symbol.NUMDOUBLE,stops))
			return (double)tempTok.getValue();
		else 
			return -1.0;
	}

	
	//syntax error
	//Syntex Error recovery
	// when any error occured we try find the next possible valid
	// symbol (stop symbol) that can occur after the error. 

	void syntaxError(Vector<Symbol> stops)
	{
		//continue to grab token until any stop symbol is found
		while(!in(stops))
		{
			lookAheadToken = scanner.nextToken();	
			//newline detected
			if(lookAheadToken.getSymbol() == Symbol.NEWLINE)
			{
				break;
			}
			//lexical errors
			else if(lookAheadToken.getSymbol() == Symbol.UNDEFINED)
			{
				//admin.error(ScanE,lookAheadTok.getSymbol(),0);
			}
		}
	}
	//after matching any terminal symbol we check whether our look ahead token is valid or not
	void syntaxCheck(Vector<Symbol> stops)
	{
		//grab a look ahead token
		while(true && textLine == 0)
		{	
			
			//newline detected
			if(lookAheadToken.getSymbol() == Symbol.NEWLINE)
			{
				break;
			}
			//lexical errors
			else if(lookAheadToken.getSymbol() == Symbol.UNDEFINED)
			{
				//admin.error(ScanE,lookAheadTok.getSymbol(),0);
			}
			else if(lookAheadToken.getSymbol() != Symbol.UNDEFINED)
			{
				break;
			}
			else if(lookAheadToken.getSymbol() == Symbol.UNDEFINED && !undefinedGoer)
			{
				admin.error(ScanE,Symbol.UNDEFINED,1);
			}
				
			lookAheadToken = scanner.nextToken();
		}
		//look ahead symbol is not in stop set so sytax error occuered
		if(!in(stops) && textLine == 0)
		{
			//report syntax error
			admin.error(ParseE,lookAheadToken.getSymbol(),2);
			
			//recover from syntax error
			syntaxError(stops);
		}
	}
	
	
	boolean match(Symbol sym, Vector<Symbol> stops) {
		// TODO Auto-generated method stub
		boolean isMatched = false;
		if(lookAheadToken.getSymbol() == sym)
		{
			isMatched = true;
			if(sym == Symbol.NEWLINE)
			{
				admin.NewLine();
				//System.out.println();
			}
			while(true)
			{
				lookAheadToken = scanner.nextToken();
				
				//newline detected
				if(lookAheadToken.getSymbol() == Symbol.NEWLINE)
				{
					//admin.NewLine();
					break;
				}
				else if(lookAheadToken.getSymbol() != Symbol.UNDEFINED)
				{
					//report lexical error
					
					break;
				}
				else if(lookAheadToken.getSymbol() == Symbol.UNDEFINED && !undefinedGoer)
				{
					admin.error(ScanE,sym,1);
				}
			}
		}
		else
		{
			//report syntax error 
			admin.error(ParseE,sym,1);
			//recover from syntax error
			syntaxError(stops);
		}
		//check whether the look ahead symbol is valid or not
		//if not crave for valid lookahead symbol
		syntaxCheck(stops);
		return isMatched;
	}
	
	
	void lookAheadToken()
	{
		while(true)
		{
			lookAheadToken = scanner.nextToken();
			
			//newline detected
			if(lookAheadToken.getSymbol() == Symbol.NEWLINE)
			{
				//System.out.println();
				break;
			}
			else if(lookAheadToken.getSymbol() != Symbol.UNDEFINED)
			{
				//report lexical error
				//admin.error(ScanE,Symbol.UNDEFINED,1);
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

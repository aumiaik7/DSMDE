package parser;

import java.util.Collection;
import java.util.Vector;

import scanner.Symbol;

public class FirstFollow {
	
	//Vector<Symbol> firstOfHeader
	//Vector<Symbol> firstOfBanner
	//Vector<Symbol> fisrtOfObjectType
	//Vector<Symbol> fisrtOfQualifiers
	//Vector<Symbol> firstOfQualList
	//Vector<Symbol> firstOFFormatType
	//Vector<Symbol> firstOfNDomain
	//Vector<Symbol> firstOfInteger
	//Vector<Symbol> firstOfStructure
	//Vector<Symbol> firstOfNiattribute
	//Vector<Symbol> firstOfNumericType
	//Vector<Symbol> firstOfOrientn
	//Vector<Symbol> firstOfTextLine
	//Vector<Symbol> firstOfDomainNames
	//Vector<Symbol> firstOfModElementNames
	//Vector<Symbol> firstOfInteractAttributeNames
	//Vector<Symbol> firstOfDocumentation
	//Vector<Symbol> firstOfIAttribute
	//Vector<Symbol> firstOfReal
	//Vector<Symbol> firstOfValues
	
	//first of header
	Vector<Symbol> firstOfHeader()
	{
		Vector<Symbol> fOfBanner = firstOfBanner();
		return fOfBanner;
	}

	//first of banner
	Vector<Symbol> firstOfBanner() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfBanner = new Vector<Symbol>() ;
		fOfBanner.add(Symbol.MM);
		return fOfBanner;
	}
	
	
	
	
	//first of Comments
	Vector<Symbol> firstOfComments() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOftl = firstOfTextLine() ;
		Vector<Symbol> fOfDoc = firstOfDocumentation() ;
		
		Vector<Symbol> merge = new Vector<Symbol>();
		merge.addAll(fOftl);
		merge.addAll(fOfDoc);
		return merge;
	}
	
	//first of Data
	Vector<Symbol> firstOfData() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfInt = new Vector<Symbol>();
		
		//fOfInt.add(Symbol.PLUS);
		//fOfInt.add(Symbol.MINUS);
		fOfInt.add(Symbol.NUMINT);
		return fOfInt;	
	}

	//first of objectType
	Vector<Symbol> firstOfObjectType() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfObjType = new Vector<Symbol>() ;
		fOfObjType.add(Symbol.MATRIX);
		fOfObjType.add(Symbol.DSM);
		fOfObjType.add(Symbol.MDM);
		fOfObjType.add(Symbol.DMM);
		return fOfObjType;
	}
	
	

	Vector<Symbol> firstOfQualifiers() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfNdom = firstOfNDomain() ;
		Vector<Symbol> fOfQList = firstOfQualList() ;
		
		Vector<Symbol> merge = new Vector<Symbol>();
		merge.addAll(fOfNdom);
		merge.addAll(fOfQList);
		return merge;
	}

	Vector<Symbol> firstOfQualList() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfFormatType = firstOFFormatType() ;
		return fOfFormatType;
	}

	Vector<Symbol> firstOFFormatType() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfFormatType = new Vector<Symbol>();
		fOfFormatType.add(Symbol.COORD);
		fOfFormatType.add(Symbol.ARRAY);
		return fOfFormatType;
	}

	Vector<Symbol> firstOfNDomain() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfInt = firstOfInteger();
		
		return fOfInt;
	}

	Vector<Symbol> firstOfInteger() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfInt = new Vector<Symbol>();
		
		//fOfInt.add(Symbol.PLUS);
		//fOfInt.add(Symbol.MINUS);
		fOfInt.add(Symbol.NUMINT);
		return fOfInt;
	}

	Vector<Symbol> firstOfStructure() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfStruct = new Vector<Symbol>();
		fOfStruct.add(Symbol.GENERAL);
		fOfStruct.add(Symbol.SYMETRIC);
		fOfStruct.add(Symbol.SKSYMETRIC);
		fOfStruct.add(Symbol.HERMITIAN);
		return fOfStruct;
	}

	Vector<Symbol> firstOfNiattribute() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfInt = firstOfInteger();
		
		return fOfInt;
	}

	Vector<Symbol> firstOfNumericType() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfNumType = new Vector<Symbol>();
		fOfNumType.add(Symbol.INT);
		fOfNumType.add(Symbol.REAL);
		fOfNumType.add(Symbol.COMPLEX);
		fOfNumType.add(Symbol.PATTERN);
		
		return fOfNumType;
	}

	Vector<Symbol> firstOfOrientn() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfOrient = new Vector<Symbol>();
		fOfOrient.add(Symbol.IC);
		fOfOrient.add(Symbol.IR);
		return fOfOrient;
	}

	Vector<Symbol> firstOfTextLine() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfTxtLine = new Vector<Symbol>();
		fOfTxtLine.add(Symbol.COMMENT);
		return fOfTxtLine;
	}

	Vector<Symbol> firstOfDomainNames() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfDomName = new Vector<Symbol>();
		fOfDomName.add(Symbol.BDOMAIN);
		return fOfDomName;
	}

	Vector<Symbol> firstOfModElementNames() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfModEName = new Vector<Symbol>();
		fOfModEName.add(Symbol.BMODE);
		return fOfModEName;
	}

	Vector<Symbol> firstOfInteractAttributeNames() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfIAEName = new Vector<Symbol>();
		fOfIAEName.add(Symbol.BATTRIB);
		return fOfIAEName;
	}

	Vector<Symbol> firstOfDocumentation() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfDomName = firstOfDomainNames();
		Vector<Symbol> fOfModEName = firstOfModElementNames();
		Vector<Symbol> fOfIAEName = firstOfInteractAttributeNames();
		
		Vector<Symbol> merge = new Vector<Symbol>();
		merge.addAll(fOfDomName);
		merge.addAll(fOfModEName);
		merge.addAll(fOfIAEName);
		return merge;
	}

	Vector<Symbol> firstOfIAttribute() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfInt = firstOfInteger();
		Vector<Symbol> fOfReal = firstOfReal();
		
		Vector<Symbol> merge = new Vector<Symbol>();
		merge.addAll(fOfInt);
		merge.addAll(fOfReal);
		return merge;
	}

	Vector<Symbol> firstOfReal() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfReal = new Vector<Symbol>();
		
		//fOfInt.add(Symbol.PLUS);
		//fOfInt.add(Symbol.MINUS);
		fOfReal.add(Symbol.NUMDOUBLE);
		return fOfReal;
	}

	Vector<Symbol> firstOfValues() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfIAt = firstOfIAttribute();
		return fOfIAt;
	}

	Vector<Symbol> firstOfCoorDataLine() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfInt = firstOfInteger();
		return fOfInt;
	}

	Vector<Symbol> firstOfArrayDataLine() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfInt = firstOfInteger();
		return fOfInt;
	}

	Vector<Symbol>  followOfValues() {
		// TODO Auto-generated method stub
		Vector<Symbol> fOfVal = new Vector<Symbol>();
		
		//fOfInt.add(Symbol.PLUS);
		//fOfInt.add(Symbol.MINUS);
		fOfVal.add(Symbol.NEWLINE);
		return fOfVal;
	}

}

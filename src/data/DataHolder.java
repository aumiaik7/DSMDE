package data;

import java.util.Vector;

import data.HeaderFields.FormatType;
import data.HeaderFields.ObjectType;
import data.HeaderFields.Orientn;
import data.HeaderFields.Structure;

public class DataHolder {
	
	//enum types of Header Fields
	private ObjectType objType;
	private FormatType formatType;
	private Structure structure;
	private Orientn orientn;
	
	private int rows,columns=0;
	private int prevDataRow = 0;
	private int nonZero;
	private int niAttribute = 0;
	
	//private double [][] matrix ;
	Vector<Vector <CoordColnValue>> matrix = new Vector<Vector<CoordColnValue>>();
	Vector<CoordColnValue> rowMat = new Vector<CoordColnValue>();
	
	private boolean isInit = false;
	
	
	//set the flag for array or coordinate 
	public void setObjectType(ObjectType ot)
	{
		objType = ot;
	}
	//return the flag for array or coordinate  
	public ObjectType getObjectType()
	{
		return objType;
	}
	//set the flag for array or coordinate 
	public void setFormatType(FormatType ft)
	{
		formatType = formatType;
	}
	//return the flag for array or coordinate  
	public FormatType getFormatType()
	{
		return formatType;
	}
	//set number of rows in matrix
	public void setRows(int r)
	{
		rows = r;
	}
	//get number of rows in matrix
	public int getRows()
	{
		return rows;
	}
	//set number of columns in matrix
	public void setCols(int c)
	{
		columns = c;
	}
	//get number of cols in matrix
	public int getCols()
	{
		return columns;
	}
	//set number of nonZero entries in matrix
	public void setNzN(int n)
	{
		nonZero = n;
	}
	//get number of nonzero entries in matrix
	public int getNzN()
	{
		return nonZero;
	}
	//set NIAttribute
	public void setNiAttribute(int niAttr)
	{
		niAttribute = niAttr;
	}
	//get NIAttribute
	public int getNiAttribute()
	{
		return niAttribute;
	}
	
	//set Structure 
	public void setStructure(Structure struct) {
		// TODO Auto-generated method stub
		structure = struct;
	}
	//get Structure 
	public Structure getStructure()
	{
		return structure;
	}
	//set orientation
	public void setOrientn(Orientn ortn) {
		// TODO Auto-generated method stub
		orientn = ortn;
	}
	//get Structure 
	public Orientn getOrientn()
	{
		return orientn;
	}
	public void initMatrix()
	{
		//matrix = new double[rows][columns];
		isInit = true;
	}
	//returns true if matrix is initialized
	public boolean isInitialized()
	{
		return isInit;
	}
	//set item in matrix index
	public void setItem(int rowIndex, int colIndex, Vector<Double> values) {
		// TODO Auto-generated method stub
		if(formatType == FormatType.COORDINATE)
		{
			//new item is in same row 
			if(rowIndex == prevDataRow || prevDataRow == 0)
			{
				if(values.size()!= 0)
				{
					//matrix[rowIndex-1][colIndex-1] = values.elementAt(0);
					rowMat.add(new CoordColnValue(colIndex, values.elementAt(0)));
				}
				else
				{
					//matrix[rowIndex-1][colIndex-1] = 1;
					rowMat.add(new CoordColnValue(colIndex, 1.0));
				}
			}
			else
			{
				Vector<CoordColnValue> rowMatClone = new Vector<CoordColnValue>(rowMat.size());
				rowMatClone = (Vector)rowMat.clone();
				matrix.add(rowMatClone);
				rowMat.clear();
				if(values.size()!= 0)
				{
					//matrix[rowIndex-1][colIndex-1] = values.elementAt(0);
					rowMat.add(new CoordColnValue(colIndex, values.elementAt(0)));
				}
				else
				{
					//matrix[rowIndex-1][colIndex-1] = 1;
					rowMat.add(new CoordColnValue(colIndex, 1.0));
				}
			}
			prevDataRow = rowIndex;
				
		}
		
	}
	
	
}

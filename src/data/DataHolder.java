package data;

import java.util.Vector;

public class DataHolder {
	
	private int coordOrArray;
	private int rows,columns=0;
	private int prevDataRow = 0;
	private int nonZero;
	//private double [][] matrix ;
	Vector<Vector <ColnValue>> matrix = new Vector<Vector<ColnValue>>();
	Vector<ColnValue> rowMat = new Vector<ColnValue>();
	
	private boolean isInit = false;
	
	//set the flag for array or coordinate 
	public void setCoordOrArray(int ca)
	{
		coordOrArray = ca;
	}
	//return the flag for array or coordinate  
	public int getCoordOrArray()
	{
		return coordOrArray;
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
		if(coordOrArray ==1)
		{
			//new item is in same row 
			if(rowIndex == prevDataRow || prevDataRow == 0)
			{
				if(values.size()!= 0)
				{
					//matrix[rowIndex-1][colIndex-1] = values.elementAt(0);
					rowMat.add(new ColnValue(colIndex, values.elementAt(0)));
				}
				else
				{
					//matrix[rowIndex-1][colIndex-1] = 1;
					rowMat.add(new ColnValue(colIndex, 1.0));
				}
			}
			else
			{
				Vector<ColnValue> rowMatClone = new Vector<ColnValue>(rowMat.size());
				rowMatClone = (Vector)rowMat.clone();
				matrix.add(rowMatClone);
				rowMat.clear();
				if(values.size()!= 0)
				{
					//matrix[rowIndex-1][colIndex-1] = values.elementAt(0);
					rowMat.add(new ColnValue(colIndex, values.elementAt(0)));
				}
				else
				{
					//matrix[rowIndex-1][colIndex-1] = 1;
					rowMat.add(new ColnValue(colIndex, 1.0));
				}
			}
			prevDataRow = rowIndex;
				
		}
		
	}
}

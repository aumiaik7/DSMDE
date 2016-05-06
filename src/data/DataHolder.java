package data;

import java.util.Vector;

public class DataHolder {
	
	private int coordOrArray;
	private int rows,columns;
	private int nonZero;
	private double [][] matrix = new double[12][12];
	
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
	//set number of columns in matrix
	public void setCols(int c)
	{
		columns = c;
	}
	//set number of nonZero entries in matrix
	public void setNzN(int n)
	{
		nonZero = n;
	}
	public void initMatrix()
	{
		//matrix = new double[rows][columns];
	}
	public void setItem(int rowIndex, int colIndex, Vector<Double> values) {
		// TODO Auto-generated method stub
		if(coordOrArray ==1)
		{
			if(values.size()!= 0)
				matrix[rowIndex-1][colIndex-1] = values.elementAt(0);
			else
				matrix[rowIndex-1][colIndex-1] = 1;
		}
		
	}
}
